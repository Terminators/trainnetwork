package fileReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import NetworkRepresentation.*;
import Routes.InvalidRouteException;

/**
 * 
 * @author Ryan Crosby
 * @project TrainNetwork
 * @version 1
 */
public class GMLReader {
	ArrayList<Block> storeBlock = new ArrayList<Block>(); // Store a list of all
															// the blocks from
															// the file
	ArrayList<Point> storePoint = new ArrayList<Point>(); // Store a list of all
															// the point from
															// the file
	List<Section> storeSection = new ArrayList<Section>(); // Store list of
															// every section of
															// the track

	ValidateNetwork v = new ValidateNetwork();

	public void readFile(String path) throws IOException, InvalidNetworkException, InvalidRouteException
	{

		BufferedReader input = new BufferedReader(new FileReader(path));

		while (input.readLine() != null)
		{
			String line = input.readLine();

			if (line == null)
			{
				break;
			}

			if (line.equals("Block"))
			{

				String bId = input.readLine();
				String left = input.readLine();
				String right = input.readLine();
				String track = input.readLine();
				Boolean up = null;
				if (track.equals("plus"))
				{
					up = true;
				} else if (track.equals("minus"))
				{
					up = false;
				}

				String signalUp = input.readLine();
				String signalDown = input.readLine();

				Block b = Block.getInstance(bId.trim(), left.trim(), right.trim(), up, signalUp.trim(), signalDown.trim());
				ValidateNetwork.validateBlock(b);
				storeSection.add(b);
				storeBlock.add(b);

			} else if (line.equals("Point"))
			{

				String pId = input.readLine();
				String neighbour1 = input.readLine();
				String neighbour2 = input.readLine();
				String neighbour3 = input.readLine();

				Point p = Point.getInstance(pId.trim(), neighbour1.trim(), neighbour2.trim(), neighbour3.trim());
				ValidateNetwork.validatePoint(p);

				storeSection.add(p);
				storePoint.add(p);

			}

			else
			{
				throw new InvalidRouteException("Please only define a Block or a Point.");
			}

		}

		input.close();
	}

	public ArrayList<Block> getStoreBlock()
	{
		return storeBlock;
	}

	public ArrayList<Point> getStorePoint()
	{
		return storePoint;
	}

	public List<Section> getStoreSection()
	{
		return storeSection;
	}

	// public static void main(String[] args) throws IOException {
	// GMLReader one = new GMLReader();
	//
	// one.readFile("one.txt");
	//
	// for (int i = 0; i < one.storeBlock.size(); i++) {
	//
	// System.out.println(one.storeBlock.get(i).toString() + "\n");
	//
	// }
	//
	//
	// for (int l = 0; l < one.storePoint.size(); l++) {
	//
	// System.out.println(one.storePoint.get(l).toString() + "\n");
	//
	// }
	// }

}
