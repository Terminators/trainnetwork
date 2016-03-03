package fileReading;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * 
 * @author Ryan Crosby
 * @project Group Work
 * @version 1
 */
public class GMLReader {
	ArrayList<Block> storeBlock = new ArrayList<Block>(); // Store a list of all the blocks from the file
	ArrayList<Point> storePoint = new ArrayList<Point>(); // Store a list of all the point from the file

	public void readFile(String path) throws IOException {

		BufferedReader input = new BufferedReader(new FileReader(path));

		while (input.readLine() != null) {
			String line = input.readLine();

			if (line == null) {
				break;
			}
			
			if (line.equals("Block")) {

		
				String name = input.readLine();
				String status = input.readLine();
				String left = input.readLine();
				String right = input.readLine();
				String plus  = input.readLine(); 
				Boolean up = true;
				if(!plus.equals("up")){
				up = false;	
				}
				
				String signalOne = input.readLine();
				String signalTwo = input.readLine();
				storeBlock.add(Block.getInstance(name, status, left, right, up, signalOne, signalTwo));

			}
			if (line.equals("Point")) {
				
				String name = input.readLine();
				String status = input.readLine();
				String left = input.readLine();
				String right = input.readLine();
				String top = input.readLine();
				String plus  = input.readLine(); 
				Boolean up = true;
				if(!plus.equals("up")){
				up = false;	
				}
				
				String signalOne = input.readLine();
				String signalTwo = input.readLine();
				
				storePoint.add(Point.getInstance(name, status, left, right, top, up, signalOne, signalTwo));
				
			}

		}
	}

	public static void main(String[] args) throws IOException {
		GMLReader one = new GMLReader();

		one.readFile("one.txt");

		for (int i = 0; i < one.storeBlock.size(); i++) {
			
			System.out.println(one.storeBlock.get(i).toString() + "\n");

		}
		
		
		for (int l = 0; l < one.storePoint.size(); l++) {
			
			System.out.println(one.storePoint.get(l).toString() + "\n");

		}
	}

}
