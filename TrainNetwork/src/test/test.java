package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Interlocking.InterlockingTableGenerator;
import NetworkRepresentation.CreateNetwork;
import NetworkRepresentation.InvalidNetworkException;
import NetworkRepresentation.ValidateNetwork;
import Routes.InvalidRouteException;
import Routes.Route;
import fileReading.GMLReader;

public class test {

	public static void main(String[] args) throws InvalidRouteException, IOException, InvalidNetworkException {
		
		GMLReader reader = new GMLReader();

		String filePath = new File("").getAbsolutePath();
		
		reader.readFile((filePath + "/src/ExtendedNW.txt"));

		for (int i = 0; i < reader.getStoreBlock().size(); i++) {
			
			System.out.println(reader.getStoreBlock().get(i).blockString() + "\n");

		}
		
		
		for (int l = 0; l < reader.getStorePoint().size(); l++) {
			
			System.out.println(reader.getStorePoint().get(l).pointString() + "\n");

		}
				
		CreateNetwork.addEdges(reader);
		CreateNetwork.findPairs(reader);
		CreateNetwork.populateSignalMap(reader);
		
//		Route r1 = new Route(1, 1, 6);
//		Route r2 = new Route(2, 6, 7);
//		Route r3 = new Route(3, 8, 3);
//		Route r4 = new Route(4, 3, 2);
//		Route r5 = new Route(5, 1, 4);
//		Route r6 = new Route(6, 4, 7);
//		Route r7 = new Route(7, 8, 5);
//		Route r8 = new Route(8, 5, 2);
		
//		Route r1 = new Route(1, 1, 8);
//		Route r2 = new Route(2, 1, 10);
//		Route r3 = new Route(3, 8, 13);
//		Route r4 = new Route(4, 10, 13);
//		Route r5 = new Route(5, 1, 4);
//		Route r6 = new Route(6, 1, 6);
//		Route r7 = new Route(7, 4, 13);
//		Route r8 = new Route(8, 6, 13);
//		Route r9 = new Route(9, 14, 9);
//		Route r10 = new Route(10, 14, 7);
//		Route r11 = new Route(11, 9, 2);
//		Route r12 = new Route(12, 7, 2);
//		Route r13 = new Route(13, 14, 5);
//		Route r14 = new Route(14, 14, 3);
//		Route r15 = new Route(15, 5, 2);
//		Route r16 = new Route(16, 3, 2);
//		Route r17 = new Route(17, 4, 6);
//		Route r18 = new Route(18, 5, 3);
//		Route r19 = new Route(19, 8, 10);
//		Route r20 = new Route(20, 9, 7);
//		Route r21 = new Route(21, 1, 12);
//		Route r22 = new Route(22, 8, 12);
//		Route r23 = new Route(23, 10, 12);
//		Route r24 = new Route(24, 12, 13);
////		Route r25 = new Route(25, 14, 11);
//		Route r26 = new Route(26, 11, 9);
//		Route r27 = new Route(27, 11, 7);
//		Route r28 = new Route(28, 11, 2);

		//Route errorRoute1 = new Route(21, 5, 12);
		
		//Extended
		
		Route r1 = new Route(1, 1, 8);
		Route r2 = new Route(2, 1, 10);
		Route r3 = new Route(3, 8, 11);
		Route r4 = new Route(4, 10, 11);
		Route r5 = new Route(5, 1, 4);
		Route r6 = new Route(6, 1, 6);
		Route r7 = new Route(7, 4, 11);
		Route r8 = new Route(8, 6, 11);
		Route r9 = new Route(9, 12, 9);
		Route r10 = new Route(10, 12, 7);
		Route r11 = new Route(11, 9, 2);
		Route r12 = new Route(12, 7, 2);
		Route r13 = new Route(13, 12, 5);
		Route r14 = new Route(14, 12, 3);
		Route r15 = new Route(15, 5, 2);
		Route r16 = new Route(16, 3, 2);
		Route r17 = new Route(17, 4, 6);
		Route r18 = new Route(18, 5, 3);
		Route r19 = new Route(19, 8, 10);
		Route r20 = new Route(20, 9, 7);
		
		List<Route> j1 = new ArrayList<Route>();
		
//		//Jack
//		
//		Route r1 = new Route(1, 1, 4);
//		Route r2 = new Route(2, 1, 6);
//		Route r3 = new Route(3, 6, 7);
//		Route r4 = new Route(4, 4, 7);
//		//Route r5 = new Route(5, 7, 10);
//		Route r6 = new Route(6, 7, 12);
//		Route r7 = new Route(7, 10, 13);
//		Route r8 = new Route(8, 12, 13);
//		Route r9 = new Route(9, 14, 9);
//		//Route r10 = new Route(10, 14, 11);
//		//Route r11 = new Route(11, 9, 8);
//		//Route r12 = new Route(12, 11, 8);
//		Route r13 = new Route(13, 8, 3);
//		Route r14 = new Route(14, 8, 5);
//		Route r15 = new Route(15, 5, 2);
//		Route r16 = new Route(16, 3, 2);
		
		//j1.add(errorRoute1);
		
		j1.add(r1);
//		j1.add(r2);
		j1.add(r3);
//		j1.add(r4);
//		j1.add(r5);
//		j1.add(r6);
//		j1.add(r7);
//		j1.add(r8);
//		j1.add(r9);
//		j1.add(r10);
//		j1.add(r11);
//		j1.add(r12);
//		j1.add(r13);
//		j1.add(r14);
//		j1.add(r15);
//		j1.add(r16);
//		j1.add(r17);
//		j1.add(r18);
//		j1.add(r19);
//		j1.add(r20);
//		j1.add(r21);
//		j1.add(r22);
//		j1.add(r23);
//		j1.add(r24);
////		j1.add(r25);
//		j1.add(r26);
//		j1.add(r27);
//		j1.add(r28);
		
//		j1.add(r1);
//		j1.add(r2);
//		j1.add(r3);
//		j1.add(r4);
//		j1.add(r5);;
//		j1.add(r6);
//		j1.add(r7);
//		j1.add(r8);
//		
		InterlockingTableGenerator t = new InterlockingTableGenerator();
		t.printTable();
		
		for (int i = 0; i < reader.getStoreBlock().size(); i++) {
			
			System.out.println(reader.getStoreBlock().get(i).blockString() + "\n");

		}
		
		
		for (int l = 0; l < reader.getStorePoint().size(); l++) {
			
			System.out.println(reader.getStorePoint().get(l).pointString() + "\n");

		}
		
	}

}
