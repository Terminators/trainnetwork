package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Interlocking.InterlockingTableGenerator;
import NetworkRepresentation.CreateNetwork;
import Routes.InvalidRouteException;
import Routes.Route;
import fileReading.GMLReader;

public class test {

	public static void main(String[] args) throws InvalidRouteException, IOException {
		
		GMLReader reader = new GMLReader();

		reader.readFile("D:\\workspace D\\TrainNetwork\\testNetworkFile.txt");

		for (int i = 0; i < reader.getStoreBlock().size(); i++) {
			
			//System.out.println(reader.getStoreBlock().get(i).blockString() + "\n");

		}
		
		
		for (int l = 0; l < reader.getStorePoint().size(); l++) {
			
			System.out.println(reader.getStorePoint().get(l).pointString() + "\n");

		}
		
		CreateNetwork.addEdges(reader);
		CreateNetwork.findPairs(reader);
		CreateNetwork.populateSignalMap(reader);
		
		Route r1 = new Route(1, 1, 6);
		Route r2 = new Route(2, 6, 7);
		Route r3 = new Route(3, 8, 3);
		Route r4 = new Route(4, 3, 2);
		Route r5 = new Route(5, 1, 4);
		Route r6 = new Route(6, 4, 7);
		Route r7 = new Route(7, 8, 5);
		Route r8 = new Route(8, 5, 2);

		List<Route> j1 = new ArrayList<Route>();
		j1.add(r1);
		j1.add(r2);
		j1.add(r3);
		j1.add(r4);
		j1.add(r5);
		j1.add(r6);
		j1.add(r7);
		j1.add(r8);
		
		InterlockingTableGenerator t = new InterlockingTableGenerator(j1);
		t.printTable();
	}

}
