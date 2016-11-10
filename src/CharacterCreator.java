import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
    	
    	long startTime = System.nanoTime();
    	
	    ArrayList<String> characterResults = new ArrayList<String>();
	    Scanner sc = new Scanner(System.in);
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("HazeGaming NPC Generator");
	    		frame.setSize(800,700);
	    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		frame.setVisible(true);
			}
	    });
	    	    
	    //GET USER INPUT SECTION

	    String userRace;
	    String userSubRace;
	    String userSex;
	    String userName;
	    String userAge;
	    String userProfession;
		    String myRace = ""; 
			String mySubRace = "";	
			String mySex = "";	
			String myName = "";
			String myAge = "";
			String myMotivation = "";	
			String myPersonality = "";		
			String myNickname = "";	
			String myDetail = "";
	    
	    boolean inputNN = false;
	    int nicknameChance = 0;
	    if(inputNN){
	    	System.out.println("Chance (0 -> 100) of having a nickname:");
		    int userNickname = sc.nextInt();
		    nicknameChance = userNickname;
	    }	   	    
	    
        Race thisRace = new Race(); 
    	SubRace thisSubRace = new SubRace();		
		Name thisName = new Name();
		Profession thisProfession = new Profession();
		AdditionalFeatures thisMotivation = new AdditionalFeatures();

	    
		for(int i = 0; i < 1000; i++){	
		//RACE SECTION    	
			thisRace.pickNewRace();
			myRace = thisRace.chosenRace;
			
		//SUBRACE SECTION			
			mySubRace = thisSubRace.getChosenRace(myRace);			
					
		//NAME (& SEX & AGE) SECTION			
			thisName.generateNewNameData();
			mySex = thisName.sex;	
				//thisName.sex = ""; //Define a desired sex to output
			myName = thisName.chosenName;
				//thisName.chosenName = ""; //Pick your own name!
			myAge = thisName.chosenAge;
				//thisName.chosenAge = "Very Old"; //Define a desired age to output
			
		//PROFESSION (& ALIGNMENT) SECTION
			thisProfession.generateNewProfession(myAge);
				//thisProfession.chosenProfession = "Baron / Baroness";
			String myProfession = thisProfession.chosenProfession;			
			
		//ADDITIONAL FEATURES (MOTIVATION, PERSONALITY, NICKNAME, DETAILS) SECTION
			if(inputNN){
				thisMotivation.generateNewAdditionalFeatures(nicknameChance, myAge, myProfession, myRace);
			}
			else{
				thisMotivation.generateNewAdditionalFeatures(myAge, myProfession, myRace);
			}			
			myMotivation = thisMotivation.chosenMotivation;			
			myPersonality = thisMotivation.chosenPersonality;			 
			myNickname = thisMotivation.chosenNickname;			
			myDetail = thisMotivation.chosenDetail;
			
			
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
		
			@SuppressWarnings("unused")
			WriteToFile thisWrite = new WriteToFile(characterResults);
			
			long endTime = System.nanoTime();
			System.out.println("Runtime: "+((endTime - startTime)/1000000000.0) + " s"); 
		
    }// End main()    
}