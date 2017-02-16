import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
    	
    	//Initial List Setup work:
	    Race races = new Race(); 
	    SubRace subraces = new SubRace(); 
	    Name names = new Name(); 
	    Profession professions = new Profession("Adult");
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
	    });
    }
}