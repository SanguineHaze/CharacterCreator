import java.util.ArrayList;
import java.util.Random;

public class AdditionalFeatures {
	
	ArrayList<String> motivationList = new ArrayList<String>();
	ArrayList<String> personalityList = new ArrayList<String>();
	ArrayList<String> nicknameList = new ArrayList<String>();
	ArrayList<String> detailsList = new ArrayList<String>();
		ArrayList<String> theLocalList = new ArrayList<String>();
		ArrayList<String> favorToList = new ArrayList<String>();
		ArrayList<String> protectedByList = new ArrayList<String>();
	String chosenPersonality;
	String chosenMotivation;
	String chosenNickname;
	String chosenDetail;
	
	public AdditionalFeatures(String chosenAge, String chosenProfession, String chosenRace){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = null;
		loadMotivationList();
		generateMotivation();
		loadPersonalityList();
		generatePersonality();
		loadNicknameList();
		generateNickname(chosenAge, chosenProfession, chosenRace);
		loadDetailsList();
		generateDetails(chosenRace, chosenProfession);
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
		int hasNickname = assignNickname.nextInt(101); //Randomly decide if character has nickname
		
		if(hasNickname > 85){
			Random randomNickname = new Random();
			int index = randomNickname.nextInt(nicknameList.size());
			chosenNickname = nicknameList.get(index);
		}//end if "hasNickname"		
		
	}//end generateNickname()
	
	private void loadDetailsList(){
		String detailsListTargetFile = "src/Details.txt";
		try {
			ReadFromFile file = new ReadFromFile(detailsListTargetFile);
			detailsList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}//end Try/Catch - Default Details List
		
		String theLocalReplacementTargetFile = "src/TheLocalReplacement.txt";
		try{
			ReadFromFile file = new ReadFromFile(theLocalReplacementTargetFile);
			theLocalList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		String favorToReplacementTargetFile = "src/OwesFavorTo.txt";
		try{
			ReadFromFile file = new ReadFromFile(favorToReplacementTargetFile);
			favorToList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		String protectedByReplacementFile = "src/ProtectedBy.txt";
		try{
			ReadFromFile file = new ReadFromFile(protectedByReplacementFile);
			protectedByList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}//end loadDetailsList()
	
	public void generateDetails(String race, String profession){
		Random detailsChance = new Random();
		int recieves = detailsChance.nextInt(85);
		if (recieves > 0){
			Random assignDetails = new Random();
			int index = assignDetails.nextInt(detailsList.size());
			chosenDetail = detailsList.get(index);
			
			if(chosenDetail.contains("...")){
				if(chosenDetail.contains("owed a favor by")){
					Random assignTheLocal = new Random();
					int indexTL = assignTheLocal.nextInt(theLocalList.size());
					String tLReplace = theLocalList.get(indexTL);
					this.chosenDetail = chosenDetail.replace("..." , tLReplace);
				} else if (chosenDetail.contains("Owes a favor to")){
					Random assignFavorTo = new Random();
					int indexFT = assignFavorTo.nextInt(favorToList.size());
					String fTReplace = favorToList.get(indexFT);
					this.chosenDetail = chosenDetail.replace("...", fTReplace);
				} else if (chosenDetail.contains("Protected by")){
					Random assignProtection = new Random();
					int indexPB = assignProtection.nextInt(protectedByList.size());
					String pBReplace = protectedByList.get(indexPB);
					this.chosenDetail = chosenDetail.replace("...", pBReplace);
				}
				//this.chosenDetail = chosenDetail.replace("..." , "TEST");
			}
		}
	}//end generateDetails()
	
}// end CLASS
