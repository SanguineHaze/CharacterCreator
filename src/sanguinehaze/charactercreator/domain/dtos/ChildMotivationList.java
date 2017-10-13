package sanguinehaze.charactercreator.domain.dtos;

import java.util.ArrayList;

public class ChildMotivationList extends DataList {
    public static ChildMotivationList of(ArrayList<String> list){
        return new ChildMotivationList(list);
    }

    public ChildMotivationList() {
        this.list = new ArrayList<String>();
    }

    private ChildMotivationList(ArrayList<String> list){
        this.list = list;
    }
}
