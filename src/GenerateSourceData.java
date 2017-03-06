import java.io.File;
import java.util.ArrayList;

public class GenerateSourceData {
	
	ArrayList<String> raceSourcePreStatic = new ArrayList<String>();
	static ArrayList<String> raceSourceStatic = new ArrayList<String>();
	ArrayList<String> subraceSourcePreStatic = new ArrayList<String>();
	static ArrayList<String> subraceSourceStatic = new ArrayList<String>();
	
	////////GETTERS & SETTERS////////

	public static ArrayList<String> getRaceSourceStatic() {
		return raceSourceStatic;
	}

	////////CONSTRUCTOR////////
	GenerateSourceData(){
		raceSourceStatic.add(0, "Any Race");
		generateRaceData();
		generateSubraceData();
	}
	
	////////METHODS////////
	private void generateRaceData() {
		File targetFile = new File("Race.txt");
   	 
   	 try {
   		    ReadFromFile file = new ReadFromFile(targetFile);
   		    
   		    raceSourcePreStatic = file.OpenFile();
   		    //System.out.println(raceStaticPreList);
   		    for(String s: raceSourcePreStatic){
   		    	raceSourceStatic.add(s.replace("RACE:", ""));
   		    }
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
   		}
	}
	
	private void generateSubraceData() {
		File subRaceTargetFile = new File("Subrace.txt"); 	
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subraceSourcePreStatic.clear(); //Ensure subrace list in UI is never duplicated.
			subraceSourceStatic.clear(); //2nd step to ensure no duplicates.
			
			subraceSourcePreStatic = file.OpenFile();
			for(String lineEntry: subraceSourcePreStatic){
				String[] line = lineEntry.split(":");
				subraceSourceStatic.add(line[1]);
			}
			//DEBUG TOOL: Check to see that the list is being created
            System.out.println("Sub-Race List:");
			for(String out: subraceSourcePreStatic){
				System.out.println(out);
			}

		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());
        }
	}
	
}

