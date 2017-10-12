package sanguinehaze.charactercreator.domain.dtos;

import java.util.ArrayList;

public class DataList {
    protected ArrayList<String> list;

    public ArrayList<String> getList() {
        return this.list;
    }

    public String get(Integer index) {
        return this.list.get(index);
    }

    public void addToTop(String value) {
        this.list.add(0, value);
    }

    public void sortAlphabetically() {
        this.list.sort(null);
    }

    public int size() {
        return this.list.size();
    }
}
