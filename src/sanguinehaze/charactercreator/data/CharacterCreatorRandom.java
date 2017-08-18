package sanguinehaze.charactercreator.data;

import sanguinehaze.charactercreator.data.dtos.Sex;
import sanguinehaze.charactercreator.data.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Random;

public class CharacterCreatorRandom extends Random {

    public Sex nextSex() {
        return nextSex(0.5f);
    }

    public Sex nextSex(float chanceOfMale) {
        if (chanceOfMale > 1 || chanceOfMale < 0) {
            throw new InvalidArgumentException("Percentage Chance of Male must be between 0 and 1.");
        }
        float test = nextFloat();
        return test < chanceOfMale ? Sex.Male : Sex.Female;
    }

    public <T> T nextElement(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(nextInt(list.size() - 1));
    }

}
