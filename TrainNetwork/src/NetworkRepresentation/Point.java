package NetworkRepresentation;

import java.util.List;

public class Point extends Section {
	int pId;
	boolean plus;
	Point pair;
	
	public Point(int sId, int pId) {
		super(sId);
		this.setpId(pId);
		
//		if(pId > 0)
//		{
//			if ((pId % 2) == 0)
//			{
//				setPair(pair);
//				
//			}
//		}
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

	public Point getPair() {
		return pair;
	}
	public void setPair(Point pair) {
		this.pair = pair;
	}
	public void addToGlobalList(List<Point> pointList)
	{
		pointList.add(this);
	}
	
	public boolean pointFacingUp()
	{
		int up = 0;
		
		for (int i=0; i < this.getNeighList().size(); i++)
		{
			if (this.getNeighList().get(i).getsId() > this.getsId())
			{
				up++;
			}
		}
		
		if (up == 2)
		{
			return true;
		}
		
		else 
		{
			return false;
		}
	}
	
	public boolean pointPair(Point p2)
	{
		if ((this.pointFacingUp() && !(p2.pointFacingUp())) || (!(this.pointFacingUp()) && p2.pointFacingUp())){ return true; }
		else return false;
	}
	
	public boolean pointFacingRouteDirection(Route r)
	{
		if (pointFacingUp() == (r.isUp()))
		{
			return true;
		}
		return false;
	}
		
	public String toString()
	{
		return "p" + pId;
	}

}
