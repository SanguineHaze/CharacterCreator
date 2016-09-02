import java.io.IOException;


public class race {
	race() throws IOException {
		String targetFile = "C:/users/dylanc/Desktop/test.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(targetFile);
			String[] aryLines = file.OpenFile();
			
			int i;
			for(i=0; i < aryLines.length; i++){
				System.out.println(aryLines[i]);
			}
		}
		
		catch (IOException e) {
			//Default error message
			System.out.println(e.getMessage());
			
			//Let's make our own error
			//System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
		}
	}
	
	
	
	//Keeping this to "show our work", lol.
	/*race(){
			
	String[] raceArray = new String[10];
	
	raceArray[0] = "Dragonborn";
	raceArray[1] = "Dwarf";
	raceArray[2] = "Elf";
	raceArray[3] = "Genasi";
	raceArray[4] = "Gnome";
	raceArray[5] = "Goliath";
	raceArray[6] = "Halfling";
	raceArray[7] = "Half-Elf";
	raceArray[8] = "Half-Orc";
	raceArray[9] = "Human";
	raceArray[10] = "Tiefling";
	}*/
	
	/*public enum Race {
		Dragonborn, Dwarf, Elf, Genasi, Gnome, Goliath, Halfling, Half-Elf, Half-Orc, Human, Tiefling;
	}*/
	
	//It doesn't like the dashes in Half-Elf. Talking with Blair, he suggested that perhaps the best method would indeed be to load from file. Therefore, I'm going to do so.
	
	
	/*void subRace(){
		String[] subRaceArray = new String[22];
		
		//Dwarves
		subRaceArray[0] = "Hill Dwarf";
		subRaceArray[1] = "Mountain Dwarf"; 
		subRaceArray[2] = "Duergar"; 
		//Elves
		subRaceArray[3]	= "Drow"; 
		subRaceArray[4] = "Eladrin";
		subRaceArray[5] = "High Elf";
		subRaceArray[6] = "Wood Elf";
	}*/
	
	//Lunar suggests looking into Enums - as there may be a better way to do this.
	
	//I should also look into how to load from a file. http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
}