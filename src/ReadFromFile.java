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

}