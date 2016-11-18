import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	public String chosenSubRace;
	private String line;
	private String lineName;
	private String lineValue;
	//Instantiate The Lists!
	ArrayList<String> subRaceList = new ArrayList<String>(); //This is the master list of all subraces
	static ArrayList<String> subRaceStaticList = new ArrayList<String>();
	static ArrayList<String> subRaceStaticList2 = new ArrayList<String>();
	String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\").replace("%20", " ");
	
	public SubRace() {
		chosenSubRace = "";
		loadSubRaceList();
		//System.out.println(chosenSubRace); 
	}//End SubRace()
	
	public SubRace(String chosenRace) {
		chosenSubRace = "";
		generateSubRace(chosenRace);
		//System.out.println(chosenSubRace); 
	}//End SubRace()
	
	public void loadSubRaceList(){
		//The master list containing all subraces
		String subRaceTargetFile = filesDirectory + "sourceData\\Subrace.txt"; //path to the file on local environment.  	 	
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subRaceList = file.OpenFile();			
			subRaceList.sort(null);
			
			subRaceStaticList = file.OpenFile();
			for(String s: subRaceStaticList){
				String[] line = s.split(":");
				subRaceStaticList2.add(line[1]);
			}
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
			} else {
				chosenSubRace = "";
			}
		}
		if(!(tempList.isEmpty())){
			int index = randomSubRace.nextInt(tempList.size());
			chosenSubRace = tempList.get(index);
			
			String line = chosenSubRace;
			String[] lineContents = line.split(":");
			lineName = lineContents[0];
			lineValue = lineContents[1];
			chosenSubRace = lineContents[1];
		}			
	} //end generateSubRace("RACE")		
}//End class