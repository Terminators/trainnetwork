package NetworkRepresentation;

import java.util.HashMap;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

/**
 * 
 * @author Jack Chandler, Ryan Crosby
 * @project TrainNetwork
 * @version 1
 */

public class Block extends Section {
	@NotNull
	private int bId;

	@NotNull(message = "Must input which part of the track a block is on eg. plus or minus")
	private Boolean plus;

	@Length
	@MatchPattern(pattern = { "[bBpP]\\d|^NA" }, message = "Please input a valid section ie. b1, p3 OR input NA")
	private String leftNeighbour;
	@Length
	@MatchPattern(pattern = { "[bBpP]\\d|^NA" }, message = "Please input a valid section ie. b1, p3 OR input NA")
	private String rightNeighbour;

	private Signal signalUp;
	private Signal signalDown;

	private Point closestPointUp;
	private Point closestPointDown;

	private static final HashMap<String, Block> BlockMap = new HashMap<String, Block>();

	private Block(@NotNull @MatchPattern(pattern = { "[bB]\\d" }) String bId, String leftNeighbour, String rightNeighbour, Boolean plus,
			@NotNull @MatchPattern(pattern = { "[sS]\\d" }) String signalDown,
			@NotNull @MatchPattern(pattern = { "[sS]\\d" }) String signalUp) throws InvalidNetworkException
	{
		super();

		try
		{
			this.bId = Integer.parseInt(bId.substring(1));
		} catch (java.lang.NumberFormatException e1)
		{
			throw new InvalidNetworkException("Block " + this + ": Please input valid block id eg. b1");
		}
		try
		{
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
		} catch (java.lang.NumberFormatException e1)
		{
			throw new InvalidNetworkException("Block " + this + ": Please input valid signal eg. s1");
		}

	}

	public static Block getInstance(String name, String leftNeighbour, String rightNeighbour, Boolean plus, String signalOne,
			String signalTwo) throws InvalidNetworkException
	{
		final String key = name;
		if (!BlockMap.containsKey(key))
		{
			BlockMap.put(key, new Block(name, leftNeighbour, rightNeighbour, plus, signalOne, signalTwo));

		}
		return BlockMap.get(key);

	}

	public Signal getSignalFromSigId(int sigId)
	{
		if (signalUp != null)
		{
			if (sigId == signalUp.getSigId())
			{
				return signalUp;
			}
		}

		if (signalDown != null)
		{
			if (sigId == signalDown.getSigId())
			{
				return signalDown;
			}
		}

		return null;
	}

	public String blockString()
	{
		return "BLOCK:" + bId + " \n leftNeighbour: " + leftNeighbour + " \n rightNeightbour: " + rightNeighbour + "\n Plus: " + plus
				+ "\n signalUp: " + ifNull(signalUp) + "- " + ifNullState(signalUp) + "\n signalDown: " + ifNull(signalDown) + "- "
				+ ifNullState((signalDown));

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

	public String ifNull(Signal s)
	{
		if (s == null)
		{
			return "NA";
		}

		else
			return s.toString();
	}

	public String ifNullState(Signal s)
	{
		if (s == null)
		{
			return "NA";
		}

		else
			return s.state();
	}
	
	public void setSignalsClear()
	{
		if (signalUp != null)
		{
			signalUp.setClear();
		}
		
		if (signalDown != null)
		{
			signalDown.setClear();
		}
	}
}
