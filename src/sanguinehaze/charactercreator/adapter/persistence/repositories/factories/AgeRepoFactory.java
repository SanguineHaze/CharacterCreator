package sanguinehaze.charactercreator.adapter.persistence.repositories.factories;

import sanguinehaze.charactercreator.adapter.persistence.repositories.AgeRepo;
import sanguinehaze.charactercreator.adapter.persistence.repositories.inMemory.InMemoryAgeRepo;

public class AgeRepoFactory {
    public static AgeRepo create() {
        return new InMemoryAgeRepo();
    }
}
