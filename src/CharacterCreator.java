import java.util.Scanner;

public class CharacterCreator {
    public static void main(String[] args) {
	    //Note to self: Consider adding each of these results to an arrayList, which can then just print everything at once. (17,Oct,2016)	
    	
		for(int i = 0; i < 10; i++){	
		//RACE SECTION    	
	        Race thisRace = null; 
			thisRace = new Race(); 
				//thisRace.chosenRace = ""; //Define a desired race to output
			String myRace = thisRace.chosenRace; 
			
		//SUBRACE SECTION			
	    	SubRace thisSubRace = null;
			thisSubRace = new SubRace();
			String mySubRace = thisSubRace.getChosenRace(myRace);			
					
		//NAME (& SEX & AGE) SECTION			
			Name thisName = null;
			thisName = new Name();
				//thisName.sex = ""; //Define a desired sex to output
			String mySex = thisName.sex;			
			String myName = thisName.fullName;
				//thisName.chosenAge = "Very Old"; //Define a desired age to output
			String myAge = thisName.chosenAge;			
			
		//PROFESSION (& ALIGNMENT) SECTION
			Profession thisProfession = null;
			thisProfession = new Profession(myAge);
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
			String myNickname = thisNickname.chosenNickname;
			
			AdditionalFeatures thisDetail = null;
			thisDetail = new AdditionalFeatures(myAge, myRace, myProfession);
			String myDetail = thisDetail.chosenDetail;
			
			
		//CHARACTER SYSOUT SECTION
			System.out.println("Race: " + myRace);
			if(mySubRace != ""){
				System.out.println("Subrace: " + mySubRace);
			}
			System.out.println("Sex: " + mySex);
			if(myNickname != null){
				System.out.println("Name: " + myName + " " + myNickname);
			} else {
				System.out.println("Name: " + myName);
			}
			System.out.println("Age: " + myAge);
			if(!(myProfession.equals("None"))){
				System.out.println("Profession: " + myProfession);
			}
			System.out.println("Motivated by: " + myMotivation);
			System.out.println("Personality Traits: " + myPersonality);		
			if(myDetail != null){
				System.out.println("Additional Detail: " + myDetail);
			}
			
		//Line-split for multiple result tidiness
			System.out.println("");
			
		}//end FOR loop
    }// End main()    
}