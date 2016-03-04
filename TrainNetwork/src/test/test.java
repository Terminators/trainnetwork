package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Interlocking.InterlockingTableGenerator;
import NetworkRepresentation.CreateNetwork;
import Routes.InputJourney;
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
		
		

		List<Route> j1 = routes.getJourney();

//		j1.add(r1);
//		j1.add(r2);
//		j1.add(r3);
//		j1.add(r4);
//		j1.add(r5);
//		j1.add(r6);
//		j1.add(r7);
//		j1.add(r8);
//		
		InterlockingTableGenerator t = new InterlockingTableGenerator();
		t.printTable();
	}

}
