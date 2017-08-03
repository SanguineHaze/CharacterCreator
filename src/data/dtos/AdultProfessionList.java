package data.dtos;

import java.util.ArrayList;

public class AdultProfessionList {
    private ArrayList<String> list;

    public static AdultProfessionList of(ArrayList<String> list) {
        return new AdultProfessionList(list);
    }

    public AdultProfessionList() {
        this.list = new ArrayList<>();
    }

    private AdultProfessionList(ArrayList<String> list) {
        this.list = list;
    }

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
