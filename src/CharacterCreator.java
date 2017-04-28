import persistence.SourcePersistanceFactory;
import persistence.SourcePersistence;

import javax.swing.SwingUtilities;

public class CharacterCreator {

    static int thisVersion = 1;

    public static void main(String[] args) {
        SourcePersistence sourcePersistance = SourcePersistanceFactory.Create();

        try {
            if (Integer.parseInt(UpdateChecker.getLatestVersion()) > thisVersion) {
                new UpdateInfo(UpdateChecker.getWhatsNew());
            } else {
                // Initial Data List Setup work:
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

            // In case there's no internet connection, just launch. TODO: deal with outputting stacktrace
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