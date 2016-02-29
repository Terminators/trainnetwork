package NetworkRepresentation;

public class test {

	public static void main(String[] args) {
		
		Section b1 = new Block(1, 1);
		Section b2 = new Block(2, 2);
		Section p1 = new Point(3, 1);
		Section b3 = new Block(4, 3);
		Section b4 = new Block(5, 4);
		Section p2 = new Point(6, 2);
		Section b5 = new Block(7, 5);
		Section b6 = new Block(8, 6);
		
		Signal s1 = new Signal(1, true);
		Signal s2 = new Signal(1, false);
		Signal s3 = new Signal(1, false);
		Signal s4 = new Signal(1, true);
		Signal s5 = new Signal(1, false);
		Signal s6 = new Signal(1, true);
		Signal s7 = new Signal(1, true);
		Signal s8 = new Signal(1, false);

		//Network
		b1.addEdge(b2);
		
		b2.addEdge(p1);
		
		p1.addEdge(b3);
		p1.addEdge(b4);
		
		b3.addEdge(p2);
		
		b4.addEdge(p2);
		
		p2.addEdge(b5);
		
		b5.addNeigh(b6);
		
		//Add Signals
		((Block)b1).addSignalUp(s1);
		((Block)b2).addSignalDown(s2);
		((Block)b3).addSignals(s3, s4);
		((Block)b4).addSignals(s5, s6);
		((Block)b5).addSignalUp(s7);
		((Block)b6).addSignalDown(s8);

	}

}
