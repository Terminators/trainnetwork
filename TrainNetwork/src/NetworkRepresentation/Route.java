package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	boolean up;
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

	}
	
	public void populatePath(Block source, Block dest)
	{
		if (up)
		{
			Section temp = source.getNeighList().get(1);
			
			while (temp!=dest)
			{
			     //Block temp;
			} 
			
		}
		
	}
	
}
