package data.dtos;

import java.util.ArrayList;

public class DetailsList extends DataList {
    public static DetailsList of(ArrayList<String> list) {
        return new DetailsList(list);
    }

    public DetailsList() {
        this.list = new ArrayList<>();
    }

    private DetailsList(ArrayList<String> list) {
        this.list = list;
    }
}
