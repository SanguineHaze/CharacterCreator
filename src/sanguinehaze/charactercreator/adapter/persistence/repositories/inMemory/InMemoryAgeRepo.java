package sanguinehaze.charactercreator.adapter.persistence.repositories.inMemory;

import sanguinehaze.charactercreator.adapter.persistence.repositories.AgeRepo;
import sanguinehaze.charactercreator.domain.dtos.Age;
import sanguinehaze.charactercreator.domain.identifiers.AgeId;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAgeRepo implements AgeRepo {

    private final List<Age> ages;

    public InMemoryAgeRepo() {
        this.ages = preLoadAges();
    }

    private List<Age> preLoadAges() {
        List<Age> listOfAges = new ArrayList<>();
        listOfAges.add(new Age(AgeId.of(0), "Child"));
        listOfAges.add(new Age(AgeId.of(1), "Young Adult"));
        listOfAges.add(new Age(AgeId.of(2), "Adult"));
        listOfAges.add(new Age(AgeId.of(3), "Old"));
        listOfAges.add(new Age(AgeId.of(4), "Very Old"));
        return listOfAges;
    }

    @Override
    public List<Age> getAllAges() {
        return new ArrayList<>(ages);
    }
}

