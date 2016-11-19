import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Race {
	
	Random randomGenerator = new Random();
	public String chosenRace;
	
	ArrayList<String> raceList = new ArrayList<String>();
	static ArrayList<String> raceStaticList = new ArrayList<String>();

     public Race() {
    	 chosenRace= "";
    	 loadRaceList(); 
     } //end Race()
     
     public void pickNewRace(){
    	chosenRace= "";
	   	int index = randomGenerator.nextInt(raceList.size());
	   	chosenRace = raceList.get(index);
		chosenRace = chosenRace.replace("RACE:", "");
		//DEBUG TOOL: Check to see how big raceList ArrayList is
	   	//System.out.println("Debug Info: raceList is " + raceList.size() + " items"); 
		//DEBUG TOOL: check to see what value is being assigned to Index
	    //System.out.println("Debug Info: index has been randomly set to " + index); 
     }
     
     public void pickNewRace(String race){
    	 chosenRace = race;
     }
     
     private void loadRaceList(){
    	 String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\").replace("%20", " ");
    	 //The master list, containing all Races
    	 String targetFile = filesDirectory + "sourceData\\Race.txt"; //path to the file on local environment.
    	 
    	 try {
    		    ReadFromFile file = new ReadFromFile(targetFile);
    		    raceList = file.OpenFile();
    		    raceList.sort(null); // Fun fact about vectors, they can do sorts for you, uncommenting this will put stuff in alphabetical order.
    		        		    
    		    //DEBUG TOOL: Check to see that the list is being created
    		    /*
    		    System.out.println("Race List:");
    		    for(String out: raceList){
    		        System.out.println(out);
    		    }
    		    */
    		   
    		} catch (Exception e) {
    		    //Default error message
    		    System.out.println(e.getMessage());
    		} //End try / catch
    		raceStaticList = raceList;
     }//End loadRaceList()
     
     private ArrayList<String> getRaceStaticList(){
		return raceStaticList;
     }
}// End class
