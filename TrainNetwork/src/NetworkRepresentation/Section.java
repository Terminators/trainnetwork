package NetworkRepresentation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jack Chandler0
 * @project TrainNetwork
 * @version 1
 */

public abstract class Section {
	private List<Section> neighList = new ArrayList<Section>();

	public List<Section> getNeighList()
	{
		return neighList;
	}

	public void addNeigh(Section section)
	{
		neighList.add(section);
	}

	public void addEdge(Section section)
	{
		neighList.add(section);
		section.addNeigh(this);
	}

	// From the data from edges + vertices in the GraphML, we can add neighbours
	// to a particluar node
	// if edge = (a, b) then a.addNeigh(b) and b.addNeigh(a). This is how we
	// generate the graph.
	// The method also needs to set all the fields of Section
	public void generateNodeDetails()
	{

	}
}
