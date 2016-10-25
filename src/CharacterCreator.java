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
				//thisName.chosenAge = "Old"; //Define a desired age to output
			String myAge = thisName.chosenAge;
			System.out.println("Age: " + myAge);
			
		//PROFESSION (& ALIGNMENT) SECTION
			Profession thisProfession = null;
			thisProfession = new Profession(myAge);
			String myProfession = thisProfession.chosenProfession;
			System.out.println("Profession: " + myProfession);
			
		//Line-split for multiple result tidiness
			System.out.println("");
		}//end FOR loop
    }// End main()    
}