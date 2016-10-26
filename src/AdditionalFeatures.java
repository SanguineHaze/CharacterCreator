import java.util.ArrayList;

public class AdditionalFeatures {
	
	String motivation;
	String personality;
	String nickname; //might remove nickname, but for now, it's in.
	ArrayList<String> motivationList = new ArrayList<String>();
	ArrayList<String> personalityList = new ArrayList<String>();
	
	public AdditionalFeatures(String chosenAge, String chosenSex, String chosenProfession){
		motivation = "";
		personality = "";
		nickname = "";
		loadMotivationList();
		loadPersonalityList();
	}
	
	private void loadMotivationList(){
		String motivationListTargetFile = "src/motivations.txt";
		
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
	
	private void loadPersonalityList() {
		String personalityListTargetFile = "src/personalities.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(personalityListTargetFile);
			personalityList = file.OpenFile();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}//end Try/Catch
	}//end loadPersonalityList()
	
	//Will need to construct each result
	
}// end CLASS
