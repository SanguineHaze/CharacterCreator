package sanguinehaze.charactercreator.domain.dtos;

import sanguinehaze.charactercreator.domain.identifiers.AgeId;

public class Age {
    private AgeId id;
    private String name;

    public Age(AgeId id, String name) {
        this.id = id;
        this.name = name;
    }

    public AgeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
