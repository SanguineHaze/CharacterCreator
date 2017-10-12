package sanguinehaze.charactercreator.adapter.views.factories;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.adapter.persistence.repositories.AgeRepo;
import sanguinehaze.charactercreator.adapter.persistence.repositories.factories.AgeRepoFactory;
import sanguinehaze.charactercreator.adapter.views.FormPanel;

public class FormPanelFactory {
    public static FormPanel create(GenerateSourceData data) {

        AgeRepo ageRepo = AgeRepoFactory.create();
        return new FormPanel(data, ageRepo);
    }
}
