package sanguinehaze.charactercreator;

import java.util.ArrayList;
import java.util.Random;

public class AgeAndSex {

    String sex = "";
    public boolean chosenSex;
    public String chosenAge;
    private ArrayList<String> ageRange;

    public AgeAndSex(GenerateSourceData data){
        GenerateSourceData data1 = data;
        ageRange = data1.getAgeRange();
        sex = "";
        chosenAge = "";
        generateSex();
        generateAge();
    }

    public void generateData(String userSex, String userAge){
        sex = "";
        chosenAge = "";
        generateSex(userSex);
        generateAge(userAge);
    }

    private void generateSex() {
        Random randomSex = new Random();
        chosenSex = randomSex.nextBoolean();

        if(chosenSex == true){
            sex = "Male";
        } else {
            sex = "Female";
        }
    }

    private void generateSex(String userSex){
        if(userSex.equals("Male")){
            sex = userSex;
        } else if(userSex.equals("Female")){
            sex = userSex;
        } else {
            generateSex();
        }
    }

    //Age
    public void generateAge(){
        Random randomAge = new Random();
        int index = randomAge.nextInt(ageRange.size());
        chosenAge = ageRange.get(index);
    }

    public void generateAge(String userAge){
        Random randomAge = new Random();
        if(ageRange.contains(userAge)){
            chosenAge = userAge;
        } else if(userAge.equals("Any Age")){
            int index = randomAge.nextInt(ageRange.size());
            chosenAge = ageRange.get(index);
        } else {
            int index = randomAge.nextInt(ageRange.size());
            chosenAge = ageRange.get(index);
        }
    }

}