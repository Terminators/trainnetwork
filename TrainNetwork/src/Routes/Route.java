package Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.NotNull;
import NetworkRepresentation.Block;
import NetworkRepresentation.CreateNetwork;
import NetworkRepresentation.InvalidNetworkException;
import NetworkRepresentation.Point;
import NetworkRepresentation.Section;
import NetworkRepresentation.Signal;

public class Route {	
	
	private static final HashMap<String, Route> Routes = new HashMap<String, Route>();

	@NotNull
	private int rId;
	
	private Signal dest;
	private Signal source;

	private boolean up = true;
	private List<Section> path = new ArrayList<Section>();
	private boolean hasPoint;
	
	public Route(int rId, @NotNull int sourceId,  @NotNull int destId) throws InvalidRouteException{
		this.rId = rId;
		source = CreateNetwork.blockBySignal(sourceId).getSignalFromSigId(sourceId);
		dest = CreateNetwork.blockBySignal(destId).getSignalFromSigId(destId);
		
		//if the answer is negative, the direction of the route is UP
		if ((sourceId - destId) > 0)
		{
			up = false;
		}
		
		populatePath(CreateNetwork.blockBySignal(this.source.getSigId()), CreateNetwork.blockBySignal(this.dest.getSigId()) );				
	}
	
	public static Route getInstance(int rId, int sourceId, int destId) throws InvalidRouteException{
		final String key = "Route " + Integer.toString(rId);
		
		if(!Routes.containsKey(key)){
			
		Routes.put(key, new Route(rId, sourceId, destId));
			
			
		}
		
		return Routes.get(key);
	}
	
	public int getrId() {
		return rId;
	}



	public void setrId(int rId) {
		this.rId = rId;
	}



	public boolean isUp() {
		return up;
	}
	
	public List<Section> getPath() {
		return path;
	}



	public void setPath(List<Section> path) {
		this.path = path;
	}
	


	public int getSourceId() {
		return this.source.getSigId();
	}



	public int getDestId() {
		return this.dest.getSigId();
	}
	
	
	public static HashMap<String, Route> getRoutes()
	{
		return Routes;
	}

	public boolean isHasPoint()
	{
		return hasPoint;
	}

	public Block getSourceOwner()
	{
		return CreateNetwork.blockBySignal(this.source.getSigId());
	}

	public Block getDestOwner()
	{
		return CreateNetwork.blockBySignal(this.dest.getSigId());

	}

