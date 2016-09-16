import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	
	ArrayList<String> subRaceList = new ArrayList<String>();
	
	SubRace() throws IOException {
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
			
			//This section isn't working. It's not comparing the strings correctly. TODO: Solve this.
			if(race.chosenRace == "Dragonborn"){
				System.out.println("ALL HAIL THE DRAGON");
			} else if(race.chosenRace == "Dwarf") {
				System.out.println("Dig all the gold!");
			} else {
				System.out.println("I find your lack of tests disturbing.");
			}
			
		} catch (IOException e) {
            //Default error message
            System.out.println(e.getMessage());

            //Let's make our own error
            //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
        } //End Try / Catch
		
	}//End SubRace()
		
}//End class
