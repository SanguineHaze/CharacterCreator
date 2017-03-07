import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Name {
	
	String sex = "";
	boolean chosenSex;	
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
	public String chosenLastName;
	private ArrayList<String> beginningName = GenerateSourceData.beginningName;
	private ArrayList<String> middleName = GenerateSourceData.middleName;
	private ArrayList<String> endName = GenerateSourceData.endName;
	private ArrayList<String> ageRange = GenerateSourceData.ageRange;
	
	public Name(){
		sex = "";
		chosenName = "";
		chosenAge = "";
		generateSex();
		generateFullName();
		generateFullLastName();
		generateAge();
	}//end Name()
	
	public void generateNewNameData(String userSex, String userAge){
		sex = "";
		chosenName = "";
		chosenAge = "";
		generateSex(userSex);
		generateFullName();
		generateFullLastName();
		generateAge(userAge);
		//DEBUG TOOL
		//System.out.println("GenerateNewNameData - UserSex: " + userSex);
	}
	
	private void generateSex() {
		Random randomSex = new Random();
		chosenSex = randomSex.nextBoolean();
		
		//DEBUG TOOL - Check to see what true/false value is assigned to chosenSex
		//System.out.println(chosenSex);
		
		if(chosenSex == true){
			sex = "Male";
		} else {
			sex = "Female";
		}
		//DEBUG TOOL - Check to see what sex is being assigned
		//System.out.println(sex);
	}
	
	private void generateSex(String userSex){
		if(userSex.equals("Male")){
			sex = userSex;
		} else if(userSex.equals("Female")){
			sex = userSex;
		} else {
			generateSex();
		}
		//DEBUG TOOL
		//System.out.println("GenerateSex - UserSex: " + userSex + ". ChosenSex: " + chosenSex);
	}
		
	
	
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
	}
	
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
	}
	
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
	}
	
	private void generateFullName(){
		generateBeginning();
		generateMiddle();
		generateEnd();
		String fullNameBuilder = chosenBeginningName + chosenMidName + chosenEndName;

		String fullNameConversion = fullNameBuilder.replaceAll("\\'", "");

		chosenName = fullNameConversion.substring(0,1).toUpperCase() + fullNameConversion.substring(1);
		
		//DEBUG TOOL - check to see the built name
		//System.out.println("Built Name: " + fullNameBuilder);
		
		//DEBUG TOOL - check to see the converted name
		//System.out.println("Converted Name: " + fullNameConversion);
		
		//DEBUG TOOL - check to see the actual full name
		//System.out.println("Final Name: " + chosenName);
		
	}
	
	private void generateFullLastName(){
		generateBeginning();
		generateMiddle();
		generateEnd();
		String fullNameBuilder = chosenBeginningName + chosenMidName + chosenEndName;

		String fullNameConversion = fullNameBuilder.replaceAll("\\'", "");

		chosenLastName = fullNameConversion.substring(0,1).toUpperCase() + fullNameConversion.substring(1);
		
		//DEBUG TOOL - check to see the built name
		//System.out.println("Built Name: " + fullNameBuilder);
		
		//DEBUG TOOL - check to see the converted name
		//System.out.println("Converted Name: " + fullNameConversion);
		
		//DEBUG TOOL - check to see the actual last name
		//System.out.println("Final Name: " + chosenLastName);
		
	}
	
	public void generateAge(){
		Random randomAge = new Random();
		int index = randomAge.nextInt(ageRange.size());
		chosenAge = ageRange.get(index);
	}
	
	public void generateAge(String userAge){		
		Random randomAge = new Random();
		if(ageRange.contains(userAge)){
			chosenAge = userAge;
			
			//DEBUG TOOL
			//System.out.println("generateAge 1: " + userAge);
		} else if(userAge.equals("Any Age")){
			int index = randomAge.nextInt(ageRange.size());
			chosenAge = ageRange.get(index);
			
			//DEBUG TOOL
			//System.out.println("generateAge 2 - userAge: " + userAge + " chosenAge: " + chosenAge);
		} else {
			int index = randomAge.nextInt(ageRange.size());
			chosenAge = ageRange.get(index);
			
			//DEBUG TOOL
			//System.out.println("generateAge 3 - userAge: " + userAge + " chosenAge: " + chosenAge);
		}
	}

}