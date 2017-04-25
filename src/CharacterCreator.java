import javax.swing.SwingUtilities;

public class CharacterCreator {
    public static void main(String[] args) {
    	
    	//Check for Updates
    	try {
			if (Integer.parseInt(UpdateChecker.getLatestVersion()) > 0) {
				//new UpdateInfo(UpdateChecker.getWhatsNew()); //TODO: Create UpdateInfo class.
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	
    	//Initial Data List Setup work:
    	@SuppressWarnings("unused")
		GenerateSourceData sourceData = new GenerateSourceData(); 
    	
    	//TODO: Create version checker and auto-updater
	    
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
	    });
    }
}