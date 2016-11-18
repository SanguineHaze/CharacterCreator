import java.awt.BorderLayout;
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

	
	public TextPanel textPanel;
	private JButton generateBtn;
	private FormPanel formPanel;
	private FormListener formListener;
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
		
		setSize(1100,700);
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
					
					long startTime = System.nanoTime();
				    
				    boolean inputNN = false;
				    int nicknameChance = 0;
				    if(inputNN){
				    	System.out.println("Chance (0 -> 100) of having a nickname:");
					    int userNickname = 50;
					    nicknameChance = userNickname;
				    }	   	    
				    
			        Race thisRace = new Race(); 
			    	SubRace thisSubRace = new SubRace();		
					Name thisName = new Name();
					Profession thisProfession = new Profession();
					AdditionalFeatures thisMotivation = new AdditionalFeatures();
					
					numGenInt = formPanel.getNumGenInt(); //Check to see user's input
					//If user hasn't specified, set default:
					if(numGenInt < 1){
						numGenInt = 25;
					}
					
					textPanel.appendText("OUTPUTTING " + numGenInt + " CHARACTER(S):" + "\n");
					textPanel.appendText("\n");
				    
					for(int i = 0; i < numGenInt; i++){	
						//USER INPUTS
						userAge = "";
						userSex = "";
						userRace = "";
						
						//RACE SECTION
						if(userRace.isEmpty()){
							thisRace.pickNewRace();
						} else {
							thisRace.pickNewRace(userRace);
						}						
						myRace = thisRace.chosenRace;
						
					//SUBRACE SECTION			
						mySubRace = thisSubRace.getChosenRace(myRace);

					//NAME (& SEX & AGE) SECTION	
						thisName.generateNewNameData(userSex, userAge);
						
						mySex = thisName.sex;								
						myName = thisName.chosenName;
						myAge = thisName.chosenAge;
						
					//PROFESSION (& ALIGNMENT) SECTION
						thisProfession.generateNewProfession(myAge);
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
						
						@SuppressWarnings("unused")
						WriteToFile thisWrite = new WriteToFile(characterResults);
						textPanel.appendText(thisWrite.getWTFLocation() + "\n");
						textPanel.appendText("\n");
						
						long endTime = System.nanoTime();
						System.out.println("Runtime: "+((endTime - startTime)/1000000000.0) + " s"); 
						
				}//end if Clicked (GENERATE!)
			}//end actionPerformed
		});//end generate button actionListener
	}//end MainFrame()
}//end CLASS
