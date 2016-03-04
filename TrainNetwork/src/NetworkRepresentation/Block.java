package NetworkRepresentation;

import java.util.HashMap;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

public class Block extends Section {
	private int bId;

	@NotNull private boolean plus;

	@NotNull @Length(min=2,max=2) private String leftNeighbour;
	private String rightNeighbour;

	private Signal signalUp;
	private Signal signalDown;

	private Point closestPointUp;
	private Point closestPointDown;

	private static final HashMap<String, Block> Blocks = new HashMap<String, Block>();

	private Block(@NotNull @Length(min=2,max=2) String bId, String leftNeighbour,
			@NotNull @Length(min=2,max=2) String rightNeighbour, Boolean plus, @NotNull @Length(min=2,max=2)String signalDown, @NotNull @Length(min=2,max=2)String signalUp)
	{
		super();
		this.bId = Integer.parseInt(bId.substring(1));

		this.leftNeighbour = leftNeighbour;
		this.rightNeighbour = rightNeighbour;

		this.plus = plus;

		if (!signalUp.equals("NA"))
		{
			this.signalUp = new Signal(Integer.parseInt(signalUp.substring(1)), true);
		} else
		{
			this.signalUp = null;
		}

		if (!signalDown.equals("NA"))
		{
			this.signalDown = new Signal(Integer.parseInt(signalDown.substring(1)), false);
		} else
		{
			this.signalDown = null;
		}

	}

	public static Block getInstance(String name, String leftNeighbour, String rightNeighbour, Boolean plus, String signalOne,
			String signalTwo)
	{
		final String key = name;
		if (!Blocks.containsKey(key))
		{
			Blocks.put(key, new Block(name, leftNeighbour, rightNeighbour, plus, signalOne, signalTwo));

		}
		return Blocks.get(key);

	}

	public String blockString()
	{
		return "BLOCK:" + bId + " \n leftNeighbour: " + leftNeighbour + " \n rightNeightbour: " + rightNeighbour + "\n Plus: " + plus
				+ "\n signalUp: " + signalUp.toString() + "\n signalDown: " + signalDown.toString();
//		return "BLOCK:" + bId + " \n leftNeighbour: " + ifNull(leftNeighbour) + " \n rightNeightbour: " + ifNull(rightNeighbour)
//				+ "\n Plus: " + plus + "\n signalUp: " + ifNull(signalUp.toString()) + "\n signalDown: " + ifNull(signalDown.toString());

	}

	public Signal getSignalUp()
	{
		return signalUp;
	}

	public Signal getSignalDown()
	{
		return signalDown;
	}

	public boolean isPlus()
	{
		return plus;
	}

	public int getbId()
	{
		return bId;
	}

	public Point getClosestPointUp()
	{
		return closestPointUp;
	}

	public void setClosestPointUp(Point closestPointUp)
	{
		this.closestPointUp = closestPointUp;
	}

	public Point getClosestPointDown()
	{
		return closestPointDown;
	}

	public void setClosestPointDown(Point closestPointDown)
	{
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

	public boolean hasOneNeighbour()
	{
		if (this.getNeighList().size() == 1)
		{
			return true;
		}

		else
			return false;
	}

	public String getLeftNeighbour()
	{
		return leftNeighbour;
	}

	public String getRightNeighbour()
	{
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

		else
			return s;
	}

}
