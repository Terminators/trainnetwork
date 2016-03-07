package Routes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Ryan Crosby, Jack Chandler
 * @project TrainNetwork
 * @version 1
 */

public class InputJourney {

	private int sId;
	private int dId;
	int terminate = 0;
	boolean fail = false;
	ArrayList<Route> journey = new ArrayList<Route>();
	int failCounter;

	public void inputRoute() throws InvalidRouteException
	{

		if (terminate < 3)
		{
			try
			{
				Scanner inputSource = new Scanner(System.in);
				System.out.println("Please Enter a beginning destination ------ Such as S1");
				sId = Integer.parseInt(inputSource.next().substring(1));
			} catch (java.lang.NumberFormatException e1)
			{
				System.out.println("Source must be in the following format S1 or s1, please input route again");

				//failCounter++;
				terminate++;
				inputRoute();

			}
			
			try
			{
				Scanner inputDestination = new Scanner(System.in);
				System.out.println("Please Enter a final destination ------ Such as S6");
				dId = Integer.parseInt(inputDestination.next().substring(1));
				Route r = Route.getInstance(Route.getRoutes().size() + 1, sId, dId);
				journey.add(r);
			} catch (java.lang.NumberFormatException e1)
			{
				System.out.println("Destination must be in the following format S1 or s1, please input route again");

				//failCounter++;
				terminate++;
				inputRoute();
			}
		} else
		{
			throw new InvalidRouteException("Incorrect value entered three times. Program Shutdown");

		}
		

	}

	public void jMake() throws InvalidRouteException
	{
		Scanner loop = new Scanner(System.in);
		try
		{
			System.out.println("How many routes do you wish to add?");
			int count = loop.nextInt();
			for (int i = 0; i < count; i++)
			{
				//i = i - failCounter;
				inputRoute();
			}
		} catch (InputMismatchException e1)
		{
			throw new InvalidRouteException("Must input a positive integer for number of routes");
		}

	}

	public ArrayList<Route> getJourney()
	{
		return journey;
	}

	public int getFailCounter()
	{
		return failCounter;
	}

	// public static void main(String[] args) throws IOException
	// {
	// InputRoute one = new InputRoute();
	//
	// one.inputRoute();
	//
	// }

}
