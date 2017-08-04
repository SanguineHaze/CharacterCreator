package sanguinehaze.charactercreator;

import java.util.Random;

public class Race {
	
	Random randomGenerator = new Random();
	public String chosenRace;
		
     public Race() {
    	 chosenRace= "";
     }
     
     public void pickNewRace(){
    	chosenRace= "";
	   	int index = randomGenerator.nextInt(GenerateSourceData.getRaceSourceStatic().size());
	   	chosenRace = GenerateSourceData.getRaceSourceStatic().get(index);
	   	if(chosenRace.equals("Any sanguinehaze.charactercreator.Race")){
	   		pickNewRace();
	   	}
     }
     
     public void pickNewRace(String race){
    	 chosenRace = race;
     }
}
