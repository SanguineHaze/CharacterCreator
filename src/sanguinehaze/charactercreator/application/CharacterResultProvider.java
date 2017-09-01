package sanguinehaze.charactercreator.application;

import sanguinehaze.charactercreator.AdditionalFeatures;
import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.Profession;
import sanguinehaze.charactercreator.Race;
import sanguinehaze.charactercreator.RacialStatBlockBuilder;
import sanguinehaze.charactercreator.SubRace;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterGenerationRequestViewModel;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterResultViewModel;
import sanguinehaze.charactercreator.domain.AgeGenerator;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;
import sanguinehaze.charactercreator.domain.NameBuilder;
import sanguinehaze.charactercreator.domain.dtos.FullName;
import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CharacterResultProvider {

    private CharacterCreatorRandom rand;

    public CharacterResultProvider(CharacterCreatorRandom rand) {
        this.rand = rand;
    }

    public CharacterResultViewModel generateCharacter(GenerateSourceData data, NameBuilder nameBuilder, CharacterGenerationRequestViewModel request, String myAge) {
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
        if (!Objects.equals(mySubRace, "")) {
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
