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
		generateSubRace();
		//System.out.println(chosenSubRace); 
	}//End SubRace()
	
	public void loadSubRaceList(){
		//The master list containing all subraces
		String subRaceTargetFile = "src/Subrace.txt"; //path to the file on local environment.  	 	
		
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
	
	public void generateSubRace() {
		int index = randomSubRace.nextInt(subRaceList.size());
		chosenSubRace = subRaceList.get(index);
		
		String line = chosenSubRace;
		String[] lineContents = line.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenSubRace = lineContents[1];
	}//end generateSubRace() (no parameters passed)
	
	public void generateSubRace(String chosenRace){
		
		ArrayList<String> tempList = new ArrayList<String>();
		for (String entry: subRaceList){ //go through our full list
			if(entry.toLowerCase().contains(chosenRace.toLowerCase())) {  //Do a safe check to see if our race is in the entry
				tempList.add(entry); //If yes, put in temp list.
			}
		}
		int index = randomSubRace.nextInt(tempList.size());
		chosenSubRace = tempList.get(index);
		
		String line = chosenSubRace;
		String[] lineContents = line.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenSubRace = lineContents[1];
		
		//DEBUG TOOL - Check to see if you're going to have any naming conflicts
		/*
		for(String out: subRaceList){
			if(out.toLowerCase().contains("elf")){
				System.out.println(out);
			}
		}//end DEBUG
		*/
		
	} //end generateSubRace("RACE")

	/*
	getChosenRace - This should be done in three steps:
	1)Check to see what the assigned Race is
	2)If subrace selection is applicable, generate a subrace
	3)Output the results of the chosen subrace OR a blank line for no subrace.
	*/
	public String getChosenRace(String chosenRace){
		String output = "";
		if("Aarakocra".equals(chosenRace)){  
			output = ""; //no subrace currently
		} else if("Aasimar".equals(chosenRace)){
			output = ""; //no subrace currently
		} else if("Dragonborn".equals(chosenRace)){ 
			generateSubRace("Dragonborn"); 
			output = chosenSubRace;
		} else if(chosenRace.equals("Dwarf")) {
			generateSubRace("Dwarf");
			output = chosenSubRace;
		} else if("Elf".equals(chosenRace)){
			generateSubRace("Elf");
			output = chosenSubRace;
		} else if("Genasi".equals(chosenRace)){
			generateSubRace("Genasi");
			output = chosenSubRace;
		} else if("Gnome".equals(chosenRace)) {
			generateSubRace("Gnome");
			output = chosenSubRace;
		} else if("Goliath".equals(chosenRace)){
			output = ""; //no subrace currently
		} else if(chosenRace.equals("Half-Elf")) {
			generateSubRace("Half-Elven");
			output = chosenSubRace + " Elf";
		} else if("Half-Orc".equals(chosenRace)){
			output = ""; //no subrace currently
		} else if ("Halfling".equals(chosenRace)){
			generateSubRace("Halfling");
			output = chosenSubRace;
		} else if(chosenRace.equals("Human")){
			generateSubRace("Human");
			output = chosenSubRace;
		} else if("Tiefling".equals(chosenRace)){
			generateSubRace("Tiefling");
			output = chosenSubRace;
		} else {
			output = chosenSubRace;
		} 
		return output;
	}//end getChosenRace()
		
}//End class