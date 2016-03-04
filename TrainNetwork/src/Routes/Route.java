package Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import NetworkRepresentation.Block;
import NetworkRepresentation.CreateNetwork;
import NetworkRepresentation.Point;
import NetworkRepresentation.Section;

public class Route {	
	
	private static final HashMap<String, Route> Routes = new HashMap<String, Route>();

	private int destId;
	private int rId;
	private boolean up = true;
	private List<Section> path = new ArrayList<Section>();
	//private Point point;
	private boolean hasPoint;
	private int sourceId;

	
	private Route(int rId, int sourceId, int destId) throws InvalidRouteException{
		this.rId = rId;
		this.sourceId = sourceId;
		this.destId = destId; 
		
		//if the answer is negative, the direction of the route is UP
		if ((sourceId - destId) > 0)
		{
			up = false;
		}
		
		populatePath(CreateNetwork.blockBySignal(this.sourceId), CreateNetwork.blockBySignal(this.destId) );
		validRoute();
				
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
		return sourceId;
	}



	public int getDestId() {
		return destId;
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
		return CreateNetwork.blockBySignal(sourceId);
	}

	public Block getDestOwner()
	{
		return CreateNetwork.blockBySignal(destId);

	}

	public void populatePath(Block sourceBlock, Block destBlock)
	{
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
					path.add(temp.getNeighList().get(1));
					temp = temp.getNeighList().get(1);
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
					path.add(temp.getNeighList().get(0));
					temp = temp.getNeighList().get(0);
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
		}
		
	}
	
	public boolean hasPoint() {
		return hasPoint;
	}
	
	public Point getPoint()
	{
		for (Section section : this.path )
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
		System.out.print("(s" + sourceId + ", ");
		for (int i=0; i < path.size(); i++)
		{
			System.out.print(path.get(i) + ", ");
		}
		
		System.out.print("s" + destId + ")");
		System.out.println("");

	}
	
	public String getPathString()
	{
		String pathString = "";
		for (int i=0; i < path.size(); i++)
		{
			pathString = pathString + path.get(i) + ", ";
		}
		return pathString;
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
			throw new InvalidRouteException("Routes can only pass through one point.");

		}
		
		if (sourceId == destId)
		{
			throw new InvalidRouteException("Source and Destination signals cannot be equal.");
		}

	}
	
	public String toString()
	{
		return "r" + rId;

	}
}
