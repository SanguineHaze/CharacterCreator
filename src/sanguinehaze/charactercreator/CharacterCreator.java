package sanguinehaze.charactercreator;

import javax.swing.SwingUtilities;

import sanguinehaze.charactercreator.factories.MainFrameFactory;
import sanguinehaze.charactercreator.persistence.SourcePersistanceFactory;
import sanguinehaze.charactercreator.persistence.SourcePersistence;

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
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        MainFrameFactory.create(sourcePersistance);
                    }
                });
                notice.dispose();
            }            
        } catch (Exception ex) {
            notice.dispose();
            ex.printStackTrace();

            // In case there's no internet connection, just launch. 
            //TODO: deal with outputting stacktrace
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MainFrameFactory.create(sourcePersistance);
                }
            });
        }
    }

}