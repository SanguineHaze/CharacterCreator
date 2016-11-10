import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Name {
	String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\").replace("%20", " ");
	
	String sex = "";
	boolean chosenSex;
	ArrayList<String> nameList = new ArrayList<String>();
	ArrayList<String> beginningName = new ArrayList<String>();
	ArrayList<String> middleName = new ArrayList<String>();
	ArrayList<String> endName = new ArrayList<String>();
	ArrayList<String> ageRange = new ArrayList<String>();
	public String chosenBeginningName = "";
	public String chosenMidName = "";
	public String chosenEndName = "";
	public String lineNameBegin;
	public String lineValueBegin;
	public String lineNameMid;
	public String lineValueMid;
	public String lineNameEnd;
	public String lineValueEnd;
	public String chosenName;
	public String chosenAge;
	
	public Name(){
		sex = "";
		chosenName = "";
		chosenAge = "";
		loadNameList();
		generateSex();
		generateNameLists();
		generateBeginning();
		generateMiddle();
		generateEnd();
		generateFullName();
		generateAge();
	}//end Name()
	
	public void generateNewNameData(){
		sex = "";
		chosenName = "";
		chosenAge = "";
		generateSex();
		generateNameLists();
		generateBeginning();
		generateMiddle();
		generateEnd();
		generateFullName();
		generateAge();
	}
	
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
		//DEBUG TOOL - Check to see what sex is being assigned
		//System.out.println(sex);
	}//end Sex()
	
	public String getSex() {
		return sex;
	}
	
	//Second, generate a list of names
	private void loadNameList(){
		//The master list containing all Name options
		String nameListTargetFile = filesDirectory + "sourceData\\NameDefault.txt"; //path to the file on local environment.
		//TODO: Expand this section to have different files for the different races. Should be able to if/else if this, the same as subrace.
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
	
	public void generateNameLists(){
		
		for(String entry: nameList){
			if(entry.contains("BEGINNING:")){
				beginningName.add(entry);
			} else if(entry.contains("MIDDLE:")) {
				middleName.add(entry);
			} else if(entry.contains("END:")) {
				endName.add(entry);
			}//end if

		}//end for
		
		//DEBUG TOOL: Check to see what's made it to the lists
		/*
		System.out.println("Sub-Race List:");
		for(String out: beginningName){
			System.out.println(out);
		}
		for(String out: middleName){
			System.out.println(out);
		}
		for(String out: endName){
			System.out.println(out);
		}		
		*/
	}//end generateNameList()
	
	//BEGIN NAME!
	private void generateBeginning() {		
		Random randomNameBegin = new Random();
		int index = randomNameBegin.nextInt(beginningName.size());
		chosenBeginningName = beginningName.get(index);
		String line = chosenBeginningName;
		String[] lineContents = line.split(":");
		lineNameBegin = lineContents[0];
		lineValueBegin = lineContents[1];
		chosenBeginningName = lineContents[1];
		//DEBUG TOOL
		//System.out.println(chosenBeginningName);
	}//end generateBeginning()
	
	//CONTINUE NAME!	
	private void generateMiddle(){
		Random randomNameMid = new Random();
		int index = randomNameMid.nextInt(middleName.size());
		chosenMidName = middleName.get(index);
		String line = chosenMidName;
		String[] lineContents = line.split(":");
		lineNameMid = lineContents[0];
		lineValueMid = lineContents[1];
		chosenMidName = lineContents[1];
		
		//DEBUG TOOL
		//System.out.println(chosenMidName);		
	}//end generateMiddle()
	
	//END NAME!
	private void generateEnd(){
		Random randomNameEnd = new Random();
		int index = randomNameEnd.nextInt(endName.size());
		chosenEndName = endName.get(index);
		String line = chosenEndName;
		String[] lineContents = line.split(":");
		lineNameEnd = lineContents[0];
		lineValueEnd = lineContents[1];
		chosenEndName = lineContents[1];
		
		//DEBUG TOOL
		//System.out.println(chosenEndName);
	}//end generateEnd()
	
	private void generateFullName(){
		String fullNameBuilder = chosenBeginningName + chosenMidName + chosenEndName;

		String fullNameConversion = fullNameBuilder.replaceAll("\\'", "");

		chosenName = fullNameConversion.substring(0,1).toUpperCase() + fullNameConversion.substring(1);
		
		//DEBUG TOOL - check to see the built name
		//System.out.println("Built Name: " + fullNameBuilder);
		
		//DEBUG TOOL - check to see the converted name
		//System.out.println("Converted Name: " + fullNameConversion);
		
		//DEBUG TOOL - check to see the actual full name
		//System.out.println("Final Name: " + fullName);
		
	}//end generateFullName
	
	public void generateAge(){
		ageRange.add("Child");
		ageRange.add("Young Adult");
		ageRange.add("Adult");
		ageRange.add("Old");
		ageRange.add("Very Old");
		
		Random randomAge = new Random();
		int index = randomAge.nextInt(ageRange.size());
		chosenAge = ageRange.get(index);
	}//end generateAge()

}//end Class