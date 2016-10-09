import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	public String chosenSubRace;
	private String line;
	private String lineName;
	private String lineValue;
	//Instantiate The Lists!
	ArrayList<String> subRaceList = new ArrayList<String>(); //This is the master list of all subraces. Reference purposes.
	
	public SubRace() {
		chosenSubRace = "";
		loadSubRaceList();
		System.out.println(chosenSubRace);
	}//End SubRace()
	
	public void loadSubRaceList(){
		
		//Home Location "C:/Users/sangu_000/Desktop/test2.txt";
		//Work Location "C:/users/dylanc/Desktop/test2.txt";
		
		//The master list containing all subraces
		String subRaceTargetFile = "C:/users/sangu_000/Desktop/test2.txt";
		//Each Sub-Race File
   	 	
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subRaceList = file.OpenFile();
			
			subRaceList.sort(null);
			
			//DEBUG TOOL: Check to see that the list is being created
            /*System.out.println("Sub-Race List:");
			for(String out: subRaceList){
				System.out.println(out);
			}*/
			int index = randomSubRace.nextInt(subRaceList.size());;
			this.chosenSubRace = subRaceList.get(index);
			
			String line = chosenSubRace;			
            String[] lineContents = line.split(":");
            lineName = lineContents[0];
            lineValue = lineContents[1];
            this.chosenSubRace = lineContents[1];
			
		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());

        } //End Try / Catch
		
	}//End loadRaceList()
	
	
	public String getChosenRace(String chosenRace){
		String output = "";
		if("Dragonborn".equals(chosenRace)){ //First iteration checks to see what Race has been chosen
			if("Dragonborn".equals(lineName)){ //Second iteration checks to see what SubRace has been chosen
				System.out.println("Here there be dragons!");
			}
		} else if(chosenRace.equals("Dwarf")) {
			if("Dwarf".equals(lineName)){
				System.out.println("Hi-Ho, Hi-Ho, it's off to kill I go!");
			}
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
