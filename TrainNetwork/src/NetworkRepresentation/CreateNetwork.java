package NetworkRepresentation;

import java.util.HashMap;

import Routes.InvalidRouteException;
import fileReading.GMLReader;

public class CreateNetwork {
	
	private static final HashMap<Integer, Block> signalMap = new HashMap<Integer, Block>();

	public static Block blockLookup(GMLReader reader, int bId)
	{
		for (Block block : reader.getStoreBlock())
		{
			if (block.getbId() == bId)
			{
				return block;
			}
		}
		
		return null;
	}
	
	public static Point pointLookup(GMLReader reader, int pId)
	{
		for (Point point: reader.getStorePoint())
		{
			if (point.getpId() == pId)
			{
				return point;
			}
		}
		
		return null;
	}
	
	
	public static void addEdges(GMLReader reader)
	{
		//loop through the section store, and for each neighbour string, add to the neighbourlist, unless NA
		
		//for (int i = 0; i < reader.getStoreSection().size())
		
		//loop through each section
		for (int i = 0; i < reader.getStoreSection().size(); i++)
		{
			//If the current section is a block
			if (reader.getStoreSection().get(i) instanceof Block)
			{
				//LEFT NEIGHBOUR
				//If the neighbour is not NA
				if (!((Block)reader.getStoreSection().get(i)).getLeftNeighbour().equals("NA"))
				{
					//If the left neighbour is a block
					if (reader.getStoreBlock().get(i).getLeftNeighbour().substring(0, 1).equals("b"))
					{
						//add the block to the section's list of neighbours
						reader.getStoreSection().get(i).addNeigh(blockLookup(reader, Integer.parseInt(((Block)reader.getStoreSection().get(i)).getLeftNeighbour().substring(1))));
					}
					
					else //the section is a point
					{
						//add the point to the section's list of neighbours
						reader.getStoreSection().get(i).addNeigh(pointLookup(reader, Integer.parseInt(((Block)reader.getStoreSection().get(i)).getLeftNeighbour().substring(1))));
						
					}
				}
				
				//RIGHT NEIGHBOUR
				//
				if (!((Block)reader.getStoreSection().get(i)).getRightNeighbour().equals("NA"))
				{
					//If the right neighbour is a block
					if (reader.getStoreBlock().get(i).getRightNeighbour().substring(0, 1).equals("b"))
					{
						//add the block to the section's list of neighbours
						reader.getStoreSection().get(i).addNeigh(blockLookup(reader, Integer.parseInt(((Block)reader.getStoreSection().get(i)).getRightNeighbour().substring(1))));
					}
					
					else //the section is a point
					{
						//add the point to the section's list of neighbours
						reader.getStoreSection().get(i).addNeigh(pointLookup(reader, Integer.parseInt(((Block)reader.getStoreSection().get(i)).getRightNeighbour().substring(1))));
					}
				}
			}
			
			else //The current section is a point - points can never have NA neighbours
			{
				reader.getStoreSection().get(i).addNeigh(blockLookup(reader, Integer.parseInt(((Point)reader.getStoreSection().get(i)).getNeighbour1().substring(1))));
				
				reader.getStoreSection().get(i).addNeigh(blockLookup(reader, Integer.parseInt(((Point)reader.getStoreSection().get(i)).getNeighbour2().substring(1))));
				
				reader.getStoreSection().get(i).addNeigh(blockLookup(reader, Integer.parseInt(((Point)reader.getStoreSection().get(i)).getNeighbour3().substring(1))));
			}
		}
			
	}
	
	public static void findPairs(GMLReader reader)
	{

		
		for (int i = 0; i < reader.getStorePoint().size(); i++)
		{
			//if a pId is even
			if ((reader.getStorePoint().get(i).getpId() % 2 == 0) && (reader.getStorePoint().get(i).getpId() > 0))
			{
				reader.getStorePoint().get(i).setBothPairs(pointLookup(reader, reader.getStorePoint().get(i).getpId() - 1));
			}
		}

	}
	
	public static void populateSignalMap(GMLReader reader) throws InvalidNetworkException
	{
		for (int i = 0; i < reader.getStoreBlock().size(); i++)
		{
			if (reader.getStoreBlock().get(i).getSignalDown() != null)
			{
				if (signalMap.get(reader.getStoreBlock().get(i).getSignalDown().getSigId()) == null)
				{
					signalMap.put(reader.getStoreBlock().get(i).getSignalDown().getSigId(), reader.getStoreBlock().get(i));

				}
				
				else throw new InvalidNetworkException("Cannot have two of the same signal in the network");

			}
			
			if (reader.getStoreBlock().get(i).getSignalUp() != null)
			{
				
				if (signalMap.get(reader.getStoreBlock().get(i).getSignalUp().getSigId()) == null)
				{				
					signalMap.put(reader.getStoreBlock().get(i).getSignalUp().getSigId(), reader.getStoreBlock().get(i));

				}
				
				else throw new InvalidNetworkException("Cannot have two of the same signal in the network");


			}
		}
	}

	public static Block blockBySignal(int sigId)
	{
		return signalMap.get(sigId);
				
	}

}
