package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

public class InterlockingTableGenerator {
	List<Route> journey= new ArrayList<Route>();
	
	public InterlockingTableGenerator(List<Route> journey)
	{
		this.journey = journey;
	}
	
	public void createTable()
	{
		for (int i=0; i < journey.size(); i++)
		{
			journey.get(0);
			
			
			
			
			journey.remove(0);
		}

	}
	
	public void generateSettings(Route r)
	{
		
	}

}
