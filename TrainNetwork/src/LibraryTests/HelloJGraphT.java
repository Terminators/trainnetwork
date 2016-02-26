/** ==========================================
 ** JGraphT : a free Java graph-theory library
 ** ==========================================
 **
 ** Project Info:  [http://jgrapht.sourceforge.net/](http://jgrapht.sourceforge.net/)
 ** Project Creator:  Barak Naveh ([http://sourceforge.net/users/barak_naveh)](http://sourceforge.net/users/barak_naveh))
 **
 ** (C) Copyright 2003-2006, by Barak Naveh and Contributors.
 **
 ** This library is free software; you can redistribute it and/or modify it
 ** under the terms of the GNU Lesser General Public License as published by
 ** the Free Software Foundation; either version 2.1 of the License, or
 ** (at your option) any later version.
 **
 ** This library is distributed in the hope that it will be useful, but
 ** WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 ** or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 ** License for more details.
 **
 ** You should have received a copy of the GNU Lesser General Public License
 ** along with this library; if not, write to the Free Software Foundation,
 ** Inc.,
 ** 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 **/
/** &#8212;&#8212;&#8212;&#8212;&#8212;&#8211;
 ** HelloJGraphT.java
 ** &#8212;&#8212;&#8212;&#8212;&#8212;&#8211;
 ** (C) Copyright 2003-2006, by Barak Naveh and Contributors.
 **
 ** Original Author:  Barak Naveh
 ** Contributor(s):   -
 **
 ** $Id: HelloJGraphT.java 504 2006-07-03 02:37:26Z perfecthash $
 **
 ** Changes
 ** &#8212;&#8212;-
 ** 27-Jul-2003 : Initial revision (BN);
 **
 */
package LibraryTests;

import java.io.File;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import dk.aaue.sna.ext.graphml.GraphMLImporter;

/***
 ** A simple introduction to using JGraphT.
 **
 ** @author Barak Naveh
 ** @since Jul 27, 2003
 **/
public final class HelloJGraphT
{

    //~ Constructors &#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;-

    private HelloJGraphT()
    {
    } // ensure non-instantiability.

    //~ Methods &#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;&#8212;

    /***
     ** The starting point for the demo.
     **
     ** @param args ignored.
     */
    public static void main(String [] args)
    {
    	//import example
        DirectedGraph<String, DefaultEdge> exampleGraph = createExampleGraph();
    	
    	
    	
    	//example
        DirectedGraph<Integer, DefaultEdge> exampleGraph = createExampleGraph();
        
        System.out.println(exampleGraph.toString());
    	
        UndirectedGraph<String, DefaultEdge> stringGraph = createStringGraph();

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println(stringGraph.toString());

        // create a graph based on URL objects
        //DirectedGraph<URL, DefaultEdge> hrefGraph = createHrefGraph();

        // note directed edges are printed as: (<v1>,<v2>)
        //System.out.println(hrefGraph.toString());
    }

    /***
     ** Creates a toy directed graph based on URL objects that represents link
     ** structure.
     **
     ** @return a graph based on URL objects.
     **/
    private static DirectedGraph<URL, DefaultEdge> createHrefGraph()
    {
        DirectedGraph<URL, DefaultEdge> g =
            new DefaultDirectedGraph<URL, DefaultEdge>(DefaultEdge.class);

        try {
            URL amazon = new URL("[http://www.amazon.com](http://www.amazon.com)");
            URL yahoo = new URL("[http://www.yahoo.com](http://www.yahoo.com)");
            URL ebay = new URL("[http://www.ebay.com](http://www.ebay.com)");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }

    /***
     ** Create a toy graph based on String objects.
     **
     ** @return a graph based on String objects.
     */
    private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }
    
    private static DirectedGraph<Integer, DefaultEdge> createExampleGraph()
    { 
    	DirectedGraph<Integer, DefaultEdge> g =
             new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
    	
    	int i1 = 1;
    	int i2 = 2;
    	int i3 = 3;
    	int i4 = 4;
    	
    	//add int vertices
    	g.addVertex(i1);
    	g.addVertex(i2);
    	g.addVertex(i3);
    	g.addVertex(i4);
    	
    	// add edges to create a circuit
        g.addEdge(i1, i2);
        g.addEdge(i2, i3);
        g.addEdge(i3, i4);
    	
        return g;
    }
    
    private static DirectedGraph<String, DefaultEdge> importGraphMLTest()
    {
    	ClassBasedVertexFactory<String> stringFactory = new ClassBasedVertexFactory<String>(String.class);
    	
    	File exampleGraphML = new File ("D:/MComp/Group Project/TrainNetwork/TrainNetwork/src/FileImport/exampleGraphML");
        GraphMLImporter<String, DefaultEdge> importer = GraphMLImporter.createFromFile(exampleGraphML);
    	
        DirectedGraph<String, DefaultEdge> graph = null;
        Map<String, String> map = new HashMap<String, String>();
        
        importer.generateGraph(graph, stringFactory, map);
        
        return graph;
    }
    

    
//    @Test
//    public void testKrebsTerror2() throws Exception {
//
//        GraphMLImporter<String, DefaultWeightedEdge> e = GraphMLImporter.createFromClasspathResource("/Krebs-terror2.xml");
//
//        WeightedGraph<String, DefaultWeightedEdge> graph = emptyWeighted();
//        Map<String, String> map = new HashMap<String, String>();
//        e.generateGraph(graph, stringFactory(), map);
//
//        CentralityResult<String> r = new IDMHierarchy(graph).calculate();
//
//        assertNotNull(r);
//    }
//
//    @Test
//    public void testCalculateDirected() throws Exception {
//
//        GraphMLImporter<String, DefaultWeightedEdge> e = GraphMLImporter.createFromClasspathResource("/Krebs-terror2.xml");
//
//        WeightedGraph<String, DefaultWeightedEdge> graph = emptyWeighted();
//        Map<String, String> map = new HashMap<String, String>();
//        e.generateGraph(graph, stringFactory(), map);
//
//        System.out.println("graph    = " + graph);
//
//        DirectedGraph<String, DefaultWeightedEdge> directed = IDMHierarchy.calculateDirected(graph);
//
//        System.out.println("directed = " + directed);
//
//        // save(directed, "src/test/resources/Krebs-terror2-directed.xml");
//    }
}