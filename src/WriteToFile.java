import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteToFile {
	private DateFormat dateFormat;
	Date date = new Date();

	private Date dateFormat(Date date) {
		dateFormat = new SimpleDateFormat("MM_dd_yyyy");
		return this.date;
	}
	
	public WriteToFile(ArrayList<String> characterResults) {
		date = dateFormat(date);
		String filePath = "src/foo";
		
		try{
			File file = new File(filePath);
			PrintWriter pw = new PrintWriter(filePath + date + ".txt");
			if(!file.exists()){
				if (file.createNewFile()) {
					for(String input: characterResults){
						pw.println(input);
					}
					System.out.println("File Created!");
		        }//end createNewFile
			}//end if file NOT exists
			if(file.exists()){				
				for(String input: characterResults){
					pw.println(input);
				}
				System.out.println("File Created!");
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