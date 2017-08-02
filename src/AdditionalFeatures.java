import java.util.ArrayList;
import java.util.Random;

public class AdditionalFeatures {
		
	ArrayList<String> motivationList = GenerateSourceData.motivationSourceData;
	ArrayList<String> personalityList = GenerateSourceData.personalitySourceData;
	ArrayList<String> nicknameList = GenerateSourceData.nicknameSourceData;
	ArrayList<String> detailsList = GenerateSourceData.detailsSourceData;
	ArrayList<String> itemList = GenerateSourceData.itemsSourceData;
	
	//Lists contained within DetailsList
	ArrayList<String> theLocalList = GenerateSourceData.theLocalSourceData;
	ArrayList<String> favorToList = GenerateSourceData.favorToSourceData;
	ArrayList<String> protectedByList = GenerateSourceData.protectedBySourceData;
	ArrayList<String> mapToList = GenerateSourceData.mapToSourceData;
	ArrayList<String> possessesAList = GenerateSourceData.possessesASourceData;
	ArrayList<String> obsessedByList = GenerateSourceData.obsessedBySourceData;
	ArrayList<String> cursedByList = GenerateSourceData.cursedBySourceData;
	
	String chosenPersonality, chosenMotivation, chosenNickname, chosenDetail, chosenItem;
	private int nicknameChance, detailChance, itemChance;

	public void generateNewAdditionalFeatures(int nicknameChance, String chosenAge, String chosenProfession, String chosenRace, int detailChance,int itemChance){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = "";
		chosenItem="";
		chosenNickname = "";
		generateMotivation(chosenAge);
		generatePersonality();
		setNicknameChance(nicknameChance);
		generateNickname(chosenAge, chosenProfession, chosenRace);
		this.detailChance = detailChance;
		generateDetails(chosenRace, chosenProfession);
		this.itemChance = itemChance;
		generateItem(); //Currently no inputs, could easily take in race or profession -- Max
	}
	
	public void setNicknameChance(int nicknameChance) {
		this.nicknameChance = nicknameChance;
	}
	
	//MOTIVATION SECTION	
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
	}
	
	//PERSONALITY SECTION	
	public void generatePersonality(){
		Random randomPersonality = new Random();
		int index = randomPersonality.nextInt(personalityList.size());
		int index2 = randomPersonality.nextInt(personalityList.size());
		int index3 = randomPersonality.nextInt(personalityList.size());
			//Validate the selections
			if(index2 == index || index2 == index3){
				index2 = randomPersonality.nextInt(personalityList.size());
			}
			if(index3 == index2 || index3 == index){
				index3 = randomPersonality.nextInt(personalityList.size());
			}
		chosenPersonality = personalityList.get(index) + ", " + personalityList.get(index2) + ", " + personalityList.get(index3);
	}
	
	//NICKNAME SECTION
	//TODO: Use Age, Profession, and Race in nickname creation!!
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
		}		
	}
	//ITEM SECTION
	public void generateItem(){
	Random assignItem = new Random();
	int hasItem = assignItem.nextInt(101); //Randomly decide if character has itemChance
	
	//DEBUG TOOL: Check nickname chance rolls
	
	//System.out.println("HNN#: " + hasItem);
	//System.out.println("NNC#: " + itemChance);
	
	

			if(hasItem <= itemChance){
			Random randomItem = new Random();
			int index = randomItem.nextInt(itemList.size());
			chosenItem = itemList.get(index);
			if(chosenItem.contains("+")){
				this.chosenItem = "" + chosenItem + "!";
			}
		}		
	}
		
	
	
	
	///ADDITIONAL DETAILS SECTION///
	public void generateDetails(String race, String profession){
		
		//TODO: use Race and Profession
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
				}
			}
		}
	}
	
}
