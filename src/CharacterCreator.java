import java.io.IOException;

public class CharacterCreator {
    public static void main(String[] args) {
        try {
            Race thisRace = new Race();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
        	SubRace thisSubRace = new SubRace(); 
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }// End main()
}
