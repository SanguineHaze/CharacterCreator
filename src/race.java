import java.io.IOException;
import java.util.Vector;

public class race {

	Vector<String> raceList = new Vector<String>(); //Don't declare vectors with a size, to avoid making it too big if the file changes.

	 race() throws IOException {
		String targetFile = "C:/users/dylanc/Desktop/test.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(targetFile);
			String[] aryLines = file.OpenFile();
			
			for(String in: aryLines){ 
				raceList.add(in);
			}
			
			raceList.sort(null); //Uncommenting this will sort into alphabetical order apparently. Neat!
			
			for(String out: raceList){
				System.out.println(out);
			}
		}
		
		catch (IOException e) {
			//Default error message
			System.out.println(e.getMessage());
			
			//Let's make our own error
			//System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
		} //End catch

	}// End race()
	 
	 public static void Main(String[] args){
		 try {
			 race thisRace = new race();
		 } catch (IOException e) {
			 //ToDo auto-generated catch block
			 e.printStackTrace();
		 }
	 }//End main()

}// End class
