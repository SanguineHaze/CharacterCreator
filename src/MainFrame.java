import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class MainFrame extends JFrame {
	
	ArrayList<String> characterResults = new ArrayList<String>();
	
    String userRace;
    String userSubRace;
    String userSex;
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

	
	public TextPanel textPanel;
	private JButton generateBtn;
	private FormPanel formPanel;
	private FormListener formListener;
	private boolean saveNext;
	int numGenInt;
	
	FormEvent formEvent = new FormEvent(this, numGenInt);
	
	public MainFrame() {
		
		// LAYOUT SECTION 
		super("HazeGaming NPC Generator");
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		generateBtn = new JButton("GENERATE!");
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(generateBtn, BorderLayout.SOUTH);
		
		setSize(1200,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				numGenInt = e.getNumGenInt();
				System.out.println(numGenInt);
			}
		});
		
		// GENERATE BUTTON SECTION
	
		generateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {

				JButton clicked = (JButton)ev.getSource();
				
				if(clicked == generateBtn){
					
					textPanel.clearText(); //Operation 'Clean Slate' is a go.
									
					long startTime = System.nanoTime();
				    
				    boolean inputNN = false; //Building for the Future. Not yet in use.
				    int nicknameChance = 0;
				    if(inputNN){
				    	System.out.println("Chance (0 -> 100) of having a nickname:");
					    int userNickname = 50;
					    nicknameChance = userNickname;
				    }	   	    
				    
			        Race thisRace = new Race();	
			        SubRace thisSubRace = new SubRace();
					Name thisName = new Name();
					Profession thisProfession = new Profession(myAge);
					AdditionalFeatures thisMotivation = new AdditionalFeatures();
					
					saveNext = formPanel.isSaveNext();
					
					numGenInt = formPanel.getNumGenInt(); //Check to see user's input
					//If user hasn't specified, set default:
					if(numGenInt < 1){
						numGenInt = 25;
					}
					
					textPanel.appendText("OUTPUTTING " + numGenInt + " CHARACTER(S):" + "\n");
					textPanel.appendText("\n");
				    
					for(int i = 0; i < numGenInt; i++){	
						//USER INPUTS
						userAge = formPanel.getAgeSelected();
						userSex = formPanel.getSexSelected();
						userRace = Race.raceStaticList.get(formPanel.getRaceSelected());
						userProfession = formPanel.getProfessionSelected();
							//DEBUG TOOL: See what index item is selected, what string that is inside formPanel, ensure that userRace is the same.
							/*
							System.out.println("MainFrame" + Race.raceStaticList);
							System.out.println("MainFrame" + formPanel.getRaceSelected());
							System.out.println("MainFrame - userRace:" + userRace);
							*/
						if(userRace.equals("Any Race")){
							userRace = "";
						}
						
						//RACE SECTION
						if(userRace.isEmpty()){
							thisRace.pickNewRace();
						} else {
							//System.out.println("MainFrame: userRace: " + userRace);
							thisRace.pickNewRace(userRace);
						}						
						myRace = thisRace.chosenRace;
						
					//SUBRACE SECTION
						userSubRace = formPanel.getSubRaceSelected();
						if(userSubRace.isEmpty()){
							thisSubRace.generateSubRace(myRace);
						} else if (!(userSubRace.isEmpty())){
							if(userSubRace.contains("Any")) {
								thisSubRace.generateSubRace(myRace);
							} else {
								thisSubRace.setChosenSubRace(userSubRace);
							}
						}						
						mySubRace = thisSubRace.chosenSubRace;

					//NAME (& SEX & AGE) SECTION
						//DEBUG TOOL
						//System.out.println("MainFrame userSex: " + userSex);
						
						thisName.generateNewNameData(userSex, userAge);
						
						mySex = thisName.sex;								
						myName = thisName.chosenName;
						myAge = thisName.chosenAge;
						
					//PROFESSION (& ALIGNMENT) SECTION
						if(userProfession.isEmpty()){
							thisProfession.generateNewProfession(myAge);
						} else if ("Any Profession".equals(userProfession)){
							thisProfession.generateNewProfession(myAge);
						} else if (!(userProfession.isEmpty())){
							thisProfession.setChosenProfession(userProfession);
						}
						
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
						
						textPanel.appendText("Race: " + myRace + "\n");
						System.out.println("Race: " + myRace);
						characterResults.add("Race: " + myRace);
						
						if(mySubRace != ""){
							textPanel.appendText("Subrace: " + mySubRace + "\n");
							System.out.println("Subrace: " + mySubRace);
							characterResults.add("Subrace: " + mySubRace);
						}
						
						textPanel.appendText("Sex: " + mySex + "\n");
						System.out.println("Sex: " + mySex);
						characterResults.add("Sex: " + mySex);
						
						if(!myNickname.isEmpty()){
							textPanel.appendText("Name: " + myName + " " + myNickname + "\n");
							System.out.println("Name: " + myName + " " + myNickname);
							characterResults.add("Name: " + myName + " " + myNickname);
						} else {
							textPanel.appendText("Name: " + myName + "\n");
							System.out.println("Name: " + myName);
							characterResults.add("Name: " + myName);
						}
						
						textPanel.appendText("Age: " + myAge + "\n");
						System.out.println("Age: " + myAge);
						characterResults.add("Age: " + myAge);
						
						if(!(myProfession.equals("None"))){
							textPanel.appendText("Profession: " + myProfession + "\n");
							System.out.println("Profession: " + myProfession);
							characterResults.add("Profession: " + myProfession);
						}
						
						textPanel.appendText("Motivated by: " + myMotivation + "\n");
						System.out.println("Motivated by: " + myMotivation);
						characterResults.add("Motivated by: " + myMotivation);
						
						textPanel.appendText("Personality Traits: " + myPersonality + "\n");
						System.out.println("Personality Traits: " + myPersonality);
						characterResults.add("Personality Traits: " + myPersonality);
						
						if(!myDetail.isEmpty()){
							textPanel.appendText("Additional Detail: " + myDetail + "\n");
							System.out.println("Additional Detail: " + myDetail);
							characterResults.add("Additional Detail: " + myDetail);
						}						
						
					//Line-split for multiple result tidiness
						textPanel.appendText("\n");
						System.out.println("");
						characterResults.add("\n");
						
					}//end FOR loop
					
					//CHARACTER WRITEOUT SECTION
						if(saveNext){ //save if true
							@SuppressWarnings("unused")
							WriteToFile thisWrite = new WriteToFile(characterResults);
							textPanel.appendText(thisWrite.getWTFLocation() + "\n");
							textPanel.appendText("\n");
						}						
						
						long endTime = System.nanoTime();
						System.out.println("Runtime: "+((endTime - startTime)/1000000000.0) + " s"); 
						
						textPanel.appendText("Runtime: "+((endTime - startTime)/1000000000.0) + " s");
						textPanel.appendText("\n");
						
				}//end if Clicked (GENERATE!)
			}//end actionPerformed
		});//end generate button actionListener
	}//end MainFrame()
}//end CLASS
