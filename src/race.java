import java.io.IOException;
import java.util.Vector;

public class race {
	public static void main(String[] args) {
		
	
	Vector<String> raceList = new Vector<String>(10);
	
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
		} //End catch
	}//End main
	}// End race()

}// End class
