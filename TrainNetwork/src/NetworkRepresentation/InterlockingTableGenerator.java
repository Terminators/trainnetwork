package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

import dnl.utils.text.table.TextTable;

public class InterlockingTableGenerator {
	List<Route> journey = new ArrayList<Route>();
	TextTable tt;
	
	public InterlockingTableGenerator(List<Route> journey)
	{
		this.journey = journey;
		createTable();
	}
	
	public void createTable()
	{
		String[] columnNames = {"ID", "Source", "Destination", "Points", "Signals", "Path", "Conflict"};
		Object[][] data = new String[journey.size() + 1][];
		
		for (int i=0; i < journey.size(); i++)
		{
			
			data[i] = generateSettings(journey.get(i));
			
		}
		
		 tt =  new TextTable(columnNames, data);
	}
	
	public String[] generateSettings(Route r)
	{
		String[] row = new String[7];
		row[0] = "r" + Integer.toString(r.getrId());
		row[1] = "s" + Integer.toString(r.getSource().getSigId());
		row[2] = "s" + Integer.toString(r.getDest().getSigId());
		String pointsString = "";
		String signalsString = " ";
		row[5] = r.getPathString();
		String conflictString = " ";

		//If the route passes a point
		if (r.hasPoint())
		{
			//set the point's settings
			if (r.getPoint().pointFacingRouteDirection(r))
			{
				if (r.getSource().getOwner().isPlus() && r.getDest().getOwner().isPlus())
				{
					//The point is plus, its pair is minus
					r.getPoint().setPlus();
					r.getPoint().getPair().setMinus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":p  ";
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getPair().getpId()) + ":m  "; 
				}
				
				else
				{
					//The point is minus, its pair is plus
					r.getPoint().setMinus();
					r.getPoint().getPair().setPlus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":m  ";
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getPair().getpId()) + ":p  "; 
				}
			}
			
			else 
			{
				if (r.getSource().getOwner().isPlus() && r.getDest().getOwner().isPlus())
				{
					//The point is plus
					r.getPoint().setPlus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":p  ";
				}
				
				else
				{
					//The point is minus
					r.getPoint().setMinus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":m  ";
				}
			}
			
			//Then Flanking Signal (not needed if there are no points in route)
			if (r.getPoint().isPlus())
			{
				if (r.getPoint().pointFacingUp())
				{
					((Block)r.getPoint().getNeighList().get(1)).getSignalDown().setStop();
					signalsString = signalsString + "s" + Integer.toString(((Block)r.getPoint().getNeighList().get(1)).getSignalDown().getSigId()) + " ";
				
				}
				
				else 
				{
					((Block)r.getPoint().getNeighList().get(0)).getSignalUp().setStop();
					signalsString = signalsString + "s" + Integer.toString(((Block)r.getPoint().getNeighList().get(0)).getSignalUp().getSigId()) + " ";
				}

			}
			
		}
		
		
		row[3] = pointsString;
		row[4] = signalsString;
		row[6] = conflictString;

		return row;
	}
	
	public void printTable()
	{
		System.out.println("");
		tt.printTable();
	}

}
