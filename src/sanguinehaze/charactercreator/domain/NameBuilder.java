package sanguinehaze.charactercreator.domain;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.domain.dtos.FullName;

import java.util.ArrayList;
import java.util.Random;

public class NameBuilder {

    private Random random;
    private ArrayList<String> beginningName;
    private ArrayList<String> middleName;
    private ArrayList<String> endName;

    public NameBuilder(GenerateSourceData data) {
        this.random = new Random();
        this.beginningName = data.getBeginningName();
        this.middleName = data.getMiddleName();
        this.endName = data.getEndName();
    }

    public FullName build() {
        return FullName.of(generateName(), generateName());
    }

    private String getRandomName(ArrayList<String> list) {
        return list.get(this.random.nextInt(list.size())).split(":")[1];
    }

    private String generateName(){
        return GetWithLeadingUppercase(String.format("%s%s%s",
                generateBeginning(),
                generateMiddle(),
                generateEnd()).replaceAll("\\'", ""));
    }

    private String generateBeginning() {
        return getRandomName(this.beginningName);
    }

    private String generateMiddle(){
        return getRandomName(this.middleName);
    }

    private String generateEnd(){
        return getRandomName(this.endName);
    }

    private String GetWithLeadingUppercase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
