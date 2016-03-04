package NetworkRepresentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileReading.GMLReader;

public class test {

	public static void main(String[] args) throws InvalidRouteException, IOException {
		
//		Block b1 = new Block(1, 1, true);
//		Block b2 = new Block(2, 2, true);
//		Point p1 = new Point(3, 1);
//		Block b3 = new Block(4, 3, false);
//		Block b4 = new Block(5, 4, true);
//		Point p2 = new Point(6, 2);
//		Block b5 = new Block(7, 5, true);
//		Block b6 = new Block(8, 6, true);
//		
//		Signal s1 = new Signal(1, true);
//		Signal s2 = new Signal(2, false);
//		Signal s3 = new Signal(3, false);
//		Signal s4 = new Signal(4, true);
//		Signal s5 = new Signal(5, false);
//		Signal s6 = new Signal(6, true);
//		Signal s7 = new Signal(7, true);
//		Signal s8 = new Signal(8, false);
//		
//		p1.setPair(p2);
//		p2.setPair(p1);
//
//		//Network
//		b1.addEdge(b2);
//		
//		b2.addEdge(p1);
//		
//		p1.addEdge(b3);
//		p1.addEdge(b4);
//		
//		b3.addEdge(p2);
//		
//		b4.addEdge(p2);
//		
//		p2.addEdge(b5);
//		
//		b5.addEdge(b6);
//		
//		//Add Signals
//		b1.addSignalUp(s1);
//		b2.addSignalDown(s2);
//		b3.addSignals(s3, s4);
//		b4.addSignals(s5, s6);
//		b5.addSignalUp(s7);
//		b6.addSignalDown(s8);
//				
		
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

		r1.printRoute();
		r2.printRoute();
		r3.printRoute();

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
