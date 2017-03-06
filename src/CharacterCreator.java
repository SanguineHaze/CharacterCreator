import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
    	
    	//Initial List Setup work:
    	GenerateSourceData sourceData = new GenerateSourceData();
	    Name names = new Name(); 
	    Profession professions = new Profession("Adult");
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
	    });
    }
}