package sanguinehaze.charactercreator;

import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;

import java.util.ArrayList;

public class AgeGenerator {

    private String generatedAge;
    private ArrayList<String> ageRange;
    private CharacterCreatorRandom random;

    public AgeGenerator(GenerateSourceData data){
        random = new CharacterCreatorRandom();
        ageRange = data.getAgeRange();
        generateData();
    }

    public void generateData(String userAge){
        if (this.ageRange.contains(userAge)) {
            generatedAge = userAge;
        }
        else  {
            generatedAge = random.nextElement(ageRange);
        }
    }

    public String getGeneratedAge() {
        return generatedAge;
    }

    private void generateData() {
        generateData("");
    }
}