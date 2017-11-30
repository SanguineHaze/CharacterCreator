package sanguinehaze.charactercreator.domain;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.application.CharacterResultProvider;
import sanguinehaze.charactercreator.domain.dtos.FullName;

import java.util.ArrayList;
import java.util.Random;

public class NameBuilder {

    private String currentNameSet = null;
    private String currentNameSetSex = null;

    private Random random;
    private ArrayList<String> beginningName;
    private ArrayList<String> middleName;
    private ArrayList<String> endName;

    private ArrayList<String> beginningNameDefault;
    private ArrayList<String> middleNameDefault;
    private ArrayList<String> endNameDefault;
    private ArrayList<String> beginningNameDwarfM;
    private ArrayList<String> middleNameDwarfM;
    private ArrayList<String> endNameDwarfM;

    public NameBuilder(GenerateSourceData data) {
        this.random = new Random();
        this.beginningNameDefault = data.getBeginningName();
        this.middleNameDefault = data.getMiddleName();
        this.endNameDefault = data.getEndName();
        this.beginningNameDwarfM = data.getBeginningNameDwarfM();
        this.middleNameDwarfM = data.getMiddleNameDwarfM();
        this.endNameDwarfM = data.getEndNameDwarfM();

    }

    public FullName build(String race, String sex) {
        checkNameSet(race, sex);
        return FullName.of(generateName(), generateName());
    }

    private String getRandomName(ArrayList<String> list) {
        return list.get(this.random.nextInt(list.size())).split(":")[1];
    }

    private String generateName(){
        return GetWithLeadingUppercase(String.format("%s%s%s",
                generateBeginning(),
                generateMiddle(),
                generateEnd()).replaceAll("\\'", ""));
    }

    private String generateBeginning() {
        return getRandomName(this.beginningName);
    }

    private String generateMiddle(){
        return getRandomName(this.middleName);
    }

    private String generateEnd(){
        return getRandomName(this.endName);
    }

    private String GetWithLeadingUppercase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private void checkNameSet(String race, String sex){
        if(!race.toLowerCase().equals(currentNameSet) && !sex.toLowerCase().equals(currentNameSetSex)){
            setRaceNameSet(race, sex);
        }
    }

    private void setRaceNameSet(String race, String sex){
        if(race.toLowerCase().equals("dwarf")){
            currentNameSet = "dwarf";
            if(sex.toLowerCase().equals("male")){
                currentNameSetSex = "male";
                this.beginningName = beginningNameDwarfM;
                this.middleName = middleNameDwarfM;
                this.endName = endNameDwarfM;
            } else if(sex.toLowerCase().equals("female")){
                currentNameSetSex = "female";
                this.beginningName = beginningNameDefault;
                this.middleName = middleNameDefault;
                this.endName = endNameDefault;
            }
        } else {
            currentNameSet = "default";
            currentNameSetSex = "default";
            this.beginningName = beginningNameDefault;
            this.middleName = middleNameDefault;
            this.endName = endNameDefault;
        }
    }
}
