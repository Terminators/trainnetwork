package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	boolean up = true;
	List<Section> path = new ArrayList<Section>();
	Signal source;
	Signal dest;

	public Route(Block source, Block dest)
	{
		//if the answer is negative, the direction of the route is UP
		if ((source.getsId() - dest.getsId()) > 0)
		{
			up = false;
		}
		
		if (up)
		{
			this.source = source.getSignalUp();
			this.dest = dest.getSignalUp();
		}
		
		else 
		{
			this.source = source.getSignalDown();
			this.dest = dest.getSignalDown();	
		}
		
		populatePath(source, dest);

	}
	
	public void populatePath(Block source, Block dest)
	{
		Section temp;
		if (up)
		{
			if (source.hasOneNeighbour())
			{
				temp = source.getNeighList().get(0);
			}
			else temp = source.getNeighList().get(1);

			path.add(temp);
			
			while (temp!=dest)
			{
				if (temp instanceof Block)
				{
					path.add(temp.getNeighList().get(1));
					temp = temp.getNeighList().get(1);
				}
				
				else if (temp instanceof Point)
				{
					if (((Point)temp).pointFacingUp())
					{
						if (dest.isPlus())
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
		
		else
		{
			temp = source.getNeighList().get(0);
			path.add(temp);
			
			while (temp!=dest)
			{
				if (temp instanceof Block)
				{
					path.add(temp.getNeighList().get(0));
					temp = temp.getNeighList().get(0);
				}
				
				else if (temp instanceof Point)
				{
					if (((Point)temp).pointFacingUp())
					{
						if (dest.isPlus())
						{
							path.add(temp.getNeighList().get(0));
							temp = temp.getNeighList().get(0);
						}
						
						else 
						{
							path.add(temp.getNeighList().get(1));
							temp = temp.getNeighList().get(1);
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
	
	public void printRoute()
	{
		System.out.print("(" + source + ", ");
		for (int i=0; i < path.size(); i++)
		{
			System.out.print(path.get(i) + ", ");
		}
		
		System.out.print(dest + ")");

	}
	
}
