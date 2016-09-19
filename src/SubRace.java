import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	
	ArrayList<String> subRaceList = new ArrayList<String>();
	
	public SubRace() throws IOException {
		String subRaceTargetFile = "C:/users/dylanc/Desktop/test2.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subRaceList = file.OpenFile();
			
			subRaceList.sort(null);
			
			//DEBUG TOOL: Check to see that the list is being created
            /*System.out.println("Sub-Race List:");
			for(String out: subRaceList){
				System.out.println(out);
			}*/		
			
		} catch (IOException e) {
            //Default error message
            System.out.println(e.getMessage());

            //Let's make our own error
            //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
        } //End Try / Catch
		
	}//End SubRace()
	
	//this doesn't do anything! argh.
	public String getRace(String thisRace){
		String output = "";
		if(thisRace.equals("Dragonborn")){
			output = "Here there be dragons";
		} else if(thisRace.equals("Dwarf")) {
			output = "Digging that gold!";
		} else if(thisRace.equals("Half-Elf")) {
			output = "Half-Elves are the best!";
		} else if(thisRace.equals("Half-Orc")){
			output = "Half Human, half Orc, all rage.";
		} else if(thisRace.equals("Human")){
			output = "Puny meatbags!";
		} else {
			output = "I can't tell you anything about " + thisRace;
		}
		
		return output;
	}
		
}//End class
