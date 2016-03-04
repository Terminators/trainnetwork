package NetworkRepresentation;

import java.util.HashMap;

public class Block extends Section {
	private int bId;
	private boolean plus;
	
	private String leftNeighbour;
	private String rightNeighbour;
	
	private Signal signalUp;
	private Signal signalDown;
	
	private Point closestPointUp;
	private Point closestPointDown;
	
	private static final HashMap<String, Block> Blocks = new HashMap<String, Block>();
	
	private Block(String bId, String leftNeighbour, String rightNeighbour, Boolean plus, String signalDown, String signalUp) {
		super();
		this.bId = Integer.parseInt(bId.substring(1).trim());
		
		this.leftNeighbour = leftNeighbour.trim();
		this.rightNeighbour = rightNeighbour.trim();
		
		this.plus = plus;
				
		if (!signalUp.trim().equals("NA"))
		{
			this.signalUp = new Signal(Integer.parseInt(signalUp.substring(1).trim()), true);
		}
		else {this.signalUp = null;}

		
		if (!signalDown.trim().equals("NA"))
		{
			this.signalDown = new Signal(Integer.parseInt(signalDown.substring(1).trim()), false);
		}
		else {this.signalDown = null;}

	}

	public static Block getInstance(String name, String leftNeighbour, String rightNeighbour,
			Boolean plus, String signalOne, String signalTwo) {
		final String key = name;
		if (!Blocks.containsKey(key)) {
			Blocks.put(key, new Block(name, leftNeighbour, rightNeighbour, plus, signalOne, signalTwo));

		}
		return Blocks.get(key);

	}

	//public String blockString() {
		//return "BLOCK:" + bId + " \n leftNeighbour: " + leftNeighbour + " \n rightNeightbour: " + rightNeighbour + "\n Plus: " + plus + "\n signalUp: " + signalUp.toString() + "\n signalDown: "+ signalDown.toString();
		//return "BLOCK:" + bId + " \n leftNeighbour: " + ifNull(leftNeighbour) + " \n rightNeightbour: " + ifNull(rightNeighbour) + "\n Plus: " + plus + "\n signalUp: " + ifNull(signalUp.toString()) + "\n signalDown: "+ ifNull(signalDown.toString());

	//}

	public Signal getSignalUp() {
		return signalUp;
	}
	
	public Signal getSignalDown() {
		return signalDown;
	}

	public boolean isPlus() {
		return plus;
	}

	public void setPlus() {
		this.plus = true;
	}
	
	public void setMinus() {
		this.plus = false;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public Point getClosestPointUp() {
		return closestPointUp;
	}

	public void setClosestPointUp(Point closestPointUp) {
		this.closestPointUp = closestPointUp;
	}

	public Point getClosestPointDown() {
		return closestPointDown;
	}

	public void setClosestPointDown(Point closestPointDown) {
		this.closestPointDown = closestPointDown;
	}
	
	public void addSignals(Signal signalDown, Signal signalUp)
	{
		addSignalUp(signalUp);
		addSignalDown(signalDown);
	}
	
	public void addSignalUp(Signal signalUp)
	{
		this.signalUp = signalUp;
		signalUp.setOwner(this);
	}
	
	public void addSignalDown(Signal signalDown)
	{
		this.signalDown = signalDown;
		signalDown.setOwner(this);
	}

	public boolean hasOneNeighbour() {
		if (this.getNeighList().size() == 1){return true;}
		
		else return false;
	}
	
	public String getLeftNeighbour() {
		return leftNeighbour;
	}

	public String getRightNeighbour() {
		return rightNeighbour;
	}

	public String toString()
	{
		return "b" + bId;
	}
	
	public String ifNull(String s)
	{
		if (s.equals("NA"))
		{
			return "NA";
		}
		
		else return s;
	}

}
