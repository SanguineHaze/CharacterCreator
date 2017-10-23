package sanguinehaze.charactercreator;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriteToFile {
	
	String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()  + File.separator).replace("%20", " ");
	
	private DateFormat dateFormat;
	Date date = new Date();
	String theDate;
	private String filePath;
	
	public WriteToFile(List<String> data, String path) {
		preWrite(path);
		write(data);
	}
	
	public String getWTFLocation() {
		String location = "Created File: " + filePath + theDate + ".txt";
		return location;
	}

	public void preWrite(String path){
		dateFormat = new SimpleDateFormat("_MM_dd_yyyy_HH_mm_ss");
		theDate = dateFormat.format(date);
		if(path.toLowerCase().equals("characterresults")){
			this.filePath = filesDirectory + "NPCResults" + File.separator + "CharacterResults";
		} else if(path.toLowerCase().equals("error")){
			this.filePath = filesDirectory + "ErrorLog" + File.separator + "Error_Log";
		}
	}

	public void write(List<String> data){
		try{
			PrintWriter pw = new PrintWriter(filePath + theDate + ".txt");
			for(String input: data){
				pw.println(input);
			}
			//System.out.println("Created File: " + filePath + theDate  + ".txt" ); //Debug Line
			pw.close();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}