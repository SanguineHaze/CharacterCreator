package sanguinehaze.charactercreator.adapter.views;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.WriteToFile;
import sanguinehaze.charactercreator.adapter.views.factories.FormPanelFactory;
import sanguinehaze.charactercreator.adapter.views.mappers.CharacterResultViewModelMapper;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterGenerationRequestViewModel;
import sanguinehaze.charactercreator.application.CharacterResultProvider;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;
import sanguinehaze.charactercreator.domain.NameBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFrame extends JFrame {
	
    private static final long serialVersionUID = 3320499509997731807L;
    
    private final List<String> characterResults = new ArrayList<>();

    private final TextPanel textPanel;
    private final JButton generateBtn;

    private final FormPanel formPanel;
    private final RacePanel racePanel;

    private final JScrollPane scrollTemplate;
    private final CharacterCreatorRandom rand;
    private final CharacterResultProvider characterResultProvider;

    public MainFrame(
            GenerateSourceData data,
            NameBuilder nameBuilder,
            CharacterResultViewModelMapper characterResultViewModelMapper,
            CharacterResultProvider characterResultProvider,
            CharacterCreatorRandom random) {

        // LAYOUT SECTION
        super("HazeGaming NPC Generator");

        this.characterResultProvider = characterResultProvider;
        this.rand = random;

        setLayout(new BorderLayout());

        JPanel formTemplate = new JPanel();
        scrollTemplate = new JScrollPane();
        textPanel = new TextPanel();
        formPanel = FormPanelFactory.create(data);
        racePanel = new RacePanel();
        generateBtn = new JButton("GENERATE!");

        add(formTemplate, BorderLayout.WEST);
        formTemplate.add(formPanel);
        add(textPanel, BorderLayout.CENTER);
        add(generateBtn, BorderLayout.SOUTH);

        setSize(1200, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        formPanel.raceBtn.addActionListener(e -> {
            JButton clicked = (JButton) e.getSource();
            if(clicked == formPanel.raceBtn){
                formPanel.setVisible(false);
                textPanel.setVisible(false);
                generateBtn.setVisible(false);
                add(scrollTemplate);
                scrollTemplate.setViewportView(racePanel);
                racePanel.setVisible(true);
                setVisible(true);
            }
        });
        
        racePanel.rpSaveBtn.addActionListener(e -> {
            JButton clicked = (JButton) e.getSource();
            if(clicked == racePanel.rpSaveBtn){
                racePanel.setVisible(false);
                formPanel.setVisible(true);
                textPanel.setVisible(true);
                generateBtn.setVisible(true);
            }
        });

        // GENERATE BUTTON SECTION
        generateBtn.addActionListener(ev -> {
            JButton clicked = (JButton) ev.getSource();
            if (clicked == generateBtn) {

                textPanel.clearText(); // Operation 'Clean Slate' is a go.
                long startTime = System.nanoTime();

                CharacterGenerationRequestViewModel request = getUiViewModel(formPanel, racePanel);

                textPanel.appendText("OUTPUTTING " + request.getNumGenInt() + " CHARACTER(S):" + "\n");
                textPanel.appendText("\n");

                for (int i = 0; i < request.getNumGenInt(); i++) {
                    characterResults.addAll(
                            characterResultViewModelMapper.viewModelToStringArray(
                                    this.characterResultProvider.generateCharacter(
                                            data,
                                            nameBuilder,
                                            request),
                                    request.isIncludeStats()
                            )
                    );
                }

                // PRINT RESULTS OUT
                for (String out : characterResults) {
                    if (!Objects.equals(out, "\n")) {
                        textPanel.appendText(out + "\n");
                    } else {
                        textPanel.appendText(out);
                    }
                    System.out.println(out);
                }

                // WRITE RESULTS TO FILE
                if (request.isSaveNext()) {
                    WriteToFile thisWrite = new WriteToFile(characterResults, "characterResults");
                    textPanel.appendText(thisWrite.getWTFLocation() + "\n");
                    textPanel.appendText("\n");
                }

                // NUKE LIST
                characterResults.clear();

                // SPIT OUT TIME
                long endTime = System.nanoTime();
                System.out.println("Runtime: " + ((endTime - startTime) / 1000000000.0) + " s");

                textPanel.appendText("Runtime: " + ((endTime - startTime) / 1000000000.0) + " s");
                textPanel.appendText("\n");
            }
        });
    }

    private CharacterGenerationRequestViewModel getUiViewModel(FormPanel formPanel, RacePanel racePanel) {
        CharacterGenerationRequestViewModel viewModel = new CharacterGenerationRequestViewModel();
        formPanel.getFormChanges();

        ArrayList<String> chars = racePanel.getSelectedRaces();
        viewModel.setSelectedRaces(chars);

        viewModel.setSaveNext(formPanel.isSaveNext());
        viewModel.setIncludeStats(formPanel.isIncludeStats());
        viewModel.setNumGenInt(formPanel.getNumGenInt());
        viewModel.setNicknameChance(formPanel.getNicknameChanceInt());
        viewModel.setDetailsChance(formPanel.getDetailsChance());
        viewModel.setItemChance(formPanel.getItemChance());
        viewModel.setSelectedSex(formPanel.getSexSelected());
        viewModel.setSelectedAge(formPanel.getAgeSelected());
        viewModel.setSelectedProfession(formPanel.getProfessionSelected());

        return viewModel;
    }

}
