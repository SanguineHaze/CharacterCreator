package sanguinehaze.charactercreator.adapter.views.factories;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.adapter.views.MainFrame;
import sanguinehaze.charactercreator.domain.NameBuilder;
import sanguinehaze.charactercreator.adapter.persistence.SourcePersistence;

public class MainFrameFactory {

    public static MainFrame create(SourcePersistence sourcePersistance) {
        GenerateSourceData sourceData = new GenerateSourceData(sourcePersistance);
        NameBuilder nameBuilder = new NameBuilder(sourceData);
        return new MainFrame(sourceData, nameBuilder);
    }
}
