import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFromFile {
	
	private String path;
	
	public ReadFromFile(String filePath) {
		path = filePath;
	}
	
	public String[] OpenFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numOfLines = readLines(); //We'll collect this info slightly later!
		String[] textData = new String[numOfLines]; //Create the array for textData
		
		int i;
		for(i=0; i<numOfLines; i++) {
			textData[i] = textReader.readLine(); //populate the array with data
		}
		
		textReader.close(); 
		return textData; // spit out that data		
		
	}
	
	
	//We need to know how many lines to read.
	//Recall that this means we need to call FileReader and FileBuffer to do this.
	//Then, we need to count it, and spit it back into OpenFile() as readLines()
	int readLines() throws IOException {
		FileReader fileToRead = new FileReader(path); 
		BufferedReader bf = new BufferedReader(fileToRead);
		
		String aLine; //we'll use this in our while statement
		
		int numberOfLines = 0;
		
		//while the bufferedReader can still read lines, keep counting!
		while( (aLine = bf.readLine() ) != null) { 
			numberOfLines++;
		}
		bf.close();
		return numberOfLines; //boom! We know how many lines we gotta read dawg!
		
	}
}
