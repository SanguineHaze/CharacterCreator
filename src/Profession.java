import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Profession {
	
	public String chosenProfession;
	private String line;
	private String lineValue;
	private String lineName;
	
	ArrayList<String> professionList = new ArrayList<String>();
	
	public Profession(){
		chosenProfession = "";
		loadProfessionList();
		generateProfession();
	}//end Profession()
	
	//Inspiration fodder: http://arcana.wikidot.com/list-of-medieval-european-professions
	//http://www222.pair.com/sjohn/blueroom/demog.htm
	public void loadProfessionList(){
		
		String professionListTargetFile = "src/Professions.txt";
		
		try {
			
			ReadFromFile file = new ReadFromFile(professionListTargetFile);
			professionList = file.OpenFile();
			professionList.sort(null); //Sort the list alphabetically.
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//DEBUG TOOL - Show the list
		//System.out.println(professionList);
		
	}//end loadProfessionList
		
	
	public void generateProfession(){
		//This *should* encompass classes. A vast list ensures we don't have a proliferation of classed NPCs (as hero types are rare)
		//Upon creating a basic list of some classes & jobs, I'm going to need to handle sub-classes and other features it looks like.
			//alternatively, I could declare them in the file itself. "Paladin of Selune" etc.
		Random randomProfession = new Random();
		int index = randomProfession.nextInt(professionList.size());
		chosenProfession = professionList.get(index);
		
		String[] lineContents = chosenProfession.split(":");
		lineName = lineContents[0];
		lineValue = lineContents[1];
		chosenProfession = lineValue;
	}
	
	//generateAlignment()
	
}//end Class - Profession
