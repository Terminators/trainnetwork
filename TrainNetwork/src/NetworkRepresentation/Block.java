package NetworkRepresentation;

public class Block extends Section {
	int bId;
	boolean plus;
	
	Signal signalUp;
	Signal signalDown;
	
	Point closestPointUp;
	Point closestPointDown;
	
	public Block(int sId, int bId, Signal signalUp, Signal signalDown) {
		super(sId);
		this.setbId(bId);
	}
	
	public Block(int sId, int bId) {
		super(sId);
		this.setbId(bId);
		
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
	
	public void addSignals(Signal signalUp, Signal signalDown)
	{
		addSignalUp(signalUp);
		addSignalDown(signalDown);
	}
	
	public void addSignalUp(Signal signalUp)
	{
		this.signalUp = signalUp;
	}
	
	public void addSignalDown(Signal signalDown)
	{
		this.signalDown = signalDown;
	}
}
