package NetworkRepresentation;

import java.util.List;

public class Point extends Section {
	int pId;
	boolean plus;
	
	public Point(int sId, int pId) {
		super(sId);
		this.setpId(pId);
	}
	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
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
	
	public void addToGlobalList(List<Point> pointList)
	{
		pointList.add(this);
	}

}
