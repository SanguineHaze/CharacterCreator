package persistence.method;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSystemPersistenceMethod implements PersistenceMethod {

    private String _filesDirectory;

    public FileSystemPersistenceMethod() {
        _filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator).replace("%20", " ");
    }

    @Override
    public ArrayList<String> GetData(String identifier) {
        ArrayList<String> readOut = new ArrayList<>();
        try {
            File path = new File(_filesDirectory + "sourceData" + File.separator + identifier + ".txt");


            Scanner textReader = new Scanner(path);
            while(textReader.hasNextLine()){
                String line = textReader.nextLine();
                readOut.add(line);
            }
            textReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return readOut;
    }
}
