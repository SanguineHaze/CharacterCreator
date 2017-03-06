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
		for (String entry: GenerateSourceData.subraceSourceStatic){
			if(entry.toLowerCase().contains(chosenRace.toLowerCase())) {
				tempList.add(entry);
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