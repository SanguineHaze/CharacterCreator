package sanguinehaze.charactercreator.domain;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.adapter.persistence.repositories.AgeRepo;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;

import java.util.ArrayList;
import java.util.Objects;

public class AgeGenerator {

    private final AgeRepo ageRepo;
    private String generatedAge;
    private CharacterCreatorRandom random;

    public AgeGenerator(AgeRepo ageRepo){
        this.ageRepo = ageRepo;
        random = new CharacterCreatorRandom();
        generateData();
    }

    public void generateData(String userAge){
        if (this.ageRepo.getAllAges().stream().anyMatch(x -> Objects.equals(x.getName(), userAge))) {
            generatedAge = userAge;
        }
        else  {
            generatedAge = random.nextElement(this.ageRepo.getAllAges()).getName();
        }
    }

    public String getGeneratedAge() {
        return generatedAge;
    }

    private void generateData() {
        generateData("");
    }
}