
public class race {
	race(){
			
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
	}
	
	void subRace(){
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
	}
	
	//Lunar suggests looking into Enums - as there may be a better way to do this.
	
	//I should also look into how to load from a file. http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
}