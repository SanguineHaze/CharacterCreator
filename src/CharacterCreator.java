import java.util.ArrayList;

public class CharacterCreator {
    public static void main(String[] args) {
    	
	    ArrayList<String> characterResults = new ArrayList<String>();
	    
	    //Foundation work to allow user choice; Explore how JComboBox works before going much further.
	    //The goal is to have a pre-set list of selection options, that will be passed back into the For loop which creates the actual characters
	    
	    /*
	    String userRace;
	    String userSubRace;
	    String userSex;
	    String userName;
	    String userAge;
	    String userProfession;
    	*/
	    
		for(int i = 0; i < 1; i++){	
		//RACE SECTION    	
	        Race thisRace = null; 
			thisRace = new Race(); 
				//thisRace.chosenRace = "Half-Elf"; //Define a desired race to output
			String myRace = thisRace.chosenRace; 
			
		//SUBRACE SECTION			
	    	SubRace thisSubRace = null;
			thisSubRace = new SubRace();
			String mySubRace = thisSubRace.getChosenRace(myRace);			
					
		//NAME (& SEX & AGE) SECTION			
			Name thisName = null;
			thisName = new Name();
			String mySex = thisName.sex;	
				//thisName.sex = ""; //Define a desired sex to output
			String myName = thisName.chosenName;
				//thisName.chosenName = ""; //Pick your own name!
			String myAge = thisName.chosenAge;
				//thisName.chosenAge = "Very Old"; //Define a desired age to output
			
		//PROFESSION (& ALIGNMENT) SECTION
			Profession thisProfession = null;
			thisProfession = new Profession(myAge);
				//thisProfession.chosenProfession = "Baron / Baroness";
			String myProfession = thisProfession.chosenProfession;			
			
		//ADDITIONAL FEATURES (MOTIVATION, PERSONALITY, NICKNAME, DETAILS) SECTION
			AdditionalFeatures thisMotivation = null;
			thisMotivation = new AdditionalFeatures(myAge, myProfession, myRace);
			String myMotivation = thisMotivation.chosenMotivation;			
			
			AdditionalFeatures thisPersonality = null;
			thisPersonality = new AdditionalFeatures(myAge, myProfession, myRace);
			String myPersonality = thisPersonality.chosenPersonality;			
			
			AdditionalFeatures thisNickname = null;
			thisNickname = new AdditionalFeatures(myAge, myProfession, myRace);
				thisNickname.setNicknameChance(99);
			String myNickname = thisNickname.chosenNickname;
			
			AdditionalFeatures thisDetail = null;
			thisDetail = new AdditionalFeatures(myAge, myRace, myProfession);
			String myDetail = thisDetail.chosenDetail;
			
			
		//CHARACTER SYSOUT SECTION
			System.out.println("Race: " + myRace);
			characterResults.add("Race: " + myRace);
			if(mySubRace != ""){
				System.out.println("Subrace: " + mySubRace);
				characterResults.add("Subrace: " + mySubRace);
			}
			System.out.println("Sex: " + mySex);
			characterResults.add("Sex: " + mySex);
			if(myNickname != null){
				System.out.println("Name: " + myName + " " + myNickname);
				characterResults.add("Name: " + myName + " " + myNickname);
			} else {
				System.out.println("Name: " + myName);
				characterResults.add("Name: " + myName);
			}
			System.out.println("Age: " + myAge);
			characterResults.add("Age: " + myAge);
			if(!(myProfession.equals("None"))){
				System.out.println("Profession: " + myProfession);
				characterResults.add("Profession: " + myProfession);
			}
			System.out.println("Motivated by: " + myMotivation);
			characterResults.add("Motivated by: " + myMotivation);
			System.out.println("Personality Traits: " + myPersonality);
			characterResults.add("Personality Traits: " + myPersonality);
			if(myDetail != null){
				System.out.println("Additional Detail: " + myDetail);
				characterResults.add("Additional Detail: " + myDetail);
			}						
			
		//Line-split for multiple result tidiness
			System.out.println("");
			characterResults.add("\n");
			
		}//end FOR loop
		
		//CHARACTER WRITEOUT SECTION
		/*
			@SuppressWarnings("unused")
			WriteToFile thisWrite = new WriteToFile(characterResults);
		*/
    }// End main()    
}