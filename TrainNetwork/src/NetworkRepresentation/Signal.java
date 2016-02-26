package NetworkRepresentation;

public class Signal {
	int sigId;
	boolean up;

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

}
