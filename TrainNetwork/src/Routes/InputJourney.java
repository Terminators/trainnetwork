package Routes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputJourney {

	private int sId;
	private int dId;
	int terminate = 0;
	boolean fail = false;
	ArrayList<Route> journey = new ArrayList<Route>();

	public void inputRoute() throws InvalidRouteException
	{

		terminate++;
		if (terminate < 4)
		{
			try
			{
				Scanner input = new Scanner(System.in);
				System.out.println("Please Enter a beginning destination ------ Such as S1");
				sId = Integer.parseInt(input.next().substring(1));
				System.out.println("Please Enter a final destination ------ Such as S6");
				dId = Integer.parseInt(input.next().substring(1));
				Route r = Route.getInstance(Route.getRoutes().size() + 1, sId, dId);
				journey.add(r);
			} catch (java.lang.NumberFormatException e1)
			{
				System.out
						.println("Soure destination must be in the following format B1 or P1. Please ensure the second characters onward are numbers");
				inputRoute();

			}
		}
		else{
			System.out.println("Incorrect value entered three times. Program Shutdown");

		}
	}
	
	public void jMake() throws InvalidRouteException{
		Scanner loop = new Scanner(System.in);
		System.out.println("How many routes do you wish to add?");
		int count = loop.nextInt();
		for (int i = 0; i<count; i++){
			inputRoute();	
		}
	}
	
	public ArrayList<Route> getJourney()
	{
		return journey;
	}

//	public static void main(String[] args) throws IOException
//	{
//		InputRoute one = new InputRoute();
//
//		one.inputRoute();
//
//	}

}
