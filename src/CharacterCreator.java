import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
    	
	    Race generateList = new Race(); //Set up a race to generate the list of races.
	    SubRace thisSubRace = new SubRace(); //Set up subRace to generate list of Subraces
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
	    });//end invokeLater (MainFrame)
	    
    }// End main()    
    
}//end CharacterCreator CLASS