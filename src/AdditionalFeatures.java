import java.io.File;
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
		ArrayList<String> mapToList = new ArrayList<String>();
		ArrayList<String> possessesAList = new ArrayList<String>();
		ArrayList<String> obsessedByList = new ArrayList<String>();
		ArrayList<String> cursedByList = new ArrayList<String>();
	String chosenPersonality;
	String chosenMotivation;
	String chosenNickname;
	String chosenDetail;
	private int nicknameChance;
	private int detailChance;
	
	public AdditionalFeatures(){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = "";
		loadMotivationList();
		generateMotivation("Child");
		loadPersonalityList();
		generatePersonality();
		loadNicknameList();
		setNicknameChance(0);
		generateNickname(null, null, null);
		loadDetailsList();
		generateDetails("Dwarf", "none");
		//DEBUG TOOL - CONSTRUCTOR 1
		//System.out.println("AddFeat 1 NN: " + chosenNickname);
	}

	public void generateNewAdditionalFeatures(int nicknameChance, String chosenAge, String chosenProfession, String chosenRace, int detailChance){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = "";
		chosenNickname = "";
		generateMotivation(chosenAge);
		generatePersonality();
		setNicknameChance(nicknameChance);
		generateNickname(chosenAge, chosenProfession, chosenRace);
		this.detailChance = detailChance;
		generateDetails(chosenRace, chosenProfession);
		//DEBUG TOOL - CONSTRUCTOR 2
		//System.out.println("AddFeat 2 NN: " + chosenNickname);
	}
	
	public void generateNewAdditionalFeatures(String chosenAge, String chosenProfession, String chosenRace){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = "";
		nicknameChance = 25;
		chosenNickname = "";
		generateMotivation(chosenAge);
		generatePersonality();
		setNicknameChance(nicknameChance);
		generateNickname(chosenAge, chosenProfession, chosenRace);
		generateDetails(chosenRace, chosenProfession);
		//DEBUG TOOL - CONSTRUCTOR 3
		//System.out.println("AddFeat 3 NN: " + chosenNickname);
	}
	
	public void setNicknameChance(int nicknameChance) {
		this.nicknameChance = nicknameChance;
	}
	
	//MOTIVATION SECTION
	private void loadMotivationList(){
		File motivationListTargetFile = new File("Motivations.txt");
		
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
	
	public void generateMotivation(String chosenAge){
		if(!"child".equals(chosenAge.toLowerCase())){
			Random randomMotivation = new Random();
			int index = randomMotivation.nextInt(motivationList.size());
			chosenMotivation = motivationList.get(index);
		} else if("child".equals(chosenAge.toLowerCase())){
			
			//TODO: CREATE CHILD MOTIVATION LIST!!!!!!!!!
			
			Random randomMotivation = new Random();
			int index = randomMotivation.nextInt(motivationList.size());
			chosenMotivation = motivationList.get(index);
		}
	}//End generateMotivation()
	
	//PERSONALITY SECTION
	private void loadPersonalityList() {
		File personalityListTargetFile = new File("Personalities.txt");
		
		try {
			ReadFromFile file = new ReadFromFile(personalityListTargetFile);
			personalityList = file.OpenFile();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}//end Try/Catch		
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
		File nicknameListTargetFile = new File("Nicknames.txt");
		
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
	
	//TODO: Use Age, Profession, and Race in nickname creation!!
	//Add in Nickname generation based off of profession and sex.
	public void generateNickname(String age, String profession, String race) {	
		Random assignNickname = new Random();  
		int hasNickname = assignNickname.nextInt(101); //Randomly decide if character has nickname
		
		//DEBUG TOOL: Check nickname chance rolls
		/*
		System.out.println("HNN#: " + hasNickname);
		System.out.println("NNC#: " + nicknameChance);
		*/
		
		if(hasNickname <= nicknameChance){
			Random randomNickname = new Random();
			int index = randomNickname.nextInt(nicknameList.size());
			chosenNickname = nicknameList.get(index);
			if(!chosenNickname.contains("the ")){
				this.chosenNickname = "'" + chosenNickname + "'";
			}
		}//end if "hasNickname"		
	}//end generateNickname()
	
	private void loadDetailsList(){
		File detailsListTargetFile = new File ("Details.txt");
		try {
			ReadFromFile file = new ReadFromFile(detailsListTargetFile);
			detailsList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File theLocalReplacementTargetFile = new File("TheLocalReplacement.txt");
		try{
			ReadFromFile file = new ReadFromFile(theLocalReplacementTargetFile);
			theLocalList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File favorToReplacementTargetFile = new File("OwesFavorTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(favorToReplacementTargetFile);
			favorToList = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File protectedByReplacementFile = new File("ProtectedBy.txt");
		try{
			ReadFromFile file = new ReadFromFile(protectedByReplacementFile);
			protectedByList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File mapToReplacementFile = new File("MapTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(mapToReplacementFile);
			mapToList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File possessesAReplacementFile = new File("PossessesA.txt");
		try{
			ReadFromFile file = new ReadFromFile(possessesAReplacementFile);
			possessesAList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File obsessedByReplacementFile = new File("ObsessedBy.txt");
		try {
			ReadFromFile file = new ReadFromFile(obsessedByReplacementFile);
			obsessedByList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File cursedReplacementFile = new File("Cursed.txt");
		try{
			ReadFromFile file = new ReadFromFile(cursedReplacementFile);
			cursedByList = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}//end loadDetailsList()
	
	public void generateDetails(String race, String profession){
		Random detailsChanceRandomInt = new Random();
		int recieves = detailsChanceRandomInt.nextInt(101);
		if (recieves <= detailChance ){
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
				} else if (chosenDetail.contains("Owns a map to")){
					Random assignMapTo = new Random();
					int indexMT = assignMapTo.nextInt(mapToList.size());
					String mTReplace = mapToList.get(indexMT);
					this.chosenDetail = chosenDetail.replace("..." , mTReplace);
				} else if (chosenDetail.contains("Possesses a ...")){
					Random assignPossessesA = new Random();
					int indexOA = assignPossessesA.nextInt(possessesAList.size());
					String pAReplace = possessesAList.get(indexOA);
					this.chosenDetail = chosenDetail.replace("a ...", pAReplace);
				} else if (chosenDetail.contains("obsessed by")){
					Random assignObsessedBy = new Random();
					int indexOB = assignObsessedBy.nextInt(obsessedByList.size());
					String oBReplace = obsessedByList.get(indexOB);
					this.chosenDetail = chosenDetail.replace("by ...", oBReplace);					
				} else if (chosenDetail.contains("Cursed - ...")){
					Random assignCurse = new Random();
					int indexC = assignCurse.nextInt(cursedByList.size());
					String cReplace = cursedByList.get(indexC);
					this.chosenDetail = chosenDetail.replace("...", cReplace);
				}//end inner token replacement
			}//end outer token replacement
		}//end details chance %
	}//end generateDetails()
	
}// end CLASS
