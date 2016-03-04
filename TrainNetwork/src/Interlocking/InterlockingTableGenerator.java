package Interlocking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import NetworkRepresentation.Block;
import NetworkRepresentation.Section;
import Routes.InputJourney;
import Routes.InvalidRouteException;
import Routes.Route;
import dnl.utils.text.table.TextTable;

public class InterlockingTableGenerator {
	List<Route> journey = new ArrayList<Route>();
	List<ArrayList<Route>> journeys = new ArrayList<ArrayList<Route>>();
	TextTable tt;
	
	public InterlockingTableGenerator() throws InvalidRouteException
	{
		mergeTables();
	}
	
	public void createTable()
	{
		
		String[] columnNames = {"ID", "Source", "Destination", "Points", "Signals", "Path", "Conflict"};
		//Object[][] data = new Object[journeys.size()][1];
		Object[][] data = new String[journeys.size() * 10][];
		
		int totalRouteCounter = 0;

		for (int i=0; i < journeys.size(); i++)
		{
			
			//data = new String[journeys.get(i).size()][];
			if (totalRouteCounter < 3)
			{
				for (int journeyCounter=0; journeyCounter < journeys.get(i).size(); journeyCounter++)
				{
					ArrayList<Route> routeList = journeys.get(i);
					data[totalRouteCounter]
							= generateSettings(routeList.get(journeyCounter));
					totalRouteCounter++;
				}
			}
		}

		 tt =  new TextTable(columnNames, data);
	}
	
	public void mergeTables() throws InvalidRouteException{
		Scanner loop = new Scanner(System.in);
		System.out.println("How many journeys do you wish to add?");
		int count = loop.nextInt();
		for (int i = 0; i<count; i++){
			System.out.println("You are on journey " + (i+1));
			createJourneys();	
		}
		createTable();
	}
	
	public List<ArrayList<Route>> createJourneys() throws InvalidRouteException
	{
		
		InputJourney journey = new InputJourney();
		
		journey.jMake();
		this.journey = journey.getJourney();
		journeys.add(journey.getJourney());
		
		return journeys;
		
	}
	
	public String[] generateSettings(Route r)
	{
		String[] row = new String[7];
		row[0] = "r" + Integer.toString(r.getrId());
		row[1] = "s" + Integer.toString(r.getSourceId());
		row[2] = "s" + Integer.toString(r.getDestId());
		row[5] = r.getPathString();

		
		row[3] = pointSettings(r);
		row[4] = signalSettings(r);
		row[6] = conflictSettings(r);

		return row;
	}
	
	public String pointSettings(Route r)
	{
		String pointsString = "";
		if (r.hasPoint())
		{
			//set the point's settings
			if (r.getPoint().pointFacingRouteDirection(r))
			{
				if (r.getSourceOwner().isPlus() && r.getDestOwner().isPlus())
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
				if (r.getSourceOwner().isPlus() && r.getDestOwner().isPlus())
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
		}
		return pointsString;
	}
	
	public String signalSettings(Route r)
	{
		String signalsString = " ";

		if (r.hasPoint())
		{
			
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
			
			else 
			{
				if (r.getPoint().pointFacingUp())
				{
					((Block)r.getPoint().getNeighList().get(2)).getSignalDown().setStop();
					signalsString = signalsString + "s" + Integer.toString(((Block)r.getPoint().getNeighList().get(2)).getSignalDown().getSigId()) + " ";
				
				}
				
				else 
				{
					((Block)r.getPoint().getNeighList().get(1)).getSignalUp().setStop();
					signalsString = signalsString + "s" + Integer.toString(((Block)r.getPoint().getNeighList().get(1)).getSignalUp().getSigId()) + " ";
				}
			}
		
		}

		
		
		//Path Signals
		if (r.isUp())
		{
			for (Section section : r.getPath() )
			{
				if (section instanceof Block)
				{
					if(r.getDestOwner().getSignalDown() != null)
					{
						((Block) section).getSignalDown().setDown();
						signalsString = signalsString + "s" + Integer.toString(((Block) section).getSignalDown().getSigId()) + " ";

					}
					
					else 
					{
						if (section.equals(r.getPath().get(r.getPath().size() - 1)))
						{
							((Block) r.getDestOwner().getNeighList().get(1)).getSignalDown().setStop();
							signalsString = signalsString + "s" + Integer.toString(((Block) r.getDestOwner().getNeighList().get(1)).getSignalDown().getSigId()) + " ";
						}
					}
				}
			}	
		}
		
		else
		{
			for (Section section : r.getPath() )
			{
				if (section instanceof Block)
				{
					if(r.getDestOwner().getSignalUp() != null)
					{
						((Block) section).getSignalUp().setUp();
						signalsString = signalsString + "s" + Integer.toString(((Block) section).getSignalUp().getSigId()) + " ";

					}
					
					else 
					{
						if (section.equals(r.getPath().get(r.getPath().size() - 1)))
						{
							((Block) r.getDestOwner().getNeighList().get(0)).getSignalUp().setStop();
							signalsString = signalsString + "s" + Integer.toString(((Block) r.getDestOwner().getNeighList().get(0)).getSignalUp().getSigId()) + " ";
						}
					}
				}
			}	
		}

		return signalsString;
	}

	public String conflictSettings(Route r)
	{
		String conflictString = " ";
		
		//for each route in the total journey, excluding the current route 
		for(int routeCounter = 0; routeCounter < journey.size(); routeCounter++)
		{
			
			//excludes current route from being compared
			if (!journey.get(routeCounter).equals(r))
			{
				boolean conflictFound = false;
				Route compareRoute = journey.get(routeCounter);
				int pathCounter = 0;
				
				//loop through the compareRoute's path until conflict is found with the current section of the original route
				while(!conflictFound && pathCounter < compareRoute.getPath().size())
				{
					//loop through every section in the path for the current route(current route decided in createTable method)
					for (Section section: r.getPath())
					{	
						
						if (section.equals(compareRoute.getPath().get(pathCounter)))
						{
							conflictFound = true;
							conflictString = conflictString + "r" + Integer.toString(compareRoute.getrId()) + " ";	

						}
					}
					
					pathCounter++;
	
				}
				
			}
				
			//if journey is the current route and not the last element, increment the routeCounter 
			else if (routeCounter < journey.get(routeCounter).getPath().size() - 1)
			{
				routeCounter++;		
				
			}
					
		}
		
		return conflictString;

	}
	
	public void printTable()
	{
		System.out.println("");
		tt.printTable();
	}

}
