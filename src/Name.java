import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Name {
	String sex = "";
	boolean chosenSex;
	ArrayList<String> nameList = new ArrayList<String>();
	
	public Name(){
		sex = "";
		generateSex();
		loadNameList();
		//placeholder for generateName();
		//DEBUG TOOL - Check to see what sex is being assigned
		System.out.println(sex);
	}//end Name()
	
	//First, lets select a sex for our new character
	private void generateSex() {
		Random randomSex = new Random();
		chosenSex = randomSex.nextBoolean();
		
		//DEBUG TOOL - Check to see what true/false value is assigned to chosenSex
		//System.out.println(chosenSex);
		
		if(chosenSex == true){
			sex = "Male";
		} else {
			sex = "Female";
		}//end If statement		
	}//end Sex()
	
	public String getSex() {
		return sex;
	}
	
	//Second, generate a list of names
	private void loadNameList(){
		String nameListTargetFile = "C:/Users/dylanc/workspace/CharacterCreator/src/test3.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(nameListTargetFile);
			nameList = file.OpenFile();
			
			//DEBUG TOOL: Check to see that the list is being created
		    /*
		    System.out.println("Name List:");
		    for(String out: nameList){
		        System.out.println(out);
		    }
		    */
			
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}//end Try/Catch
	}//end loadNameList()
	
}