package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	private int rId;
	private boolean up = true;
	private List<Section> path = new ArrayList<Section>();
	private Signal source;
	private Signal dest;
	//private Point point;
	private boolean hasPoint;

	public Route(int rId, Signal source, Signal dest) throws InvalidRouteException
	{
		this.rId = rId;
		
		//if the answer is negative, the direction of the route is UP
		if ((source.getOwner().getbId() - dest.getOwner().getbId()) > 0)
		{
			up = false;
		}
		
		this.source = source;
		this.dest = dest;
		
		populatePath(source.getOwner(), dest.getOwner());
		
		validRoute();
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



	public Signal getSource() {
		return source;
	}



	public void setSource(Signal source) {
		this.source = source;
	}



	public Signal getDest() {
		return dest;
	}



	public void setDest(Signal dest) {
		this.dest = dest;
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
		System.out.print("(" + source + ", ");
		for (int i=0; i < path.size(); i++)
		{
			System.out.print(path.get(i) + ", ");
		}
		
		System.out.print(dest + ")");

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
		
		if (source.equals(dest))
		{
			throw new InvalidRouteException("Source and Destination signals cannot be equal.");
		}

	}

}
