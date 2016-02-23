package FileImport;

import java.io.File;

import org.jgrapht.Graph;

import dk.aaue.sna.ext.graphml.GraphMLImporter;

public class test {

	public static void main(String[] args) {
		File exampleGraphML = new File ("D:/MComp/Group Project/TrainNetwork/TrainNetwork/src/FileImport/exampleGraphML");
		GraphMLImporter importer = GraphMLImporter.createFromFile(exampleGraphML);
		
		
		
	}

}
