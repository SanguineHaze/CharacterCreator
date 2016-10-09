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
		getSubRace();
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

		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());

        } //End Try / Catch
		
	}//End loadRaceList()
	
	public String getSubRace() {
	int index = randomSubRace.nextInt(subRaceList.size());;
	chosenSubRace = subRaceList.get(index);
	
	String line = chosenSubRace;			
    String[] lineContents = line.split(":");
    lineName = lineContents[0];
    lineValue = lineContents[1];
    chosenSubRace = lineContents[1];
    
    return chosenSubRace;
	}
	
	public String getChosenRace(String chosenRace){
		String output = "";
		if("Dragonborn".equals(chosenRace)){ //First iteration checks to see what Race has been chosen
			if(!"Dragonborn".equals(lineName)){ //Second iteration checks to see that Dragonborn DOES NOT equal lineName
				SubRace fetchNew = new SubRace(); //If the above is true - fetch a new SubRace
			} else {
				output = chosenSubRace;
			}
		} else if(chosenRace.equals("Dwarf")) {
			if(!"Dwarf".equals(lineName)){
				SubRace fetchNew = new SubRace(); 
			} else {
				output = chosenSubRace;
			}
		} else if(chosenRace.equals("Half-Elf")) {
			if(!"Half-Elf".equals(lineName)){
				SubRace fetchNew = new SubRace(); 
			} else {
				output = chosenSubRace;
			}
		} else if(chosenRace.equals("Half-Orc")){
			if("Half-Orc".equals(lineName)){
				output = "The strength and rage of Orc, with the intelligence of Humans.";
			}
		} else if(chosenRace.equals("Human")){
			if("Human".equals(lineName)){
				output = "Puny meatbags!!";
			}
		} else {
			output = "Whoops, something went wrong!";
		}
		//test to see if it's making it into here. 
		return output;
	}
		
}//End class
