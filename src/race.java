import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Race {
	
	Random randomGenerator = new Random();
	public String chosenRace;
	
	ArrayList<String> raceList = new ArrayList<String>(); //don't declare vectors with a size to avoid declaring too big if the file changes

     public Race() {
    	 chosenRace= "";  //instantiate to ensure no null pointer errors
    	 loadRaceList(); //make everything it's own method per Lunar's suggestion!
    	 //System.out.println(chosenRace);
     } //end Race()
     
     private void loadRaceList(){
    	 String targetFile = "C:/Users/dylanc/Desktop/test.txt"; //path to the file on local environment.
    	 
    	 //Home Location "C:/Users/sangu_000/workspace/CharacterCreator/src/test.txt";
    	 //Work Location "C:/users/dylanc/workspace/CharacterCreator/src/test.txt"; 
    	 
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
    		    
    		   	int index = randomGenerator.nextInt(raceList.size());
    		   	this.chosenRace = raceList.get(index);
    			this.chosenRace = chosenRace.replace("RACE:", "");
    		   	
    		   	//System.out.println("Debug Info: raceList is " + raceList.size() + " items"); //DEBUG TOOL: Check to see how big raceList ArrayList is
    		    //System.out.println("Debug Info: index has been randomly set to " + index); //DEBUG TOOL: check to see what value is being assigned to Index
    		   	
    		    
    		} catch (Exception e) {
    		    //Default error message
    		    System.out.println(e.getMessage());

    		    //Let's make our own error
    		    //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
    		} //End try / catch

     }//End loadRaceList()
     
     public String getRace(){
    	 return chosenRace;
     }
     
}// End class


