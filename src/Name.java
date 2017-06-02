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
	}
	
	public void generateNewNameData(String userSex, String userAge){
		sex = "";
		chosenName = "";
		chosenAge = "";
		generateSex(userSex);
		generateFullName();
		generateFullLastName();
		generateAge(userAge);
	}
	
	private void generateSex() {
		Random randomSex = new Random();
		chosenSex = randomSex.nextBoolean();
		
		if(chosenSex == true){
			sex = "Male";
		} else {
			sex = "Female";
		}
	}
	
	private void generateSex(String userSex){
		if(userSex.equals("Male")){
			sex = userSex;
		} else if(userSex.equals("Female")){
			sex = userSex;
		} else {
			generateSex();
		}
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
	}
	//Full first name
	private void generateFullName(){
		generateBeginning();
		generateMiddle();
		generateEnd();
		String fullNameBuilder = chosenBeginningName + chosenMidName + chosenEndName;

		String fullNameConversion = fullNameBuilder.replaceAll("\\'", "");

		chosenName = fullNameConversion.substring(0,1).toUpperCase() + fullNameConversion.substring(1);		
	}
	//Full last name
	private void generateFullLastName(){
		generateBeginning();
		generateMiddle();
		generateEnd();
		String fullNameBuilder = chosenBeginningName + chosenMidName + chosenEndName;

		String fullNameConversion = fullNameBuilder.replaceAll("\\'", "");

		chosenLastName = fullNameConversion.substring(0,1).toUpperCase() + fullNameConversion.substring(1);
	}
	
	//Age
	public void generateAge(){
		Random randomAge = new Random();
		int index = randomAge.nextInt(ageRange.size());
		chosenAge = ageRange.get(index);
	}
	
	public void generateAge(String userAge){		
		Random randomAge = new Random();
		if(ageRange.contains(userAge)){
			chosenAge = userAge;
		} else if(userAge.equals("Any Age")){
			int index = randomAge.nextInt(ageRange.size());
			chosenAge = ageRange.get(index);
		} else {
			int index = randomAge.nextInt(ageRange.size());
			chosenAge = ageRange.get(index);
		}
	}

}