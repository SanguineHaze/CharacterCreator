import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	public String chosenSubRace;
	ArrayList<String> subRaceList = new ArrayList<String>();
	
	public SubRace() {
		chosenSubRace = "";
		loadSubRaceList();
	}//End SubRace()
	
	public void loadSubRaceList(){
		
		String subRaceTargetFile = "C:/users/dylanc/Desktop/test2.txt";
		
   	 	//Home Location "C:/Users/sangu_000/Desktop/test2.txt";
		//Work Location "C:/users/dylanc/Desktop/test2.txt"; 
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subRaceList = file.OpenFile();
			
			subRaceList.sort(null);
			
			//DEBUG TOOL: Check to see that the list is being created
            /*System.out.println("Sub-Race List:");
			for(String out: subRaceList){
				System.out.println(out);
			}*/		
			
		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());

            //Let's make our own error
            //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
        } //End Try / Catch
		
	}//End loadRaceList()
	
	
	public String getChosenRace(String chosenRace){
		String output = "";
		if(chosenRace.equals("Dragonborn")){			
			output = "Here there be dragons";
		} else if(chosenRace.equals("Dwarf")) {
			output = "Digging that gold!";
		} else if(chosenRace.equals("Half-Elf")) {
			output = "Half-Elves are the best!";
		} else if(chosenRace.equals("Half-Orc")){
			output = "Half Human, half Orc, all rage.";
		} else if(chosenRace.equals("Human")){
			output = "Puny meatbags!";
		} else {
			
		}
		//test to see if it's making it into here.
		//System.out.println(output); 
		return output;
	}
		
}//End class
