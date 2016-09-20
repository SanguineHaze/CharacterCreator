
public class CharacterCreator {
    public static void main(String[] args) {
    	
            Race thisRace = null;
			thisRace = new Race();
			String chosenRace = thisRace.getRace();
			//Check if the race is being assigned
			//System.out.println(thisRace.getRace()); 

        	SubRace thisSubRace = null;
			thisSubRace = new SubRace();
			thisSubRace.getChosenRace(chosenRace); //not working. did something wrong.

    }// End main()    
}
