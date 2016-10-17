import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Profession {
	
	public String profession;
	ArrayList professionList = new ArrayList();
	
	public Profession(){
		profession = "";
		loadProfessionList();
		
	}//end Profession()
	
	//Inspiration fodder: http://arcana.wikidot.com/list-of-medieval-european-professions
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
		
	
	//generateProfession()
		//This *should* encompass classes. A vast list ensures we don't have a proliferation of classed NPCs (as hero types are rare)
	
	//generateAlignment()
	
}//end Class - Profession
