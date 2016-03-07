package Interlocking;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
		createTable();
	}
	
	public InterlockingTableGenerator(List<Route> journey)
	{
		this.journey = journey;
		createTableNoInput();
	}
	
	public void createTableNoInput()
	{
		String[] columnNames = {"ID", "Source", "Destination", "Points", "Signals", "Path", "Conflict"};
		Object[][] data = new String[journey.size() + 1][];
		
		for (int i=0; i < journey.size(); i++)
		{
			
			data[i] = generateSettings(journey.get(i));
			
		}
		
		 tt =  new TextTable(columnNames, data);
	}

	public void addToTable()
	{

		String[] columnNames = { "ID", "Source", "Destination", "Points", "Signals", "Path", "Conflict" };
		List<Object[]> dataList = new ArrayList<Object[]>();

		for (int journeyCounter = 0; journeyCounter < journeys.size(); journeyCounter++)
		{
			List<Route> currentJourney = journeys.get(journeyCounter);
			for (int routeCounter = 0; routeCounter < currentJourney.size(); routeCounter++)
			{
				dataList.add(generateSettings(currentJourney.get(routeCounter)));
			}
		}
		

		Object[][] data = new String[dataList.size()][];
		data = dataList.toArray(data);

		tt = new TextTable(columnNames, data);
	}

	public void createTable() throws InvalidRouteException
	{
		try
		{
			Scanner loop = new Scanner(System.in);
			System.out.println("How many journeys do you wish to add?");
			int count = loop.nextInt();
			for (int i = 0; i < count; i++)
			{
				System.out.println("You are on journey " + (i + 1));
				createJourneys();
			}
			addToTable();

		}
		catch (InputMismatchException e1)
		{
			throw new InvalidRouteException("Must input a positive integer for number of journeys");
		}
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
			// set the point's settings
			if (r.getPoint().pointFacingRouteDirection(r))
			{
				if (r.getSourceOwner().isPlus() && r.getDestOwner().isPlus())
				{
					// The point is plus, its pair is minus
					r.getPoint().setPlus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":p  ";
					if (r.getPoint().getPair() != null)
					{
						r.getPoint().getPair().setMinus();
						pointsString = pointsString + "p" + Integer.toString(r.getPoint().getPair().getpId()) + ":m  ";
					}
				}

				else
				{
					// The point is minus, its pair is plus
					r.getPoint().setMinus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":m  ";

					if (r.getPoint().getPair() != null)
					{
						r.getPoint().getPair().setPlus();
						pointsString = pointsString + "p" + Integer.toString(r.getPoint().getPair().getpId()) + ":p  ";
					}
					
				}
			}

			else
			{
				if (r.getSourceOwner().isPlus() && r.getDestOwner().isPlus())
				{
					// The point is plus
					r.getPoint().setPlus();
					pointsString = pointsString + "p" + Integer.toString(r.getPoint().getpId()) + ":p  ";
				}

				else
				{
					// The point is minus
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

		// Flanking Signal (not needed if there are no points in route)
		if (r.hasPoint())
		{
			if (r.getPoint().isPlus())
			{
				if (r.getPoint().pointFacingUp())
				{
					((Block) r.getPoint().getNeighList().get(1)).getSignalDown().setStop();
					signalsString = signalsString + "s"
							+ Integer.toString(((Block) r.getPoint().getNeighList().get(1)).getSignalDown().getSigId()) + " ";

				}

				else
				{
					((Block) r.getPoint().getNeighList().get(0)).getSignalUp().setStop();
					signalsString = signalsString + "s"
							+ Integer.toString(((Block) r.getPoint().getNeighList().get(0)).getSignalUp().getSigId()) + " ";
				}
			}

			else
			{
				if (r.getPoint().pointFacingUp())
				{
					((Block) r.getPoint().getNeighList().get(2)).getSignalDown().setStop();
					signalsString = signalsString + "s"
							+ Integer.toString(((Block) r.getPoint().getNeighList().get(2)).getSignalDown().getSigId()) + " ";

				}

				else
				{
					((Block) r.getPoint().getNeighList().get(1)).getSignalUp().setStop();
					signalsString = signalsString + "s"
							+ Integer.toString(((Block) r.getPoint().getNeighList().get(1)).getSignalUp().getSigId()) + " ";
				}
			}

		}

		// Path Signals
		if (r.isUp())
		{
			//loop through all the sections in a route's path
			for (Section section : r.getPath())
			{
				//if the current section in the path is a block
				if (section instanceof Block)
				{
					//if the current block has a down signal
					if (((Block)section).getSignalDown() != null)
					{						
						//set it to be stop (and add to table)
						((Block) section).getSignalDown().setStop();
						signalsString = signalsString + "s" + Integer.toString(((Block) section).getSignalDown().getSigId()) + " ";

					}

					else //if it doesn't have a down signal (next to a point)
					{
						//if the current section in the loop is the destination signal's owner
						if (section.equals(r.getDestOwner()))
						{
							//set it to be stop (and add to table)
							((Block) r.getDestOwner().getNeighList().get(1)).getSignalDown().setStop();
							signalsString = signalsString + "s"
									+ Integer.toString(((Block) r.getDestOwner().getNeighList().get(1)).getSignalDown().getSigId()) + " ";
						}
					}
				}
			}
		}

		else
		{
			for (Section section : r.getPath())
			{
				if (section instanceof Block)
				{
					if (((Block)section).getSignalUp() != null)
					{
						((Block) section).getSignalUp().setStop();
						signalsString = signalsString + "s" + Integer.toString(((Block) section).getSignalUp().getSigId()) + " ";

					}

					else
					{
						if (section.equals(r.getPath().get(r.getPath().size() - 1)))
						{
							((Block) r.getDestOwner().getNeighList().get(0)).getSignalUp().setStop();
							signalsString = signalsString + "s"
									+ Integer.toString(((Block) r.getDestOwner().getNeighList().get(0)).getSignalUp().getSigId()) + " ";
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
		
		
		//loop through every journey in the 
		
		for (int journeyCounter = 0; journeyCounter < journeys.size(); journeyCounter++)
		{
			journey = journeys.get(journeyCounter);
			// for each route in the total journey, excluding the current route
			for (int routeCounter = 0; routeCounter < journey.size(); routeCounter++)
			{

				// excludes current route from being compared
				if (!journey.get(routeCounter).equals(r))
				{
					boolean conflictFound = false;
					Route compareRoute = journey.get(routeCounter);
					int pathCounter = 0;

					// loop through the compareRoute's path until conflict is found
					// with the current section of the original route
					while (!conflictFound && pathCounter < compareRoute.getPath().size())
					{
						// loop through every section in the path for the current
						// route(current route decided in createTable method)
						for (Section section : r.getPath())
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

				// if journey is the current route and not the last element,
				// ignore the route
				else //if (routeCounter < journey.get(routeCounter).getPath().size() - 1)
				{
					

				}

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
