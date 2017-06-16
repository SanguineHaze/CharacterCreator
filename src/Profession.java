import java.util.ArrayList;
import java.util.Random;

public class Profession {

    public String chosenProfession;

    public Profession(String chosenAge){
        chosenProfession = "";
        generateProfession(chosenAge);
    }

    public void setChosenProfession(String chosenProfession) {
        this.chosenProfession = chosenProfession;
    }

    public void generateNewProfession(String chosenAge){
        chosenProfession = "";
        generateProfession(chosenAge);
    }

    public void generateProfession(String chosenAge){

        Random randomProfession = new Random();
        int index = randomProfession.nextInt(GenerateSourceData.adultProfessionSourceStatic.size());
        chosenProfession = GenerateSourceData.adultProfessionSourceStatic.get(index);

        if("Child".equals(chosenAge)){
            int size = GenerateSourceData.childProfessionSourceStatic.size();
            int indexR = randomProfession.nextInt(size);
            
            chosenProfession = GenerateSourceData.childProfessionSourceStatic.get(indexR);

            Random childRandom = new Random();
            int isEmployed = childRandom.nextInt(11); //0 -> 10
            if(isEmployed < 5) { // ~50% employment rate for children (What, it's the dark ages of high fantasy. There ain't no labor laws!)
                chosenProfession = "None";
            } else if(isEmployed >= 5){
                chosenProfession = "Apprentice " + chosenProfession;
            }
        }

        if("Old".equals(chosenAge)){

            if("Unemployed".equals(chosenProfession)){
                //do nothing
            } else {
                Random seniorDesignation = new Random();
                int isSenior = seniorDesignation.nextInt(11); //0 -> 10

                if(isSenior < 5){
                    //do nothing
                } else if(isSenior > 8) {
                    chosenProfession = "Master " + chosenProfession; //randomly select different title suffix.
                } else {
                    chosenProfession = "Senior " + chosenProfession;
                }
            }
        }

        if("Very Old".equals(chosenAge)){
            ArrayList<String> vOExceptionList = new ArrayList<String>();
            vOExceptionList.add("Baron / Baroness");
            vOExceptionList.add("Lord / Lady");
            Random veryOldDesignation = new Random();
            int isRetired = veryOldDesignation.nextInt(10); // 0 -> 9
            //Very rarely, allow Very Old age-group to still be employed. (1 / 10 chance)
            if(isRetired <= 9){
                if(!vOExceptionList.contains(chosenProfession)){
                    chosenProfession = "Retired " + chosenProfession;
                }
            }
        }

    }
}