	public void populatePath(Block sourceBlock, Block destBlock) throws InvalidRouteException
	{
		int blockCounter = 1;
		Section temp;
		
		//UP
		if (up)
		{

			if (sourceBlock.hasOneNeighbour())
			{
				temp = sourceBlock.getNeighList().get(0);
			}
			else temp = sourceBlock.getNeighList().get(1);

			path.add(temp);
			
			while (temp!=destBlock)
			{
				if (temp instanceof Block)
				{
					try
					{
						//If both parts of the network are Blocks (and neighbours) and they aren't on the same track (plus and minus) then throw an error
						if (temp.getNeighList().get(1) instanceof Block) 
						{
							if (((Block) temp).isPlus() == ((Block)temp.getNeighList().get(1)).isPlus())
							{
								path.add(temp.getNeighList().get(1));
								temp = temp.getNeighList().get(1);
								blockCounter++;
							}
							
							else throw new InvalidRouteException("Cannot create a route with two consecutive blocks where each block is on a different part of the track.");

						}
						
						else 
						{
							path.add(temp.getNeighList().get(1));
							temp = temp.getNeighList().get(1);
							blockCounter++;
						}
					} catch (java.lang.IndexOutOfBoundsException e1) //Stops a route that will go from inside a pair of points to a block inside another pair of points but on the other track
					{
						throw new InvalidRouteException("Cannot create a route which goes back on itself.");

					}
				}
				
				else if (temp instanceof Point)
				{
					hasPoint = true;
					if (((Point)temp).pointFacingUp())
					{
						if (destBlock.isPlus())
						{
							path.add(temp.getNeighList().get(2));
							temp = temp.getNeighList().get(2);
						}
						
						else 
						{
							path.add(temp.getNeighList().get(1));
							temp = temp.getNeighList().get(1);
						}
					}
					
					else
					{
						path.add(temp.getNeighList().get(2));
						temp = temp.getNeighList().get(2);
					} 
				} 
				
			} 
			
//			if (blockCounter > 2)
//			{
//				throw new InvalidRouteException("Problem with " + this.toString() + "Can only create routes that have a maximum of 2 track sections.");
//			}
			
		}
		
		//DOWN
		else
		{
			temp = sourceBlock.getNeighList().get(0);
			path.add(temp);
			
			while (temp!=destBlock)
			{
				if (temp instanceof Block)
				{
					try
					{
						
						//If both parts of the network are Blocks (and neighbours) and they aren't on the same track (plus and minus) then throw an error
						if (temp.getNeighList().get(1) instanceof Block) 
						{
							if (temp instanceof Block)
							{
								path.add(temp.getNeighList().get(0));
								temp = temp.getNeighList().get(0);
								blockCounter++;
							}
							
							else throw new InvalidRouteException("Cannot create a route with two consecutive blocks where each block is on a different part of the track.");
						}
						
						else 
						{
							path.add(temp.getNeighList().get(0));
							temp = temp.getNeighList().get(0);
							blockCounter++;
						}
					} catch (java.lang.IndexOutOfBoundsException e1) //Stops a route that will go from inside a pair of points to a block inside another pair of points but on the other track
					{
						throw new InvalidRouteException("Cannot create a route which goes back on itself.");

					}
					
				}
				
				else if (temp instanceof Point)
				{
					hasPoint = true;

					if (!(((Point)temp).pointFacingUp()))
					{
						if (destBlock.isPlus())
						{
							path.add(temp.getNeighList().get(1));
							temp = temp.getNeighList().get(1);
						}
						
						else 
						{
							path.add(temp.getNeighList().get(0));
							temp = temp.getNeighList().get(0);
						}
					}
					
					else
					{
						path.add(temp.getNeighList().get(0));
						temp = temp.getNeighList().get(0);
					}

				} 
			}
			
//			if (blockCounter > 2)
//			{
//				throw new InvalidRouteException("Problem with " + this.toString() + "Can only create routes that have a maximum of 2 track sections.");
//			}
		}
		
		if (path.size() == 1)
		{
			path.add(CreateNetwork.blockBySignal(this.source.getSigId()));
		}
		
	}
	
	public boolean hasPoint() {
		return hasPoint;
	}
	
	public Point getPoint()
	{
		for (Section section : path )
		{
			if (section instanceof Point)
			{
				return (Point) section;
			}
		}
		return null;
	}

	public void printRoute()
	{
		System.out.print("(s" + this.source.getSigId() + ", ");
		for (int i=0; i < path.size(); i++)
		{
			System.out.print(path.get(i) + ", ");
		}
		
		System.out.print("s" + this.dest.getSigId() + ")");
		System.out.println("");

	}
	
	public String getPathString()
	{
		String pathString = "";
		for (int i=0; i < path.size(); i++)
		{
			pathString = pathString + path.get(i) + ", ";
		}	
		
		return pathString.substring(0, pathString.length() - 2);
	}
	
	public void validateRoute() throws InvalidRouteException
	{
		Validator validator = new Validator();

		List<ConstraintViolation> violations = validator.validate(this);
		
		validRoute();
		
		if(violations.size()>0)
		{
		  System.out.println("Route " + this + " is invalid.");
		  throw new InvalidRouteException(violations);
		}
		
	}
	
	public void validRoute() throws InvalidRouteException
	{		
		int pointCounter = 0;
		
		for (Section section : this.path )
		{
			if (section instanceof Point)
			{
				pointCounter++;
			}
		}
		
		if (pointCounter > 1)
		{
			throw new InvalidRouteException("Route r" + this + ": Routes can only pass through one point.");

		}
		
		if (this.source.getSigId() == this.dest.getSigId())
		{
			throw new InvalidRouteException("Route r" + rId + ": Source and Destination signals cannot be equal.");
		}
		
		if (source.isUp() != dest.isUp())
		{
			throw new InvalidRouteException("Route r" + rId + ": Cannot go from a signal in one direction, to a signal in another direction.");
		}
		
		// Checks whether the source signal is the same direction as the direction of the route
		if (source.isUp() != up)
		{
			throw new InvalidRouteException("Route r" + rId + ": The source signal must go in the direction of the route");
		}
		
		// Checks whether the destination signal is the same direction as the direction of the route
		if (dest.isUp() != up)
		{
			throw new InvalidRouteException("Route r" + rId + ": The destination signal must go in the direction of the route");

		}

	}
	
	public String toString()
	{
		return "r" + rId;

	}
}
