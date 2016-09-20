import java.io.IOException;

public class CharacterCreator {
    public static void main(String[] args) {
            Race thisRace = null;
			try {
				thisRace = new Race();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	SubRace thisSubRace = null;
			try {
				thisSubRace = new SubRace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }// End main()
}
