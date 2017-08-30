package sanguinehaze.charactercreator.adapter.views;

import sanguinehaze.charactercreator.AdditionalFeatures;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterGenerationRequestViewModel;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterResultViewModel;
import sanguinehaze.charactercreator.domain.AgeGenerator;
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
    
    private List<String> characterResults = new ArrayList<>();
    private String myAge;

    private TextPanel textPanel;
    private JButton generateBtn;

    private FormPanel formPanel;
    private RacePanel racePanel;

    private JScrollPane scrollTemplate;
    private CharacterCreatorRandom rand = new CharacterCreatorRandom();

    public MainFrame(
            GenerateSourceData data,
            NameBuilder nameBuilder) {

        // LAYOUT SECTION
        super("HazeGaming NPC Generator");

        setLayout(new BorderLayout());

        JPanel formTemplate = new JPanel();
        scrollTemplate = new JScrollPane();
        textPanel = new TextPanel();
        formPanel = new FormPanel(data);
        racePanel = new RacePanel();
        generateBtn = new JButton("GENERATE!");

        add(formTemplate, BorderLayout.WEST);
        formTemplate.add(formPanel);
        add(textPanel, BorderLayout.CENTER);
        add(generateBtn, BorderLayout.SOUTH);

        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        formPanel.raceBtn.addActionListener(e -> {
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
        });
        
        racePanel.rpSaveBtn.addActionListener(e -> {
            JButton clicked = (JButton) e.getSource();
            if(clicked == racePanel.rpSaveBtn){
                racePanel.setVisible(false);
                formPanel.setVisible(true);
                textPanel.setVisible(true);
                generateBtn.setVisible(true);
            }
        });

        // GENERATE BUTTON SECTION
        generateBtn.addActionListener(ev -> {
            JButton clicked = (JButton) ev.getSource();
            if (clicked == generateBtn) {

                textPanel.clearText(); // Operation 'Clean Slate' is a go.
                long startTime = System.nanoTime();

                CharacterGenerationRequestViewModel request = getUiViewModel(formPanel, racePanel);

                textPanel.appendText("OUTPUTTING " + request.getNumGenInt() + " CHARACTER(S):" + "\n");
                textPanel.appendText("\n");

                for (int i = 0; i < request.getNumGenInt(); i++) {
                    characterResults.addAll(viewModelToStringArray(generateCharacter(data, nameBuilder, request), request.isIncludeStats()));
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
                if (request.isSaveNext()) {
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
        });
    }

    private CharacterGenerationRequestViewModel getUiViewModel(FormPanel formPanel, RacePanel racePanel) {
        CharacterGenerationRequestViewModel viewModel = new CharacterGenerationRequestViewModel();
        formPanel.getFormChanges();

        ArrayList<String> chars = racePanel.getSelectedRaces();
        int randChoice = rand.nextInt(chars.size());
        viewModel.setSelectedRace(chars.get(randChoice));
        viewModel.setSaveNext(formPanel.isSaveNext());
        viewModel.setIncludeStats(formPanel.isIncludeStats());
        viewModel.setNumGenInt(formPanel.getNumGenInt());
        viewModel.setNicknameChance(formPanel.getNicknameChanceInt());
        viewModel.setDetailsChance(formPanel.getDetailsChance());
        viewModel.setItemChance(formPanel.getItemChance());

        return viewModel;
    }

    private List<String> viewModelToStringArray(CharacterResultViewModel viewModel, boolean includeStats) {
        List<String> characterResults = new ArrayList<>();

        characterResults.add(String.format("Name: %s", viewModel.getName()));
        characterResults.add(String.format("Race: %s", viewModel.getRace()));
        if (viewModel.getSubrace().isPresent()) {
            characterResults.add(String.format("Subrace: %s", viewModel.getSubrace().get()));
        }
        characterResults.add(String.format("Sex: %s", viewModel.getSex()));
        characterResults.add(String.format("Age: %s", viewModel.getAge()));
        if (viewModel.getProfession().isPresent()) {
            characterResults.add(String.format("Profession: %s", viewModel.getProfession().get()));
        }
        if (viewModel.getMotivation().isPresent()) {
            characterResults.add(String.format("Motivated by: %s", viewModel.getMotivation().get()));
        }
        characterResults.add(String.format("Personality Traits: %s", viewModel.getPersonalityTraits()));
        if (viewModel.getAdditionalDetail().isPresent()) {
            characterResults.add(String.format("Additional Detail: %s", viewModel.getAdditionalDetail().get()));
        }
        if (viewModel.getAdditionalItem().isPresent()) {
            characterResults.add(String.format("Additional Item: %s", viewModel.getAdditionalItem().get()));
        }

        if (includeStats) {
            String speedString = String.format("Speed: %s", viewModel.getSpeed());
            if (viewModel.getFlySpeed().isPresent()) {
                speedString = String.format("%s \t Fly: %s", speedString, viewModel.getFlySpeed().get());
            }
            if (viewModel.getSwimSpeed().isPresent()) {
                speedString = String.format("%s \t Swim: %s", speedString, viewModel.getSwimSpeed().get());
            }
            characterResults.add(speedString);
            characterResults.add(String.format("STR: %s \tDEX: %s \nCON: %s \tINT: %s \nWIS: %s \tCHA: %s",
                    viewModel.getStrength(),
                    viewModel.getDexterity(),
                    viewModel.getConstitution(),
                    viewModel.getIntellect(),
                    viewModel.getWisdom(),
                    viewModel.getCharisma()));

            if (!viewModel.getLanguages().isEmpty()) {
                characterResults.add(String.format("Languages: %s", viewModel.getLanguages()));
            }
            if (!viewModel.getExtras().isEmpty()) {
                characterResults.add(String.format("Racial Extras: %s", viewModel.getExtras()));
            }
            if (!viewModel.getExtraChoice().isEmpty()) {
                characterResults.add(String.format("Racial Choice: %s", viewModel.getExtraChoice()));
            }
        }
        characterResults.add("\n");

        return characterResults;
    }

    private CharacterResultViewModel generateCharacter(GenerateSourceData data, NameBuilder nameBuilder, CharacterGenerationRequestViewModel request) {
        CharacterResultViewModel viewModel = new CharacterResultViewModel();

        Race thisRace = new Race();
        SubRace thisSubRace = new SubRace();
        AgeGenerator thisAgeGenerator = new AgeGenerator(data);
        Profession thisProfession = new Profession(myAge, data);
        AdditionalFeatures thisMotivation = new AdditionalFeatures(data);
        RacialStatBlockBuilder thisRacialStatBlockBuilder = new RacialStatBlockBuilder();

        // USER INPUTS
        String userRace = "";
        String userSubRace = "";

        String choice = request.getSelectedRace();

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

        String userAge = request.getSelectedAge();
        String userSex = request.getSelectedSex();
        String userProfession = request.getSelectedProfession();

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
        String myRace = thisRace.chosenRace;

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
        String mySubRace = thisSubRace.chosenSubRace;

        // NAME (& SEX & AGE) SECTION
        FullName fullName = nameBuilder.build();

        String mySex = userSex == null ? rand.nextSex().toString() : userSex;
        String myName = fullName.getFirstname();
        String myLastName = fullName.getLastname();

        thisAgeGenerator.generateData(userAge);
        myAge = thisAgeGenerator.getGeneratedAge();

        // PROFESSION SECTION
        if (userProfession == null) {
            thisProfession.generateNewProfession(myAge);
        } else if ("Any Profession".equals(userProfession)) {
            thisProfession.generateNewProfession(myAge);
        } else if (!(userProfession.isEmpty())) {
            thisProfession.setChosenProfession(userProfession);
        }
        String myProfession = thisProfession.chosenProfession;

        // ADDITIONAL FEATURES SECTION
        thisMotivation.generateNewAdditionalFeatures(request.getNicknameChance(), myAge, myProfession, myRace,
                request.getDetailsChance(), request.getItemChance());


        String myMotivation = thisMotivation.chosenMotivation;
        String myPersonality = thisMotivation.chosenPersonality;
        String myNickname = thisMotivation.chosenNickname;
        String myDetail = thisMotivation.chosenDetail;
        Optional<String> myItem = thisMotivation.getItem();

        // CHARACTER STAT BLOCK
        RacialStatBlock racialStatBlock = thisRacialStatBlockBuilder.setChosenRace(myRace).setChosenSubRace(mySubRace).build();

        int myStr = racialStatBlock.getBonusStr();
        int myDex = racialStatBlock.getBonusDex();
        int myCon = racialStatBlock.getBonusCon();
        int myInt = racialStatBlock.getBonusInt();
        int myWis = racialStatBlock.getBonusWis();
        int myCha = racialStatBlock.getBonusCha();
        List<String> myLanguage = racialStatBlock.getLanguage();
        int mySpeed = racialStatBlock.getSpeed();
        int myFlySpeed = racialStatBlock.getFlySpeed();
        int mySwimSpeed = racialStatBlock.getSwimSpeed();
        List<String> myExtra = racialStatBlock.getExtra();
        List<String> myExtraChoice = racialStatBlock.getExtraChoice();

        // GATHER RESULTS
        if (!myNickname.isEmpty()) {
            // Title nicknames (ex: Eckhart Rackvis The Just)
            if (myNickname.contains("the ")) {
                viewModel.setName(myName + " " + myLastName + " " + myNickname);
            } else {
                // Non-Title nicknames
                viewModel.setName(myName + " " + myNickname + " " + myLastName);
            }
        } else {
            viewModel.setName(myName + " " + myLastName);
        }
        viewModel.setRace(myRace);
        if (mySubRace != "") {
            viewModel.setSubrace(mySubRace);
        }
        viewModel.setSex(mySex);
        viewModel.setAge(myAge);
        if (!myProfession.equals("None")) {
            viewModel.setProfession(myProfession);
        }
        if (!myMotivation.isEmpty()) {
            viewModel.setMotivation(myMotivation);
        }
        viewModel.setPersonalityTraits(myPersonality);
        if (!myDetail.isEmpty()) {
            viewModel.setAdditionalDetail(myDetail);
        }

        myItem.ifPresent(viewModel::setAdditionalItem);

        if (request.isIncludeStats()) {
            viewModel.setSpeed(String.valueOf(mySpeed));
            viewModel.setSwimSpeed(String.valueOf(mySwimSpeed));
            viewModel.setFlySpeed(String.valueOf(myFlySpeed));
            viewModel.setStrength(String.valueOf(myStr));
            viewModel.setDexterity(String.valueOf(myDex));
            viewModel.setConstitution(String.valueOf(myCon));
            viewModel.setIntellect(String.valueOf(myInt));
            viewModel.setWisdom(String.valueOf(myWis));
            viewModel.setCharisma(String.valueOf(myCha));
            if (!myLanguage.isEmpty()) {
                viewModel.setLanguages(myLanguage);
            }
            if (!myExtra.isEmpty()) {
                viewModel.setExtras(myExtra);
            }
            if (!myExtraChoice.isEmpty()) {
                viewModel.setExtraChoice(myExtraChoice);
            }
        }

        return viewModel;
    }

}
