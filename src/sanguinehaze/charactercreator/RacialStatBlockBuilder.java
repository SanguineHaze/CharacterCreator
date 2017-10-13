package sanguinehaze.charactercreator;

import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;

import java.util.ArrayList;
import java.util.List;

public class RacialStatBlockBuilder {

    private int BASE_VALUE = 10;
    private String chosenRace;
    private String chosenSubRace;

    public RacialStatBlockBuilder setChosenRace(String chosenRace) {
        this.chosenRace = chosenRace;
        return this;
    }

    public RacialStatBlockBuilder setChosenSubRace(String chosenSubRace) {
        this.chosenSubRace = chosenSubRace;
        return this;
    }
    
    public RacialStatBlock build(){

        RacialStatBlock statBlock = new RacialStatBlock();
        
        RacialStatBlock tempRace = new RacialStatBlock();
        RacialStatBlock tempSubrace = new RacialStatBlock();

        int raceSpeed = 0,
                subraceSpeed = 0,
                raceFlySpeed = 0,
                subraceFlySpeed = 0,
                raceSwimSpeed = 0,
                subraceSwimSpeed = 0;
        List<String> raceLanguage = new ArrayList<String>(),
                subraceLanguage = new ArrayList<String>(),
                combinedLanguage = new ArrayList<String>(),
                raceExtra = new ArrayList<String>(),
                raceExtraChoice = new ArrayList<String>(),
                subraceExtra = new ArrayList<String>(),
                subraceExtraChoice = new ArrayList<String>(),
                builtExtra = new ArrayList<String>(),
                builtExtraChoice = new ArrayList<String>();

        for (RacialStatBlock entry: GenerateSourceData.raceStatBlock){
            if(entry.getName().toLowerCase().equals(chosenRace.toLowerCase())) {
                tempRace.setName(entry.getName());

                tempRace.setBonusStr(entry.getBonusStr());
                tempRace.setBonusDex(entry.getBonusDex());
                tempRace.setBonusCon(entry.getBonusCon());
                tempRace.setBonusInt(entry.getBonusInt());
                tempRace.setBonusWis(entry.getBonusWis());
                tempRace.setBonusCha(entry.getBonusCha());

                tempRace.setSpeed(entry.getSpeed());
                tempRace.setSwimSpeed(entry.getSwimSpeed());
                tempRace.setFlySpeed(entry.getFlySpeed());
                tempRace.setLanguage(entry.getLanguage());
                tempRace.setExtra(entry.getExtra());
                tempRace.setExtraChoice(entry.getExtraChoice());

                raceSpeed = tempRace.getSpeed();
                raceFlySpeed = tempRace.getFlySpeed();
                raceSwimSpeed = tempRace.getSwimSpeed();
                raceLanguage = tempRace.getLanguage();
                raceExtra = tempRace.getExtra();
                raceExtraChoice = tempRace.getExtraChoice();
            }
        }

        for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
            if(entry.getName().toLowerCase().equals(chosenSubRace.toLowerCase())){

                tempSubrace.setName(entry.getName());

                tempSubrace.setBonusStr(entry.getBonusStr());
                tempSubrace.setBonusDex(entry.getBonusDex());
                tempSubrace.setBonusCon(entry.getBonusCon());
                tempSubrace.setBonusInt(entry.getBonusInt());
                tempSubrace.setBonusWis(entry.getBonusWis());
                tempSubrace.setBonusCha(entry.getBonusCha());

                tempSubrace.setSpeed(entry.getSpeed());
                tempSubrace.setSwimSpeed(entry.getSwimSpeed());
                tempSubrace.setFlySpeed(entry.getFlySpeed());
                tempSubrace.setLanguage(entry.getLanguage());
                tempSubrace.setExtra(entry.getExtra());
                tempSubrace.setExtraChoice(entry.getExtraChoice());

                subraceSpeed = tempSubrace.getSpeed();
                subraceFlySpeed = tempSubrace.getFlySpeed();
                subraceSwimSpeed = tempSubrace.getSwimSpeed();
                subraceLanguage = tempSubrace.getLanguage();
                subraceExtra = tempSubrace.getExtra();
                subraceExtraChoice = tempSubrace.getExtraChoice();
            }
        }

        //SPEED
        int totalSpeed = raceSpeed + subraceSpeed;
        statBlock.setSpeed(totalSpeed);

        //FLY SPEED
        int totalFlySpeed = raceFlySpeed + subraceFlySpeed;
        statBlock.setFlySpeed(totalFlySpeed);

        //SWIM SPEED
        int totalSwimSpeed = raceSwimSpeed + subraceSwimSpeed;
        statBlock.setSwimSpeed(totalSwimSpeed);

        //STR
        int totalStr = BASE_VALUE + tempRace.getBonusStr() + tempSubrace.getBonusStr();
        statBlock.setBonusStr(totalStr);

        //DEX
        int totalDex = BASE_VALUE + tempRace.getBonusDex() + tempSubrace.getBonusDex();
        statBlock.setBonusDex(totalDex);

        //CON
        int totalCon = BASE_VALUE + tempRace.getBonusCon() + tempSubrace.getBonusCon();
        statBlock.setBonusCon(totalCon);

        //INT
        int totalInt = BASE_VALUE + tempRace.getBonusInt() + tempSubrace.getBonusInt();
        statBlock.setBonusInt(totalInt);

        //WIS
        int totalWis = BASE_VALUE + tempRace.getBonusWis() + tempSubrace.getBonusWis();
        statBlock.setBonusWis(totalWis);

        //CHA
        int totalCha = BASE_VALUE + tempRace.getBonusCha() + tempSubrace.getBonusCha();
        statBlock.setBonusCha(totalCha);

        //LANGUAGE
        for(String raceLang: raceLanguage){
            if(!combinedLanguage.contains(raceLang)){
                combinedLanguage.add(raceLang);
            }
        }
        if(subraceLanguage.size() >= 1){
            for(String entry: subraceLanguage){
                if(!combinedLanguage.contains(entry)){
                    combinedLanguage.add(entry);
                }
            }
        }
        statBlock.setLanguage(combinedLanguage);
        
        //EXTRA
        builtExtra.clear();
        for(String entry: raceExtra){
            if(!builtExtra.contains(entry)){
                builtExtra.add(entry);
            }
            for(String entry2: subraceExtra){
                if(!builtExtra.contains(entry2)){
                    builtExtra.add(entry2);
                }
            }
        }

        statBlock.setExtra(builtExtra);

        //EXTRACHOICE
        builtExtraChoice.clear();
        for(String entry: raceExtraChoice) {
            if (!builtExtraChoice.contains(entry)) {
                builtExtraChoice.add(entry);
            }
        }
        if (subraceExtraChoice.size() >= 1){
            for(String entry2: subraceExtraChoice){
                if(!builtExtraChoice.contains(entry2)){
                    builtExtraChoice.add(entry2);
                }
            }
        }
        statBlock.setExtraChoice(builtExtraChoice);
        
        return statBlock;
    }
}
