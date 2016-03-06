package NetworkRepresentation;

import java.util.HashMap;
import java.util.List;
import Routes.Route;

/**
 * 
 * @author Jack Chandler, Ryan Crosby
 * @project TrainNetwork
 * @version 1
 */

public class Point extends Section {
	private int pId;
	
	private String neighbour1;
	private String neighbour2;
	private String neighbour3;
	
	private boolean plus;
	private Point pair;
	
	boolean facingUp;
	
	private static final HashMap<String, Point> Point = new HashMap<String, Point>();
	
	private Point(String pId, String neighbour1, String neighbour2, String neighbour3){
		super();
		this.pId = Integer.parseInt(pId.substring(1));
		this.neighbour1 = neighbour1;
		this.neighbour2 = neighbour2; 
		this.neighbour3 = neighbour3;
	}
	
	public static Point getInstance(String pId, String neighbour1, String neighbour2, String neighbour3){
		final String key = pId; 
		if(!Point.containsKey(key)){
			Point.put(key, new Point(pId, neighbour1, neighbour2, neighbour3));	
			
		}
		return Point.get(key);
		
	}
	
	public String pointString() {
		return "POINT:" + pId + " \n neighbour1: " + neighbour1 + " \n neighbour2: " + neighbour2 +  " \n neighbour3: "+ neighbour3+ "\n Plus: " + plus;

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
	
	public void setBothPairs(Point pair)
	{
		this.pair = pair;
		pair.setPair(this);
	}
	
	public String getNeighbour1() {
		return neighbour1;
	}

	public String getNeighbour2() {
		return neighbour2;
	}

	public String getNeighbour3() {
		return neighbour3;
	}

	public void addToGlobalList(List<Point> pointList)
	{
		pointList.add(this);
	}
	
	public boolean pointFacingUp()
	{	
		Block tempBlockNeighbour = (Block) this.getNeighList().get(0);
		
		if (tempBlockNeighbour.getSignalUp() == null)
		{
			return true;
		}
		
		return false;
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
