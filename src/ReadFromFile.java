import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;


public class ReadFromFile {
	
    private File path;
        
    public ReadFromFile(File nameListTargetFile) {
        path = nameListTargetFile;
    }

    public ArrayList<String> OpenFile() throws IOException {
    	ArrayList<String> readOut = new ArrayList<String>();
        //File fr = new File(path);
        Scanner textReader = new Scanner(path);

        while(textReader.hasNextLine()){
        	        	
            String line = textReader.nextLine();
            readOut.add(line);

        }
        textReader.close();
        
        return readOut;
    }
    
}

