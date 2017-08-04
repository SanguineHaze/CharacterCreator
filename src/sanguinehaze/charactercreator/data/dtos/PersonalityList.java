package sanguinehaze.charactercreator.data.dtos;

import java.util.ArrayList;

public class PersonalityList extends DataList {
    public static PersonalityList of(ArrayList<String> list) {
        return new PersonalityList(list);
    }

    public PersonalityList() {
        this.list = new ArrayList<>();
    }

    private PersonalityList(ArrayList<String> list) {
        this.list = list;
    }
}
