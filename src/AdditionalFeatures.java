import java.util.ArrayList;
import java.util.Random;

public class AdditionalFeatures {
	
	ArrayList<String> motivationList = new ArrayList<String>();
	ArrayList<String> personalityList = new ArrayList<String>();
	String chosenPersonality;
	String chosenMotivation;
	String chosenNickname; //might remove nickname, but for now, it's in.
	
	public AdditionalFeatures(String chosenAge, String chosenSex, String chosenProfession){
		chosenPersonality = "";
		chosenMotivation = "";
		loadMotivationList();
		generateMotivation();
		loadPersonalityList();
		generatePersonality();
	}
	
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
	}
	
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
	}
	
}// end CLASS
