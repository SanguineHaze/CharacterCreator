import java.util.ArrayList;
import java.util.Random;

public class SubRace {
	
	Random randomSubRace = new Random();
	public String chosenSubRace;
	
	public SubRace() {
		chosenSubRace = "";
		//System.out.println(chosenSubRace); 
	}
	
	public void setChosenSubRace(String chosenSubRace) {
		this.chosenSubRace = chosenSubRace;
	}

	public SubRace(String chosenRace) {
		chosenSubRace = "";
		generateSubRace(chosenRace);
		//System.out.println(chosenSubRace); 
	}
	
	public void generateSubRace(String chosenRace){
		
		ArrayList<String> tempList = new ArrayList<String>();
		for (RacialStatBlock entry: GenerateSourceData.raceStatBlock){
			//System.out.println(GenerateSourceData.subraceSourceStatic);
			if(entry.parentID.toLowerCase().equals(chosenRace.toLowerCase())) {
				tempList.add(entry.name);
			} else {
				chosenSubRace = "";
			}
		}
		if(!(tempList.isEmpty())){
			int index = randomSubRace.nextInt(tempList.size());
			chosenSubRace = tempList.get(index);
		}			
	}
	
}