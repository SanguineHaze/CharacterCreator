import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteToFile {
	
	String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()  + File.separator).replace("%20", " ");
	
	private DateFormat dateFormat;
	Date date = new Date();
	String theDate;
	private String filePath = filesDirectory + "NPCResults" + File.separator + "CharacterResults";
	
	public WriteToFile(ArrayList<String> characterResults) {
		dateFormat = new SimpleDateFormat("_MM_dd_yyyy_HH_mm_ss");
		theDate = dateFormat.format(date);
		String filePath = filesDirectory + "NPCResults" + File.separator + "CharacterResults";
		
		try{
            PrintWriter pw = new PrintWriter(filePath + theDate + ".txt");
            for(String input: characterResults){
                pw.println(input);
            }
            System.out.println("Created File: " + filePath + theDate  + ".txt" );
            pw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
	}
	
	public String getWTFLocation() {
		String location = "Created File: " + filePath + theDate  + ".txt";
		return location;
	}
	
}