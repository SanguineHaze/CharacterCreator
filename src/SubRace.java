import java.io.IOException;
import java.util.ArrayList;

public class SubRace {
	ArrayList<String> subRaceList = new ArrayList<String>();
	
	SubRace() throws IOException {
		String subRaceTargetFile = "C:/users/dylanc/Desktop/test2.txt";
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subRaceList = file.OpenFile();
			
			subRaceList.sort(null);
			
			for(String out: subRaceList){
				System.out.println(out);
			}
		} //End Try
		
		catch (IOException e) {
            //Default error message
            System.out.println(e.getMessage());

            //Let's make our own error
            //System.out.println("ERROR: Dude, where's my file?! Better check that spelling!");
        } //End catch
		
	}//End SubRace()
		
}//End class
