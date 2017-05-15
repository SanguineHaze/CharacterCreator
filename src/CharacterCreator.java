import javax.swing.SwingUtilities;

import persistence.SourcePersistanceFactory;
import persistence.SourcePersistence;

public class CharacterCreator {

    static int thisVersion = 1;

    public static void main(String[] args) {
        SourcePersistence sourcePersistance = SourcePersistanceFactory.Create();
        
        //TODO: Create a "Checking Version" popup. Currently doesn't display anything, and the lag time where it's "not doing anything" is avoidable.
        try {
            if (Integer.parseInt(UpdateChecker.getLatestVersion()) > thisVersion) {
                new UpdateInfo(UpdateChecker.getWhatsNew());
            } else {
              
                @SuppressWarnings("unused")
                GenerateSourceData sourceData = new GenerateSourceData(sourcePersistance);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new MainFrame();
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            // In case there's no internet connection, just launch. 
            //TODO: deal with outputting stacktrace
            @SuppressWarnings("unused")
            GenerateSourceData sourceData = new GenerateSourceData(sourcePersistance);

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MainFrame();
                }
            });
        }
    }

}