
public class CharacterCreator {
    public static void main(String[] args) {
		
	//RACE SECTION    	
        Race thisRace = null; 
		thisRace = new Race(); 
		String myRace = thisRace.getRace();
		//Check if the race is being assigned
		System.out.println("Race: " + myRace); 
		
	//SUBRACE SECTION			
    	SubRace thisSubRace = null;
		thisSubRace = new SubRace();
		//use thisSubRace's getChosenRace method to look at thisRace.getRace method. Thus passing in whatever variable has been assigned it (as chosenRace)
		String mySubRace = thisSubRace.getChosenRace(myRace);
		if(mySubRace != ""){
			System.out.println("Subrace: " + mySubRace);
		}
				
	//NAME (& SEX & AGE) SECTION			
		Name thisName = null;
		thisName = new Name();
		String mySex = thisName.sex;
		System.out.println(mySex);
		String myName = thisName.fullName;
		System.out.println("Name: " + myName);
		String myAge = thisName.chosenAge;
		System.out.println("Age: " + myAge);
		
	//PROFESSION (& ALIGNMENT) SECTION
		//placeholder
		
    }// End main()    
}

//note to self: http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder 