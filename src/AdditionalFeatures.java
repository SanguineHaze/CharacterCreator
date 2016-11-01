import java.util.ArrayList;
import java.util.Random;

public class AdditionalFeatures {
	
	ArrayList<String> motivationList = new ArrayList<String>();
	ArrayList<String> personalityList = new ArrayList<String>();
	ArrayList<String> nicknameList = new ArrayList<String>();
	String chosenPersonality;
	String chosenMotivation;
	String chosenNickname;
	
	public AdditionalFeatures(String chosenAge, String chosenProfession, String chosenRace){
		chosenPersonality = "";
		chosenMotivation = "";
		loadMotivationList();
		generateMotivation();
		loadPersonalityList();
		generatePersonality();
		loadNicknameList();
		generateNickname(chosenAge, chosenProfession, chosenRace);
	}
	
	//MOTIVATION SECTION
	private void loadMotivationList(){
		String motivationListTargetFile = "src/Motivations.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(motivationListTargetFile);
			motivationList = file.OpenFile();
			
			//DEBUG TOOL: Check to see that the list is being created
		    /*
		    System.out.println("Motivation List:");
		    for(String out: motivationList){
		        System.out.println(out);
		    }
		    */
			
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}//end Try/Catch
	}//end loadMotivationList()
	
	public void generateMotivation(){
		Random randomMotivation = new Random();
		int index = randomMotivation.nextInt(motivationList.size());
		chosenMotivation = motivationList.get(index);
	}//End generateMotivation()
	
	//PERSONALITY SECTION
	private void loadPersonalityList() {
		String personalityListTargetFile = "src/Personalities.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(personalityListTargetFile);
			personalityList = file.OpenFile();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}//end Try/Catch
		
		//DEBUG TOOL: Check to see that the list is being created - WARNING: 640+ ITEMS IN LIST
	    /*
	    System.out.println("Personalities List:");
	    for(String out: personalityList){
	        System.out.println(out);
	    }
	    */
		
	}//end loadPersonalityList()
	
	public void generatePersonality(){
		Random randomPersonality = new Random();
		int index = randomPersonality.nextInt(personalityList.size());
		int index2 = randomPersonality.nextInt(personalityList.size());
		int index3 = randomPersonality.nextInt(personalityList.size());
		chosenPersonality = personalityList.get(index) + ", " + personalityList.get(index2) + ", " + personalityList.get(index3);
	}//end generatePersonality()
	
	//NICKNAME SECTION
	private void loadNicknameList(){
		String nicknameListTargetFile = "src/Nicknames.txt";
		
		try{
			ReadFromFile file = new ReadFromFile(nicknameListTargetFile);
			nicknameList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}//End Try/Catch
		
		//DEBUG TOOL: Check to see that the list is being created
	    /*
	    System.out.println("Nickname List:");
	    for(String out: nicknameList){
	        System.out.println(out);
	    }
	    */
	}//End loadNicknameList()
	
	//Add in Nickname generation based off of profession and sex.
	public void generateNickname(String age, String profession, String race) {	
		Random assignNickname = new Random();  
		int hasNickname = assignNickname.nextInt(100); //Randomly decide if character has nickname
		
		if(hasNickname > 85){
			Random randomNickname = new Random();
			int index = randomNickname.nextInt(nicknameList.size());
			chosenNickname = nicknameList.get(index);
		}//end if "hasNickname"		
		
	}//end generateNickname()
	
}// end CLASS
