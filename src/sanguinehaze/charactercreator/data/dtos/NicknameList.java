package sanguinehaze.charactercreator.data.dtos;

import java.util.ArrayList;

public class NicknameList extends DataList {
    public static NicknameList of(ArrayList<String> list) {
        return new NicknameList(list);
    }

    public NicknameList() {
        this.list = new ArrayList<>();
    }

    private NicknameList(ArrayList<String> list) {
        this.list = list;
    }
}
