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
	String myLastName = "";
	String myAge = "";
	String myMotivation = "";	
	String myProfession = "";
	String myPersonality = "";		
	String myNickname = "";	
	String myDetail = "";
	
	RacialStatBlock myRacialStats;
	ArrayList<String> myLanguage, myExtra, myExtraChoice;
	int myStr;
	int myDex;
	int myCon;
	int myInt;
	int myWis;
	int myCha;
	int mySpeed;
	int myFlySpeed;
	int mySwimSpeed;
	
	
    int nicknameChance;
    int detailChance;
    int numGenInt;
	
	public TextPanel textPanel;
	private JButton generateBtn;
	private FormPanel formPanel;
	private boolean saveNext;
	
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
		
		// GENERATE BUTTON SECTION	
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JButton clicked = (JButton)ev.getSource();
				if(clicked == generateBtn){
					
					textPanel.clearText(); //Operation 'Clean Slate' is a go.
									
					long startTime = System.nanoTime();
					
			        Race thisRace = new Race();	
			        SubRace thisSubRace = new SubRace();
					Name thisName = new Name();
					Profession thisProfession = new Profession(myAge);
					AdditionalFeatures thisMotivation = new AdditionalFeatures();
					RacialStatBlock thisRacialStatBlock = new RacialStatBlock();
					
					saveNext = formPanel.isSaveNext();
					
					numGenInt = formPanel.getNumGenInt(); //Check to see user's input
					//If user hasn't specified, set default:
					if(numGenInt < 1){
						numGenInt = 25;
					}
					
					nicknameChance = formPanel.getNicknameChanceInt();
					if(nicknameChance < 0 || nicknameChance > 100){
						nicknameChance = 25; //handle unset nickname chance. Set a default
					}
					
					detailChance = formPanel.getDetailsChance();
					if(detailChance < 0 || detailChance > 100){
						detailChance = 25; //Default AdditionalDetail chance.
					}
					
					//DEBUG TOOL - NICKNAME CHANCE %
					//System.out.println("MainFrame - NicknameChance:" + nicknameChance);
					
					textPanel.appendText("OUTPUTTING " + numGenInt + " CHARACTER(S):" + "\n");
					textPanel.appendText("\n");
				    
					for(int i = 0; i < numGenInt; i++){	
						//USER INPUTS
						userAge = formPanel.getAgeSelected();
						userSex = formPanel.getSexSelected();
						userRace = GenerateSourceData.getRaceSourceStatic().get(formPanel.getRaceSelected());
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
						myLastName = thisName.chosenLastName;
						myAge = thisName.chosenAge;
						
					//PROFESSION SECTION
						if(userProfession.isEmpty()){
							thisProfession.generateNewProfession(myAge);
						} else if ("Any Profession".equals(userProfession)){
							thisProfession.generateNewProfession(myAge);
						} else if (!(userProfession.isEmpty())){
							thisProfession.setChosenProfession(userProfession);
						}
						
						myProfession = thisProfession.chosenProfession;			
						
					//ADDITIONAL FEATURES (MOTIVATION, PERSONALITY, NICKNAME, DETAILS) SECTION
						
						thisMotivation.generateNewAdditionalFeatures(nicknameChance, myAge, myProfession, myRace, detailChance);
						
						myMotivation = thisMotivation.chosenMotivation;			
						myPersonality = thisMotivation.chosenPersonality;			 
						myNickname = thisMotivation.chosenNickname;
						myDetail = thisMotivation.chosenDetail;
					
					//CHARACTER STAT BLOCK
						
						thisRacialStatBlock.generateRacialStats(myRace, mySubRace);
						myStr = RacialStatBlock.builtStats.bonusStr;
						myDex = RacialStatBlock.builtStats.bonusDex;
						myCon = RacialStatBlock.builtStats.bonusCon;
						myInt = RacialStatBlock.builtStats.bonusInt;
						myWis = RacialStatBlock.builtStats.bonusWis;
						myCha = RacialStatBlock.builtStats.bonusCha;
						myLanguage = RacialStatBlock.builtStats.language;
						mySpeed = RacialStatBlock.builtStats.speed;
						myFlySpeed = RacialStatBlock.builtStats.flySpeed;
						mySwimSpeed = RacialStatBlock.builtStats.swimSpeed;
						myExtra = RacialStatBlock.builtStats.extra;
						myExtraChoice = RacialStatBlock.builtStats.extraChoice;
						
					//CHARACTER SYSOUT, DISPLAY & SAVE SECTION
						
						if(!myNickname.isEmpty()){
							if(myNickname.contains("the ")){
								textPanel.appendText("Name: " + myName + " " + myLastName + " " + myNickname + "\n"); //Set "title" nicknames (ex: 'the Epic') to the end
								System.out.println("Name: " + myName + " " + myLastName + " " + myNickname); 
								if(saveNext){
									characterResults.add("Name: " + myName + " " + myLastName + " " + myNickname);
								}
							} else { 
								textPanel.appendText("Name: " + myName + " " + myNickname + " " + myLastName + "\n"); //Set non-title nicknames to the middle
								System.out.println("Name: " + myName + " " + myNickname + " " + myLastName);
								if(saveNext){
									characterResults.add("Name: " + myName + " " + myNickname + " " + myLastName);
								}
							}
						} else {
							textPanel.appendText("Name: " + myName + " " + myLastName + "\n");
							System.out.println("Name: " + myName + " " + myLastName);
							if(saveNext){
								characterResults.add("Name: " + myName + " " + myLastName);
							}
						}
						
						textPanel.appendText("Race: " + myRace + "\n"); //textPanel = Print to the GUI
						System.out.println("Race: " + myRace); //Sysout = Print to the IDE
						if(saveNext){
							characterResults.add("Race: " + myRace); //characterResults = Add to the array used for Saving characters
						}
						if(mySubRace != ""){
							textPanel.appendText("Subrace: " + mySubRace + "\n");
							System.out.println("Subrace: " + mySubRace);
							if(saveNext){
								characterResults.add("Subrace: " + mySubRace);
							}
						}
						
						textPanel.appendText("Sex: " + mySex + "\n");
						System.out.println("Sex: " + mySex);
						if(saveNext){
							characterResults.add("Sex: " + mySex);
						}			
						
						textPanel.appendText("Age: " + myAge + "\n");
						System.out.println("Age: " + myAge);
						if(saveNext){
							characterResults.add("Age: " + myAge);
						}
						
						if(!myProfession.equals("None")){
							textPanel.appendText("Profession: " + myProfession + "\n");
							System.out.println("Profession: " + myProfession);
							if(saveNext){
								characterResults.add("Profession: " + myProfession);
							}
						}
						if(!myMotivation.isEmpty()){
							textPanel.appendText("Motivated by: " + myMotivation + "\n");
							System.out.println("Motivated by: " + myMotivation);
							if(saveNext){
								characterResults.add("Motivated by: " + myMotivation);
							}
						}						
						textPanel.appendText("Personality Traits: " + myPersonality + "\n");
						System.out.println("Personality Traits: " + myPersonality);
						if(saveNext){
							characterResults.add("Personality Traits: " + myPersonality);
						}
						
						if(!myDetail.isEmpty()){
							textPanel.appendText("Additional Detail: " + myDetail + "\n");
							System.out.println("Additional Detail: " + myDetail);
							if(saveNext){
								characterResults.add("Additional Detail: " + myDetail);
							}
						}
						//TODO
						if(mySwimSpeed == 0 && myFlySpeed == 0){
							textPanel.appendText("Speed: " + mySpeed + "\n");
						} else if(mySwimSpeed > 0){
							textPanel.appendText("Speed: " + mySpeed + "\t" + "Swim: " + mySwimSpeed + "\n");
						} else if(myFlySpeed > 0) {
							textPanel.appendText("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed + "\n");
						} else if(myFlySpeed > 0 && mySwimSpeed > 0){
							textPanel.appendText("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed + "\t" + "Swim: " + mySwimSpeed + "\n");
						}
						textPanel.appendText("STR: " + myStr + "\t" + "DEX: " + myDex + "\n");
						textPanel.appendText("CON: " + myCon + "\t" + "INT: " + myInt + "\n");
						textPanel.appendText("WIS: " + myWis + "\t" + "CHA: " + myCha + "\n");
						if(!myLanguage.isEmpty()){
							textPanel.appendText("Languages: " + myLanguage + "\n");
						}						
						if(!myExtra.isEmpty()){
							textPanel.appendText("Racial Extras:" + myExtra + "\n");
						}						
						if(!myExtraChoice.isEmpty()){
							textPanel.appendText("Racial Choice: " + myExtraChoice);
						}
						
						if(mySwimSpeed == 0 && myFlySpeed == 0){
							System.out.println("Speed: " + mySpeed);
						} else if(mySwimSpeed > 0){
							System.out.println("Speed: " + mySpeed + "\t" + "Swim: " + mySwimSpeed);
						} else if(myFlySpeed > 0) {
							System.out.println("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed);
						} else if(myFlySpeed > 0 && mySwimSpeed > 0){
							System.out.println("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed + "\t" + "Swim: " + mySwimSpeed);
						}
						System.out.println("STR: " + myStr + "\t" + "DEX: " + myDex);	
						System.out.println("CON: " + myCon + "\t" + "INT: " + myInt);
						System.out.println("WIS: " + myWis + "\t" + "CHA: " + myCha);
						if(!myLanguage.isEmpty()){
							System.out.println("Languages: " + myLanguage);
						}
						if(!myExtra.isEmpty()){
							System.out.println("Racial Extras:" + myExtra);
						}						
						if(!myExtraChoice.isEmpty()){
							System.out.println("Racial Choice: " + myExtraChoice);
						}
						
						if(saveNext){
							if(mySwimSpeed == 0 && myFlySpeed == 0){
								characterResults.add("Speed: " + mySpeed);
							} else if(mySwimSpeed > 0){
								characterResults.add("Speed: " + mySpeed + "\t" + "Swim: " + mySwimSpeed);
							} else if(myFlySpeed > 0) {
								characterResults.add("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed);
							} else if(myFlySpeed > 0 && mySwimSpeed > 0){
								characterResults.add("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed + "\t" + "Swim: " + mySwimSpeed);
							}
							characterResults.add("STR: " + myStr + "\t" + "DEX: " + myDex);
							characterResults.add("CON: " + myCon + "\t" + "INT: " + myInt);
							characterResults.add("WIS: " + myWis + "\t" + "CHA: " + myCha);
							if(!myLanguage.isEmpty()){
								characterResults.add("Languages: " + myLanguage);
							}
							if(!myExtra.isEmpty()){
								characterResults.add("Racial Extras:" + myExtra);
							}							
							if(!myExtraChoice.isEmpty()){
								characterResults.add("Racial Choice: " + myExtraChoice);
							}
						}
						
						
					//Line-split for multiple result tidiness
						textPanel.appendText("\n");
						System.out.println("");
						if(saveNext){
							characterResults.add("\n");
						}
						
					}//end FOR loop
					
					//CHARACTER WRITEOUT SECTION
					if(saveNext){ //save if true
						@SuppressWarnings("unused")
						WriteToFile thisWrite = new WriteToFile(characterResults);
						textPanel.appendText(thisWrite.getWTFLocation() + "\n");
						textPanel.appendText("\n");
						characterResults.removeAll(characterResults);
					}						
					
					long endTime = System.nanoTime();
					System.out.println("Runtime: "+((endTime - startTime)/1000000000.0) + " s"); 
					
					textPanel.appendText("Runtime: "+((endTime - startTime)/1000000000.0) + " s");
					textPanel.appendText("\n");
						
				}//end if: Clicked (GENERATE!)
			}//end actionPerformed
		});//end generate button actionListener
	}//end MainFrame()
}//end CLASS
