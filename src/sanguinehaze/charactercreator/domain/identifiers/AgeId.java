package sanguinehaze.charactercreator.domain.identifiers;

public class AgeId extends Identifier {

    public static AgeId of(int id) {
        return new AgeId(id);
    }

    private AgeId(int id) {
        super(id);
    }
}

