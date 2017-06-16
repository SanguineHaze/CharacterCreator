import javax.swing.SwingUtilities;

import persistence.SourcePersistanceFactory;
import persistence.SourcePersistence;

public class CharacterCreator {
    
    static int thisVersion = 1;

    public static void main(String[] args) {
        SourcePersistence sourcePersistance = SourcePersistanceFactory.Create();
        
        UpdateNotice notice = new UpdateNotice(); 
        try {
            if (Integer.parseInt(UpdateChecker.getLatestVersion()) > thisVersion) {
                new UpdateInfo(UpdateChecker.getWhatsNew());
                notice.dispose();
            } else {
                @SuppressWarnings("unused")
                GenerateSourceData sourceData = new GenerateSourceData(sourcePersistance);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new MainFrame();
                    }
                });
                notice.dispose();
            }            
        } catch (Exception ex) {
            notice.dispose();
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