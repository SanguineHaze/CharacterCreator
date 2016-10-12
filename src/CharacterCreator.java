
public class CharacterCreator {
    public static void main(String[] args) {
		
	//RACE SECTION    	
        Race thisRace = null; 
		thisRace = new Race(); 
		String myRace = thisRace.getRace();
		//Check if the race is being assigned
		System.out.println(myRace); 
		
	//SUBRACE SECTION			
    	SubRace thisSubRace = null;
		thisSubRace = new SubRace();
		//use thisSubRace's getChosenRace method to look at thisRace.getRace method. Thus passing in whatever variable has been assigned it (as chosenRace)
		String mySubRace = thisSubRace.getChosenRace(myRace);
		//Testing to see what value we've got assigned as subRace. Both of these are functionally the same.
		System.out.println(mySubRace);		
		
	//NAME (& SEX) SECTION			
		Name thisName = null;
		thisName = new Name();
			
    }// End main()    
}

//note to self: http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder 