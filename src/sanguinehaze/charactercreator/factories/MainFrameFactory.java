package sanguinehaze.charactercreator.factories;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.MainFrame;
import sanguinehaze.charactercreator.data.NameBuilder;
import sanguinehaze.charactercreator.persistence.SourcePersistence;

public class MainFrameFactory {

    public static MainFrame create(SourcePersistence sourcePersistance) {
        GenerateSourceData sourceData = new GenerateSourceData(sourcePersistance);
        NameBuilder nameBuilder = new NameBuilder(sourceData);
        return new MainFrame(sourceData, nameBuilder);
    }
}
