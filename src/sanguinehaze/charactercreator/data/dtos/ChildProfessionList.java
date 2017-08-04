package sanguinehaze.charactercreator.data.dtos;

import java.util.ArrayList;

public class ChildProfessionList extends DataList {
    public static ChildProfessionList of(ArrayList<String> list) {
        return new ChildProfessionList(list);
    }

    public ChildProfessionList() {
        this.list = new ArrayList<>();
    }

    private ChildProfessionList(ArrayList<String> list) {
        this.list = list;
    }
}
