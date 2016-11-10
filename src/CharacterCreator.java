import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
	    });//end invokeLater (MainFrame)
	    
    }// End main()    
    
}//end CharacterCreator CLASS