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
		
		//Home Location "C:/Users/sangu_000/Desktop/test2.txt";
		//Work Location "C:/users/dylanc/Desktop/test2.txt";
		
		//The master list containing all subraces
		String subRaceTargetFile = "C:/users/dylanc/Desktop/test2.txt";
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
	
	public void generateSubRace() {
		int index = randomSubRace.nextInt(subRaceList.size());
		chosenSubRace = subRaceList.get(index);
		
		String line = chosenSubRace;
		String[] lineContents = line.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenSubRace = lineContents[1];
	}
	
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

	}
	
	public String getChosenRace(String chosenRace){
		String output = "";
		if("Dragonborn".equals(chosenRace)){ //First iteration checks to see what Race has been chosen
			generateSubRace("Dragonborn");//If the above is true - fetch a new SubRace
			output = chosenSubRace;
		} else if(chosenRace.equals("Dwarf")) {
			generateSubRace("Dwarf");
			output = chosenSubRace;
		} else if(chosenRace.equals("Half-Elf")) {
			generateSubRace("Half-Elf");
			output = chosenSubRace;
		} else if(chosenRace.equals("Half-Orc")){
			generateSubRace("Half-Orc");
			output = chosenSubRace;
		} else if(chosenRace.equals("Human")){
			generateSubRace("Human");
			output = chosenSubRace;
		} else {
			output = chosenSubRace;
		}
		//test to see if it's making it into here. 
		return output;
	}
		
}//End class