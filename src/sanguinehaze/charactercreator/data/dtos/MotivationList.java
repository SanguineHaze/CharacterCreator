package sanguinehaze.charactercreator.data.dtos;

import java.util.ArrayList;

public class MotivationList extends DataList {
    public static MotivationList of(ArrayList<String> list) {
        return new MotivationList(list);
    }

    public MotivationList() {
        this.list = new ArrayList<>();
    }

    private MotivationList(ArrayList<String> list) {
        this.list = list;
    }
}
