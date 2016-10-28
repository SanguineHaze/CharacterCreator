import java.util.Scanner;

public class CharacterCreator {
    public static void main(String[] args) {
	    //Note to self: Consider adding each of these results to an arrayList, which can then just print everything at once. (17,Oct,2016)	
    	
		for(int i = 0; i < 5; i++){	
		//RACE SECTION    	
	        Race thisRace = null; 
			thisRace = new Race(); 
				//thisRace.chosenRace = ""; //Define a desired race to output
			String myRace = thisRace.chosenRace;
			//Check if the race is being assigned
			System.out.println("Race: " + myRace); 
			
		//SUBRACE SECTION			
	    	SubRace thisSubRace = null;
			thisSubRace = new SubRace();
			String mySubRace = thisSubRace.getChosenRace(myRace);
			if(mySubRace != ""){
				System.out.println("Subrace: " + mySubRace);
			}
					
		//NAME (& SEX & AGE) SECTION			
			Name thisName = null;
			thisName = new Name();
				//thisName.sex = ""; //Define a desired sex to output
			String mySex = thisName.sex;
			System.out.println("Sex: " + mySex);
			String myName = thisName.fullName;
			System.out.println("Name: " + myName);
				//thisName.chosenAge = "Very Old"; //Define a desired age to output
			String myAge = thisName.chosenAge;
			System.out.println("Age: " + myAge);
			
		//PROFESSION (& ALIGNMENT) SECTION
			Profession thisProfession = null;
			thisProfession = new Profession(myAge);
			String myProfession = thisProfession.chosenProfession;
			if(!(myProfession.equals("None"))){
				System.out.println("Profession: " + myProfession);
			}//end if. This ensures that if the profession is anything besides "None" the profession is printed out.
			
		//ADDITIONAL FEATURES (MOTIVATION, PERSONALITY, NICKNAME)
			AdditionalFeatures thisMotivation = null;
			thisMotivation = new AdditionalFeatures(myAge, mySex, myProfession);
			String myMotivation = thisMotivation.chosenMotivation;
			System.out.println("Motivated by: " + myMotivation);
			
			AdditionalFeatures thisPersonality = null;
			thisPersonality = new AdditionalFeatures(myAge, mySex, myProfession);
			String myPersonality = thisPersonality.chosenPersonality;
			System.out.println("Personality Traits: " + myPersonality);
			
		//Line-split for multiple result tidiness
			System.out.println("");
		}//end FOR loop
    }// End main()    
}