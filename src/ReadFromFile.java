import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {
	
	String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator).replace("%20", " ");
	
    private File path;
    
    public ReadFromFile(File targetFile) {
        path = new File (filesDirectory + "sourceData" + File.separator + targetFile);
    }
    
    public ArrayList<String> OpenFile() throws IOException {
    	ArrayList<String> readOut = new ArrayList<String>();
        Scanner textReader = new Scanner(path);
        while(textReader.hasNextLine()){
            String line = textReader.nextLine();
            readOut.add(line);
        }
        textReader.close();
        return readOut;
    }
}