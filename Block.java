package fileReading;

import java.util.HashMap;

public class Block {
	private final String name;
	private final String status;
	private final String leftNeighbour;
	private final String rightNeighbour;
	private final Boolean plus;
	private final String signalOne;
	private final String signalTwo;

	private static final HashMap<String, Block> Blocks = new HashMap<String, Block>();

	private Block(String name, String status, String leftNeighbour, String rightNeighbour, Boolean plus, String signalOne, String signalTwo) {
		this.name = name;
		this.status = status;
		this.leftNeighbour = leftNeighbour;
		this.rightNeighbour = rightNeighbour;
		this.plus = plus;
		this.signalOne = signalOne;
		this.signalTwo = signalTwo;
		
	}

	public static Block getInstance(String name, String status, String leftNeighbour, String rightNeighbour,
			Boolean plus, String signalOne, String signalTwo) {
		final String key = name;
		if (!Blocks.containsKey(key)) {
			Blocks.put(key, new Block(name, status, leftNeighbour, rightNeighbour, plus, signalOne, signalTwo));

		}
		return Blocks.get(key);

	}

	public String toString() {
		return "BLOCK:" + name + " \n Status: " + status + " \n leftNeighbour: " + leftNeighbour + " \n rightNeightbour: " + rightNeighbour + "\n Plus: " + plus + "\n signalOne " + signalOne + "\n signalTwo "+ signalTwo;

	}
}
