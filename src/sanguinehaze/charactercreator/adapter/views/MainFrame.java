package sanguinehaze.charactercreator.adapter.views;

import sanguinehaze.charactercreator.AdditionalFeatures;
import sanguinehaze.charactercreator.AgeGenerator;
import sanguinehaze.charactercreator.adapter.views.events.FormEvent;
import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.Profession;
import sanguinehaze.charactercreator.Race;
import sanguinehaze.charactercreator.RacialStatBlockBuilder;
import sanguinehaze.charactercreator.SubRace;
import sanguinehaze.charactercreator.WriteToFile;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;
import sanguinehaze.charactercreator.domain.NameBuilder;
import sanguinehaze.charactercreator.domain.dtos.FullName;
import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	
    private static final long serialVersionUID = 3320499509997731807L;
    
    List<String> characterResults = new ArrayList<>();
    List<String> myLanguage, myExtra, myExtraChoice;
    String userRace, userSubRace, userSex, userAge, userProfession;
    String myRace, mySubRace, mySex, myName, myLastName, myAge, myMotivation, myProfession, myPersonality, myNickname,
            myDetail;
    private Optional<String> myItem;
    RacialStatBlockBuilder myRacialStats;
    int myStr, myDex, myCon, myInt, myWis, myCha, mySpeed, myFlySpeed, mySwimSpeed;
    int nicknameChance, detailChance, numGenInt, itemChance;
    int defaultDetail = 25;
    int defaultNickname = 25;

    public TextPanel textPanel;
    private JButton generateBtn;
    private FormPanel formPanel;
    private RacePanel racePanel;
    private boolean saveNext, includeStats;
    private JPanel formTemplate;
    private JScrollPane scrollTemplate;
    private final GenerateSourceData data;

    FormEvent formEvent = new FormEvent(this, numGenInt);

    public MainFrame(
            GenerateSourceData data,
            NameBuilder nameBuilder) {

        // LAYOUT SECTION
        super("HazeGaming NPC Generator");
        this.data = data;
        NameBuilder nameBuilder1 = nameBuilder;

        setLayout(new BorderLayout());

        formTemplate = new JPanel();
        scrollTemplate = new JScrollPane();
        textPanel = new TextPanel();
        formPanel = new FormPanel(this.data);
        racePanel = new RacePanel();
        generateBtn = new JButton("GENERATE!");

        add(formTemplate, BorderLayout.WEST);
        formTemplate.add(formPanel);
        add(textPanel, BorderLayout.CENTER);
        add(generateBtn, BorderLayout.SOUTH);

        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        formPanel.raceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clicked = (JButton) e.getSource();
                if(clicked == formPanel.raceBtn){
                    formPanel.setVisible(false);
                    textPanel.setVisible(false);
                    generateBtn.setVisible(false);
                    add(scrollTemplate);
                    scrollTemplate.setViewportView(racePanel);
                    racePanel.setVisible(true);
                    setVisible(true);
                }
            }
        });
        
        racePanel.rpSaveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clicked = (JButton) e.getSource();
                if(clicked == racePanel.rpSaveBtn){
                    racePanel.setVisible(false);
                    formPanel.setVisible(true);
                    textPanel.setVisible(true);
                    generateBtn.setVisible(true);
                }
            }
        });

        // GENERATE BUTTON SECTION
        generateBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                JButton clicked = (JButton) ev.getSource();
                if (clicked == generateBtn) {
                    
                    textPanel.clearText(); // Operation 'Clean Slate' is a go.
                    formPanel.getFormChanges();

                    long startTime = System.nanoTime();

                    Race thisRace = new Race();
                    SubRace thisSubRace = new SubRace();
                    AgeGenerator thisAgeGenerator = new AgeGenerator(data);
                    Profession thisProfession = new Profession(myAge, data);
                    AdditionalFeatures thisMotivation = new AdditionalFeatures(data);
                    RacialStatBlockBuilder thisRacialStatBlockBuilder = new RacialStatBlockBuilder();
                    CharacterCreatorRandom rand = new CharacterCreatorRandom();

                    saveNext = formPanel.isSaveNext();
                    includeStats = formPanel.isIncludeStats();

                    numGenInt = formPanel.getNumGenInt();
                    if (numGenInt < 1) {
                        numGenInt = 25;
                    }

                    nicknameChance = formPanel.getNicknameChanceInt();
                    if (nicknameChance < 0 || nicknameChance > 100) {
                        nicknameChance = defaultNickname;
                    }

                    detailChance = formPanel.getDetailsChance();
                    if (detailChance < 0 || detailChance > 100) {
                        detailChance = defaultDetail;
                    }
                    
                    itemChance = formPanel.getItemChance();
                    if(itemChance < 0 || itemChance > 100){
                        itemChance = 25; //Default AdditionalItem chance.
                    }

                    textPanel.appendText("OUTPUTTING " + numGenInt + " CHARACTER(S):" + "\n");
                    textPanel.appendText("\n");

                    for (int i = 0; i < numGenInt; i++) {
                        // USER INPUTS
                        userRace = "";
                        userSubRace = "";
                        
                        ArrayList<String> chars = racePanel.getSelectedRaces();
                        int randChoice = rand.nextInt(chars.size());
                        String choice = chars.get(randChoice);
                        for(RacialStatBlock racialStatBlock: GenerateSourceData.raceStatBlock){
                            if(racialStatBlock.getName().toLowerCase().equals(choice.toLowerCase())){
                                if(racialStatBlock.isSubrace()){
                                    userRace = racialStatBlock.getParentId();
                                    userSubRace = choice;
                                } else if(!racialStatBlock.isSubrace()){
                                    userRace = choice;
                                    userSubRace = "";
                                }
                            }
                        }
                        
                        userAge = formPanel.getAgeSelected();
                        userSex = formPanel.getSexSelected();
                        userProfession = formPanel.getProfessionSelected();
                        
                        if (userRace.equals("Any Race")) {
                            userRace = "";
                        }

                        // RACE SECTION
                        if (userRace.isEmpty() && userSubRace.isEmpty()) {
                            thisRace.pickNewRace();
                        } else if(!userRace.isEmpty() && userSubRace.isEmpty()){
                            thisRace.pickNewRace(userRace);
                        } else if(userRace.isEmpty() && !userSubRace.isEmpty()){
                            for(RacialStatBlock rSB: GenerateSourceData.raceStatBlock){
                                if(rSB.getName().toLowerCase().equals(userSubRace.toLowerCase())){
                                    userRace = rSB.getParentId();
                                    thisRace.pickNewRace(userRace);
                                }
                            }
                        } else if(!userRace.isEmpty() && !userSubRace.isEmpty()){
                            thisRace.pickNewRace(userRace);
                            thisSubRace.setChosenSubRace(userSubRace);
                        }
                        myRace = thisRace.chosenRace;

                        // SUBRACE SECTION
                        if (userSubRace.isEmpty()) {
                            thisSubRace.generateSubRace(myRace);
                        } else if (!userSubRace.isEmpty()) {
                            if (userSubRace.contains("Any")) {
                                thisSubRace.generateSubRace(myRace);
                            } else {
                                thisSubRace.setChosenSubRace(userSubRace);
                            }
                        }
                        mySubRace = thisSubRace.chosenSubRace;

                        // NAME (& SEX & AGE) SECTION
                        FullName fullName = nameBuilder.build();

                        mySex = userSex.isEmpty() ? rand.nextSex().toString() : userSex;
                        myName = fullName.getFirstname();
                        myLastName = fullName.getLastname();

                        thisAgeGenerator.generateData(userAge);
                        myAge = thisAgeGenerator.getGeneratedAge();

                        // PROFESSION SECTION
                        if (userProfession.isEmpty()) {
                            thisProfession.generateNewProfession(myAge);
                        } else if ("Any Profession".equals(userProfession)) {
                            thisProfession.generateNewProfession(myAge);
                        } else if (!(userProfession.isEmpty())) {
                            thisProfession.setChosenProfession(userProfession);
                        }
                        myProfession = thisProfession.chosenProfession;

                        // ADDITIONAL FEATURES SECTION
                        thisMotivation.generateNewAdditionalFeatures(nicknameChance, myAge, myProfession, myRace,
                                detailChance, itemChance);



                        myMotivation = thisMotivation.chosenMotivation;
                        myPersonality = thisMotivation.chosenPersonality;
                        myNickname = thisMotivation.chosenNickname;
                        myDetail = thisMotivation.chosenDetail;
                        myItem = thisMotivation.getItem();

                        // CHARACTER STAT BLOCK
                        RacialStatBlock racialStatBlock = thisRacialStatBlockBuilder.setChosenRace(myRace).setChosenSubRace(mySubRace).build();

                        myStr = racialStatBlock.getBonusStr();
                        myDex = racialStatBlock.getBonusDex();
                        myCon = racialStatBlock.getBonusCon();
                        myInt = racialStatBlock.getBonusInt();
                        myWis = racialStatBlock.getBonusWis();
                        myCha = racialStatBlock.getBonusCha();
                        myLanguage = racialStatBlock.getLanguage();
                        mySpeed = racialStatBlock.getSpeed();
                        myFlySpeed = racialStatBlock.getFlySpeed();
                        mySwimSpeed = racialStatBlock.getSwimSpeed();
                        myExtra = racialStatBlock.getExtra();
                        myExtraChoice = racialStatBlock.getExtraChoice();

                        // GATHER RESULTS
                        if (!myNickname.isEmpty()) {
                            // Title nicknames (ex: Eckhart Rackvis The Just)
                            if (myNickname.contains("the ")) {
                                characterResults.add("Name: " + myName + " " + myLastName + " " + myNickname);
                            } else {
                                // Non-Title nicknames
                                characterResults.add("Name: " + myName + " " + myNickname + " " + myLastName);
                            }
                        } else {
                            characterResults.add("Name: " + myName + " " + myLastName);
                        }
                        characterResults.add("Race: " + myRace);
                        if (mySubRace != "") {
                            characterResults.add("Subrace: " + mySubRace);
                        }
                        characterResults.add("Sex: " + mySex);
                        characterResults.add("Age: " + myAge);
                        if (!myProfession.equals("None")) {
                            characterResults.add("Profession: " + myProfession);
                        }
                        if (!myMotivation.isEmpty()) {
                            characterResults.add("Motivated by: " + myMotivation);
                        }
                        characterResults.add("Personality Traits: " + myPersonality);
                        if (!myDetail.isEmpty()) {
                            characterResults.add("Additional Detail: " + myDetail);
                        }

                        myItem.ifPresent(s -> characterResults.add("Additional Item: " + s));

                        if (includeStats) {
                            if (mySwimSpeed == 0 && myFlySpeed == 0) {
                                characterResults.add("Speed: " + mySpeed);
                            } else if (mySwimSpeed > 0) {
                                characterResults.add("Speed: " + mySpeed + "\t" + "Swim: " + mySwimSpeed);
                            } else if (myFlySpeed > 0) {
                                characterResults.add("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed);
                            } else if (myFlySpeed > 0 && mySwimSpeed > 0) {
                                characterResults.add("Speed: " + mySpeed + "\t" + "Fly: " + myFlySpeed + "\t" + "Swim: "
                                        + mySwimSpeed);
                            }
                            characterResults.add("STR: " + myStr + "\t" + "DEX: " + myDex);
                            characterResults.add("CON: " + myCon + "\t" + "INT: " + myInt);
                            characterResults.add("WIS: " + myWis + "\t" + "CHA: " + myCha);
                            if (!myLanguage.isEmpty()) {
                                characterResults.add("Languages: " + myLanguage);
                            }
                            if (!myExtra.isEmpty()) {
                                characterResults.add("Racial Extras: " + myExtra);
                            }
                            if (!myExtraChoice.isEmpty()) {
                                characterResults.add("Racial Choice: " + myExtraChoice);
                            }
                        }
                        characterResults.add("\n");
                    }

                    // PRINT RESULTS OUT
                    for (String out : characterResults) {
                        if (out != "\n") {
                            textPanel.appendText(out + "\n");
                        } else {
                            textPanel.appendText(out);
                        }
                        System.out.println(out);
                    }

                    // WRITE RESULTS TO FILE
                    if (saveNext) {
                        WriteToFile thisWrite = new WriteToFile(characterResults);
                        textPanel.appendText(thisWrite.getWTFLocation() + "\n");
                        textPanel.appendText("\n");
                    }

                    // NUKE LIST
                    characterResults.removeAll(characterResults);

                    // SPIT OUT TIME
                    long endTime = System.nanoTime();
                    System.out.println("Runtime: " + ((endTime - startTime) / 1000000000.0) + " s");

                    textPanel.appendText("Runtime: " + ((endTime - startTime) / 1000000000.0) + " s");
                    textPanel.appendText("\n");
                }
            }
        });
    }

}
