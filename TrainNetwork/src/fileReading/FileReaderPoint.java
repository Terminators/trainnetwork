package fileReading;

import java.util.HashMap;

public class FileReaderPoint {
	
	private final String name;
	private final String status;
	private final String leftNeighbour;
	private final String rightNeighbour;
	private final String topNeighbour;
	private final Boolean plus;	
	private final String signalOne;
	private final String signalTwo;
	
		private static final HashMap<String, FileReaderPoint> FileReaderPoints = new HashMap<String, FileReaderPoint>();
		
		
		private FileReaderPoint(String name, String status, String leftNeighbour, String rightNeighbour, String topNeighbour, Boolean plus, String signalOne, String signalTwo){
			this.name=name;
			this.status=status; 
			this.leftNeighbour =leftNeighbour;
			this.rightNeighbour = rightNeighbour; 
			this.plus=plus;
			this.topNeighbour = topNeighbour;
			this.signalOne = signalOne; 
			this.signalTwo = signalTwo;
		}
		
		public static FileReaderPoint getInstance(String name, String status, String leftNeighbour, String rightNeighbour, String topNeighbour, Boolean plus, String signalOne, String signalTwo){
			final String key = name; 
			if(!FileReaderPoints.containsKey(key)){
			FileReaderPoints.put(key, new FileReaderPoint(name, status, leftNeighbour, rightNeighbour, topNeighbour, plus, signalOne, signalTwo));	
				
			}
			return FileReaderPoints.get(key);
			
		}
		
		public String toString() {
			return "Point:" + name + " \n Status: " + status + " \n leftNeighbour: " + leftNeighbour + " \n rightNeightbour: " + rightNeighbour +  " \n topNeighbour: "+ topNeighbour+"\n Plus: " + plus + "\n signalOne " + signalOne + "\n signalTwo "+ signalTwo;

		}
	}

