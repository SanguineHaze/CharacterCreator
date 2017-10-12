package sanguinehaze.charactercreator.adapter.views.factories;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.adapter.persistence.RaceInfoXmlParser;
import sanguinehaze.charactercreator.adapter.persistence.repositories.factories.AgeRepoFactory;
import sanguinehaze.charactercreator.adapter.views.MainFrame;
import sanguinehaze.charactercreator.adapter.views.mappers.CharacterResultViewModelMapper;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterResultViewModel;
import sanguinehaze.charactercreator.application.CharacterResultProvider;
import sanguinehaze.charactercreator.domain.AgeGenerator;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;
import sanguinehaze.charactercreator.domain.NameBuilder;
import sanguinehaze.charactercreator.adapter.persistence.SourcePersistence;

public class MainFrameFactory {

    @SuppressWarnings("UnusedReturnValue")
    public static MainFrame create(SourcePersistence sourcePersistance) {
        CharacterCreatorRandom rand = new CharacterCreatorRandom();
        RaceInfoXmlParser raceInfoXmlParser = new RaceInfoXmlParser();
        GenerateSourceData sourceData = new GenerateSourceData(
                sourcePersistance,
                raceInfoXmlParser);
        NameBuilder nameBuilder = new NameBuilder(sourceData);
        CharacterResultViewModelMapper mapper = new CharacterResultViewModelMapper();
        AgeGenerator ageGenerator = new AgeGenerator(AgeRepoFactory.create());
        CharacterResultProvider provider = new CharacterResultProvider(rand ,ageGenerator);

        return new MainFrame(
                sourceData,
                nameBuilder,
                mapper,
                provider,
                rand);
    }
}
