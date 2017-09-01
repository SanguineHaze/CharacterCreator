package sanguinehaze.charactercreator.adapter.views.mappers;

import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterResultViewModelMapper {
    public List<String> viewModelToStringArray(CharacterResultViewModel viewModel, boolean includeStats) {
        List<String> characterResults = new ArrayList<>();

        characterResults.add(String.format("Name: %s", viewModel.getName()));
        characterResults.add(String.format("Race: %s", viewModel.getRace()));
        if (viewModel.getSubrace().isPresent()) {
            characterResults.add(String.format("Subrace: %s", viewModel.getSubrace().get()));
        }
        characterResults.add(String.format("Sex: %s", viewModel.getSex()));
        characterResults.add(String.format("Age: %s", viewModel.getAge()));
        if (viewModel.getProfession().isPresent()) {
            characterResults.add(String.format("Profession: %s", viewModel.getProfession().get()));
        }
        if (viewModel.getMotivation().isPresent()) {
            characterResults.add(String.format("Motivated by: %s", viewModel.getMotivation().get()));
        }
        characterResults.add(String.format("Personality Traits: %s", viewModel.getPersonalityTraits()));
        if (viewModel.getAdditionalDetail().isPresent()) {
            characterResults.add(String.format("Additional Detail: %s", viewModel.getAdditionalDetail().get()));
        }
        if (viewModel.getAdditionalItem().isPresent()) {
            characterResults.add(String.format("Additional Item: %s", viewModel.getAdditionalItem().get()));
        }

        if (includeStats) {
            String speedString = String.format("Speed: %s", viewModel.getSpeed());
            if (viewModel.getFlySpeed().isPresent()) {
                speedString = String.format("%s \t Fly: %s", speedString, viewModel.getFlySpeed().get());
            }
            if (viewModel.getSwimSpeed().isPresent()) {
                speedString = String.format("%s \t Swim: %s", speedString, viewModel.getSwimSpeed().get());
            }
            characterResults.add(speedString);
            characterResults.add(String.format("STR: %s \tDEX: %s \nCON: %s \tINT: %s \nWIS: %s \tCHA: %s",
                    viewModel.getStrength(),
                    viewModel.getDexterity(),
                    viewModel.getConstitution(),
                    viewModel.getIntellect(),
                    viewModel.getWisdom(),
                    viewModel.getCharisma()));

            if (!viewModel.getLanguages().isEmpty()) {
                characterResults.add(String.format("Languages: %s", viewModel.getLanguages()));
            }
            if (!viewModel.getExtras().isEmpty()) {
                characterResults.add(String.format("Racial Extras: %s", viewModel.getExtras()));
            }
            if (!viewModel.getExtraChoice().isEmpty()) {
                characterResults.add(String.format("Racial Choice: %s", viewModel.getExtraChoice()));
            }
        }
        characterResults.add("\n");

        return characterResults;
    }
}
