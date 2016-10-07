import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	public String chosenSubRace;
	
	//Instantiate The Lists!
	ArrayList<String> subRaceList = new ArrayList<String>();
	
	String[] subRaceContent;
	String contentName;
	String contentValue;
	
	public SubRace() {
		chosenSubRace = "";
		loadSubRaceList();
		System.out.println(chosenSubRace);
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
			
			/*
			//DEBUG TOOL: Check to see that the list is being created
            System.out.println("Sub-Race List:");
			for(String out: subRaceList){
				System.out.println(out);
			}	
			*/
			
		   	int index = randomSubRace.nextInt(subRaceList.size());
		   	this.chosenSubRace = subRaceList.get(index);
		   	String[] subRaceContent = chosenSubRace.split(":");
			String contentName = subRaceContent[0];
			String contentValue = subRaceContent[1];
			this.chosenSubRace = contentValue;

			
		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());

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

/*          
String[] lineContents = line.split(":");
String lineName = lineContents[0]; //should read RACE: SUBRACE: CLASS: etc. Does not need to be sysout'd.
String lineValue = lineContents[1];
*/
