package NetworkRepresentation;

import java.util.HashMap;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

/**
 * 
 * @author Jack Chandler, Ryan Crosby
 * @project TrainNetwork
 * @version 1
 */

public class Block extends Section {
	private int bId;

	@NotNull
	private boolean plus;

	@Length(min = 2, max = 2)
	private String leftNeighbour;
	@Length(min = 2, max = 2)
	private String rightNeighbour;

	private Signal signalUp;
	private Signal signalDown;

	private Point closestPointUp;
	private Point closestPointDown;

	private static final HashMap<String, Block> Blocks = new HashMap<String, Block>();

	private Block(@NotNull @Length(min = 2, max = 2) String bId, String leftNeighbour,
			@NotNull @Length(min = 2, max = 2) String rightNeighbour, Boolean plus, @NotNull @Length(min = 2, max = 2) String signalDown,
			@NotNull @Length(min = 2, max = 2) String signalUp)
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
				+ "\n signalUp: " + ifNull(signalUp) + "- " + ifNullState(signalUp) + "\n signalDown: " + ifNull(signalDown) + "- " + ifNullState((signalDown));

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
	
//	public boolean hasSignalInDirection(boolean routeUp)
//	{
//		if (routeUp == true && signalUp != null)
//		{
//			return true;
//		}
//		
//		
//		if (signalUp != null)
//		{
//			return signalUp;
//
//		}
//		else return signalDown;
//		
//	}
	
//	public boolean insidePairOfPoints()
//	{
//		if ((this.getNeighList().get(0) instanceof Point) && (this.getNeighList().get(1) instanceof Point))
//		{
//			if ()
//		}
//	}

}
