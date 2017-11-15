package sanguinehaze.charactercreator.application;

import javax.swing.SwingUtilities;

import sanguinehaze.charactercreator.WriteToFile;
import sanguinehaze.charactercreator.adapter.views.factories.MainFrameFactory;
import sanguinehaze.charactercreator.adapter.persistence.SourcePersistanceFactory;
import sanguinehaze.charactercreator.adapter.persistence.SourcePersistence;

import java.util.ArrayList;
import java.util.List;


public class CharacterCreator {
    
    static int thisVersion = 2;

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
            List<String> errorStack = new ArrayList<>();
            errorStack.add(ex.toString());
            WriteToFile errorWrite = new WriteToFile(errorStack, "error");
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