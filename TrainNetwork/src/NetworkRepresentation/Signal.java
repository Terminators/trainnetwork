package NetworkRepresentation;

public class Signal {
	private int sigId;
	private boolean stop = false; 
	private boolean up;
	
	private Block owner;

	public Signal(int sigId, boolean up) {
		this.sigId = sigId;
		this.up = up;
	}

	public int getSigId()
	{
		return sigId;
	}
	
	public void setSigId(int sigId) {
		this.sigId = sigId;
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp() {
		this.up = true;
	}
	
	public void setDown() {
		this.up = false;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop() {
		this.stop = true;
	}
	
	public void setClear() {
		this.stop = false;
	}
	
	public String toString()
	{
		return "sig" + sigId; 
	}

	public Block getOwner() {
		return owner;
	}

	public void setOwner(Block owner) {
		this.owner = owner;
	}

}
