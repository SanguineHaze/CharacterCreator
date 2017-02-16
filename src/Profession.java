import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Profession {
		
	public String chosenProfession;
	private String lineValue;
	private String lineName;
	private File chosenProfessionList;

	ArrayList<String> professionList = new ArrayList<String>();
	static ArrayList<String> professionStaticList = new ArrayList<String>();
	static ArrayList<String> professionStaticList2 = new ArrayList<String>();
	
	public Profession(String chosenAge){
		chosenProfession = "";		
		generateProfessionList(chosenAge);
		loadProfessionList();
		generateProfession(chosenAge);
	}
	
	public void setChosenProfession(String chosenProfession) {
		this.chosenProfession = chosenProfession;
	}	
	
	public void generateNewProfession(String chosenAge){
		chosenProfession = "";
		generateProfessionList(chosenAge);
		generateProfession(chosenAge);
	}
	
	private void generateProfessionList(String chosenAge){
		//TODO: Fix the child check. The way we generate this skips over age verification. 
		if("Child".equals(chosenAge)){
			chosenProfessionList = new File("ProfessionsChild.txt");
		} else if("Young Adult".equals(chosenAge)){
			chosenProfessionList = new File("Professions.txt");
		} else if("Adult".equals(chosenAge)){
			chosenProfessionList = new File("Professions.txt");
		} else if("Old".equals(chosenAge)){
			chosenProfessionList = new File("Professions.txt");
		} else if("Very Old".equals(chosenAge)){
			chosenProfessionList = new File("Professions.txt");
		} else {
			chosenProfessionList = new File("Professions.txt");
		} 
		//DEBUG TOOL
		//System.out.println("Profession List Chosen:" + chosenProfessionList);
	}
	
	public void generateProfession(String chosenAge){

		Random randomProfession = new Random();
		int index = randomProfession.nextInt(professionList.size());
		chosenProfession = professionList.get(index);
		
		String[] lineContents = chosenProfession.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenProfession = lineValue;		
		
		if("Child".equals(chosenAge)){
			Random childRandom = new Random();
			int isEmployed = childRandom.nextInt(11); //0 -> 10
			if(isEmployed < 5) { // ~50% employment rate for children (What, it's the dark ages of high fantasy. There ain't no labor laws!)
				chosenProfession = "None";
			} else if(isEmployed >= 5){
				chosenProfession = "Apprentice " + chosenProfession;
			}
		}
		
		if("Old".equals(chosenAge)){
			if("Unemployed".equals(chosenProfession)){
				//do nothing
			} else {
				Random seniorDesignation = new Random();
				int isSenior = seniorDesignation.nextInt(11); //0 -> 10
				
				if(isSenior < 5){
					//do nothing
				} else if(isSenior > 8) {
					chosenProfession = "Master " + chosenProfession; //randomly select different title suffix.
				} else {
					chosenProfession = "Senior " + chosenProfession;			
				}	
			}
		}
		
		if("Very Old".equals(chosenAge)){
			ArrayList<String> vOExceptionList = new ArrayList<String>();
			vOExceptionList.add("Baron / Baroness");
			vOExceptionList.add("Lord / Lady");
			Random veryOldDesignation = new Random();
			int isRetired = veryOldDesignation.nextInt(10); // 0 -> 9
			//Very rarely, allow Very Old age-group to still be employed. (1 / 10 chance)
			if(isRetired <= 9){ 
				if(!vOExceptionList.contains(chosenProfession)){
					chosenProfession = "Retired " + chosenProfession;
				}
			}
		}
		
	}
	
	//Inspiration fodder: http://arcana.wikidot.com/list-of-medieval-european-professions
	//http://www222.pair.com/sjohn/blueroom/demog.htm
	public void loadProfessionList(){
		
		File professionListTargetFile = chosenProfessionList;
		
		try {
			
			ReadFromFile file = new ReadFromFile(professionListTargetFile);
			professionList = file.OpenFile();
			professionList.sort(null); //Sort the list alphabetically.
			
			professionStaticList = file.OpenFile();
			for(String s: professionStaticList){
				String[] line = s.split(":");
				professionStaticList2.add(line[1]);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//DEBUG TOOL - Show the list
		//System.out.println(professionList);
	}
	
}