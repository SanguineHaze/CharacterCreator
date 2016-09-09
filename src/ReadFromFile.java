import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;


public class ReadFromFile {

    private String path;

    public ReadFromFile(String filePath) {
        path = filePath;
    }

    public ArrayList<String> OpenFile() throws IOException {
        ArrayList<String> output = new ArrayList<String>();
        File fr = new File(path);
        Scanner textReader = new Scanner(fr);

        while(textReader.hasNext()){
            output.add(textReader.nextLine());
        }
        textReader.close();
        return output;
    }

	
	/*
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
		
	}*/
}
