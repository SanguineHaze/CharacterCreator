import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Profession {
	
	public String chosenProfession;
	private String line;
	private String lineValue;
	private String lineName;
	private String chosenProfessionList;
	private boolean childSafeListOverride = false;
	private boolean veryOldSafeListOverride = false;
	public String override = ""; //"child" || "very old"
	
	ArrayList<String> professionList = new ArrayList<String>();
	
	public Profession(String chosenAge){
		chosenProfession = "";
		professionListOverride();
		generateProfessionList(chosenAge);
		loadProfessionList();
		generateProfession();
	}//end Profession()
	
	public void professionListOverride(){
		if("child".equals(override)){
			childSafeListOverride = true;
		} else if("very old".equals(override)){
			veryOldSafeListOverride = true;
		}
	}//end professionListOverride()	
	
	private void generateProfessionList(String chosenAge){
		if("Child".equals(chosenAge)){
			Random childRandom = new Random();
			int isEmployed = childRandom.nextInt((10) + 1);
			if(isEmployed < 3) {
				if(childSafeListOverride){ //did we want an unfiltered list?
					chosenProfessionList = "src/Professions.txt";
				} else {
					chosenProfessionList = "src/ProfessionsChild.txt";
				}//end if childSafeListOverride
			} else {
				professionList.add("JOB:None"); //generates a random "null" somewhere... investigate.
			}//end if isEmployed	
		} else if("Young Adult".equals(chosenAge)){
			chosenProfessionList = "src/Professions.txt";
		} else if("Adult".equals(chosenAge)){
			chosenProfessionList = "src/Professions.txt";
		} else if("Old".equals(chosenAge)){
			chosenProfessionList = "src/Professions.txt";
		} else if("Very Old".equals(chosenAge)){
			if(veryOldSafeListOverride){ //did we want an unfiltered list?
				chosenProfessionList = "src/Professions.txt";
			} else {
				chosenProfessionList = "src/Professions.txt";
			}
		} else {
			chosenProfessionList = "src/Professions.txt";
		}
	}//end generateProfessionList()
	
	//Inspiration fodder: http://arcana.wikidot.com/list-of-medieval-european-professions
	//http://www222.pair.com/sjohn/blueroom/demog.htm
	public void loadProfessionList(){
		
		String professionListTargetFile = chosenProfessionList;
		
		try {
			
			ReadFromFile file = new ReadFromFile(professionListTargetFile);
			professionList = file.OpenFile();
			professionList.sort(null); //Sort the list alphabetically.
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//DEBUG TOOL - Show the list
		//System.out.println(professionList);
		
	}//end loadProfessionList
		
	
	public void generateProfession(){

		Random randomProfession = new Random();
		int index = randomProfession.nextInt(professionList.size());
		chosenProfession = professionList.get(index);
		
		String[] lineContents = chosenProfession.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenProfession = lineValue;
		
	}//end generateProfesion()
	
}//end Class - Profession
