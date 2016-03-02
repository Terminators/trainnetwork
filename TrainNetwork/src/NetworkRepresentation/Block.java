package NetworkRepresentation;

public class Block extends Section {
	int bId;
	boolean plus;
	
	Signal signalUp;
	Signal signalDown;
	
	Point closestPointUp;
	Point closestPointDown;
	
	public Block(int sId, int bId, boolean plus, Signal signalUp, Signal signalDown) {
		super(sId);
		this.setbId(bId);
		this.plus = plus;
		this.signalUp = signalUp;
		this.signalDown = signalDown;

	}
	
	public Block(int sId, int bId, boolean plus) {
		super(sId);
		this.setbId(bId);
		this.plus = plus;

	}	

	public Signal getSignalUp() {
		return signalUp;
	}

	public void setSignalUp(Signal signalUp) {
		this.signalUp = signalUp;
	}
	
	public Signal getSignalDown() {
		return signalDown;
	}

	public void setSignalDown(Signal signalDown) {
		this.signalDown = signalDown;
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
	
	public String toString()
	{
		return "b" + bId;
	}

	public boolean hasOneNeighbour() {
		if (this.getNeighList().size() == 1){return true;}
		
		else return false;
	}
}
