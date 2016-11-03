import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteToFile {
	
	public WriteToFile(ArrayList<String> characterResults) {
		boolean append = true;
		boolean autoFlush = true;
		String charset = "UTF-8";
		String filePath = "src/foo.txt";
		try{
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file, append);
			OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw, autoFlush);
			if(!file.exists()){
				if (file.createNewFile()) {
					for(String input: characterResults){
						pw.println(input);
					}
		        }//end createNewFile
			}//end if file NOT exists
			if(file.canWrite()){
				for(String input: characterResults){
					pw.println(input);
				}
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}

/*
protected static void saveCommandsToFile() throws FileNotFoundException, IOException {
    File file = new File("resources/commands.txt");
    System.out.println("Absolute path:" + file.getAbsolutePath());
    if (!file.exists()) {
        if (file.createNewFile()) {
            PrintWriter out = new PrintWriter(file);
            out.println("hi");
            out.close();
        }
    }
}
*/