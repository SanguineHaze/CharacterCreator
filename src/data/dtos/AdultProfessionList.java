package data.dtos;

import java.util.ArrayList;

public class AdultProfessionList extends DataList {

    public static AdultProfessionList of(ArrayList<String> list) {
        return new AdultProfessionList(list);
    }

    public AdultProfessionList() {
        this.list = new ArrayList<>();
    }

    private AdultProfessionList(ArrayList<String> list) {
        this.list = list;
    }

}
