package sanguinehaze.charactercreator;

import sanguinehaze.charactercreator.data.dtos.DetailsList;
import sanguinehaze.charactercreator.data.dtos.MotivationList;
import sanguinehaze.charactercreator.data.dtos.NicknameList;
import sanguinehaze.charactercreator.data.dtos.PersonalityList;

import java.util.ArrayList;
import java.util.Random;

public class AdditionalFeatures {

	private final GenerateSourceData data;
	private final MotivationList _motivationList;
	private final PersonalityList _personalityList;
	private final NicknameList _nicknameList;
	private final DetailsList _detailsList;
	
	//Lists contained within DetailsList
	private final ArrayList<String> theLocalList;
	private final ArrayList<String> favorToList;
	private final ArrayList<String> protectedByList;
	private final ArrayList<String> mapToList;
	private final ArrayList<String> possessesAList;
	private final ArrayList<String> obsessedByList;
	private final ArrayList<String> cursedByList;
	private final ArrayList<String> itemList;

	String chosenPersonality, chosenMotivation, chosenNickname, chosenDetail;
	private int nicknameChance, detailChance;
	private int itemChance;
	public String chosenItem;

	public AdditionalFeatures(GenerateSourceData data) {
		this.data = data;
		_motivationList = data.getMotivationList();
		_personalityList = data.getPersonalityList();
		_nicknameList = data.getNicknameList();
		_detailsList = data.getDetailsList();
		theLocalList = data.getTheLocalSourceData();
		favorToList = data.getFavorToSourceData();
		protectedByList = data.getProtectedBySourceData();
		mapToList = data.getMapToSourceData();
		possessesAList = data.getPossessesASourceData();
		obsessedByList = data.getObsessedBySourceData();
		cursedByList = data.getCursedBySourceData();
		itemList = data.getItemSourceData();
	}

	public void generateNewAdditionalFeatures(int nicknameChance, String chosenAge, String chosenProfession, String chosenRace, int detailChance, int itemChance){
		chosenPersonality = "";
		chosenMotivation = "";
		chosenDetail = "";
		chosenNickname = "";
		generateMotivation(chosenAge);
		generatePersonality();
		setNicknameChance(nicknameChance);
		generateNickname(chosenAge, chosenProfession, chosenRace);
		this.detailChance = detailChance;
		this.itemChance = itemChance;
		generateDetails(chosenRace, chosenProfession);
		generateItem();
	}
	
	public void setNicknameChance(int nicknameChance) {
		this.nicknameChance = nicknameChance;
	}
	
	//MOTIVATION SECTION	
	public void generateMotivation(String chosenAge){
		if(!"child".equals(chosenAge.toLowerCase())){
			Random randomMotivation = new Random();
			int index = randomMotivation.nextInt(_motivationList.size());
			chosenMotivation = _motivationList.get(index);
		} else if("child".equals(chosenAge.toLowerCase())){
			
			//TODO: CREATE CHILD MOTIVATION LIST (LOW PRIORITY)
			
			Random randomMotivation = new Random();
			int index = randomMotivation.nextInt(_motivationList.size());
			chosenMotivation = _motivationList.get(index);
		}
	}
	
	//PERSONALITY SECTION	
	public void generatePersonality(){
		Random randomPersonality = new Random();
		int index = randomPersonality.nextInt(_personalityList.size());
		int index2 = randomPersonality.nextInt(_personalityList.size());
		int index3 = randomPersonality.nextInt(_personalityList.size());
			//Validate the selections
			if(index2 == index || index2 == index3){
				index2 = randomPersonality.nextInt(_personalityList.size());
			}
			if(index3 == index2 || index3 == index){
				index3 = randomPersonality.nextInt(_personalityList.size());
			}
		chosenPersonality = _personalityList.get(index) + ", " + _personalityList.get(index2) + ", " + _personalityList.get(index3);
	}
	
	//NICKNAME SECTION
	//TODO: Use Age, Profession, and Race in nickname creation!!
	public void generateNickname(String age, String profession, String race) {	
		Random assignNickname = new Random();  
		int hasNickname = assignNickname.nextInt(101);

		if(hasNickname <= nicknameChance){
			Random randomNickname = new Random();
			int index = randomNickname.nextInt(_nicknameList.size());
			chosenNickname = _nicknameList.get(index);
			if(!chosenNickname.contains("the ")){
				this.chosenNickname = "'" + chosenNickname + "'";
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
			int index = assignDetails.nextInt(_detailsList.size());
			chosenDetail = _detailsList.get(index);
			
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
	//ITEM SECTION\
	public void generateItem(){
		Random assignItem = new Random();
		int hasItem = assignItem.nextInt(101);
		if(hasItem <= itemChance){
			Random randomItem = new Random();
			int index = randomItem.nextInt(itemList.size());
			chosenItem = itemList.get(index);
			if(chosenItem.contains("+")){
				this.chosenItem = "" + chosenItem + "!";
			}
		}
	}
	
}