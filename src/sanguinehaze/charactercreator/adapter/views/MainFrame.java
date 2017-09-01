package sanguinehaze.charactercreator.adapter.views;

import sanguinehaze.charactercreator.AdditionalFeatures;
import sanguinehaze.charactercreator.adapter.views.mappers.CharacterResultViewModelMapper;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterGenerationRequestViewModel;
import sanguinehaze.charactercreator.adapter.views.viewmodels.CharacterResultViewModel;
import sanguinehaze.charactercreator.application.CharacterResultProvider;
import sanguinehaze.charactercreator.domain.AgeGenerator;
import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.Profession;
import sanguinehaze.charactercreator.Race;
import sanguinehaze.charactercreator.RacialStatBlockBuilder;
import sanguinehaze.charactercreator.SubRace;
import sanguinehaze.charactercreator.WriteToFile;
import sanguinehaze.charactercreator.domain.CharacterCreatorRandom;
import sanguinehaze.charactercreator.domain.NameBuilder;
import sanguinehaze.charactercreator.domain.dtos.FullName;
import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.swing.*;

public class MainFrame extends JFrame {
	
    private static final long serialVersionUID = 3320499509997731807L;
    
    private List<String> characterResults = new ArrayList<>();
    private String myAge;

    private TextPanel textPanel;
    private JButton generateBtn;

    private FormPanel formPanel;
    private RacePanel racePanel;

    private JScrollPane scrollTemplate;
    private CharacterCreatorRandom rand;
    private CharacterResultViewModelMapper characterResultViewModelMapper;
    private CharacterResultProvider characterResultProvider;

    public MainFrame(
            GenerateSourceData data,
            NameBuilder nameBuilder,
            CharacterResultViewModelMapper characterResultViewModelMapper,
            CharacterResultProvider characterResultProvider,
            CharacterCreatorRandom random) {

        // LAYOUT SECTION
        super("HazeGaming NPC Generator");

        this.characterResultViewModelMapper = characterResultViewModelMapper;
        this.characterResultProvider = characterResultProvider;
        this.rand = random;

        setLayout(new BorderLayout());

        JPanel formTemplate = new JPanel();
        scrollTemplate = new JScrollPane();
        textPanel = new TextPanel();
        formPanel = new FormPanel(data);
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
                                            request,
                                            myAge),
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
                    WriteToFile thisWrite = new WriteToFile(characterResults);
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
        int randChoice = rand.nextInt(chars.size());
        viewModel.setSelectedRace(chars.get(randChoice));
        viewModel.setSaveNext(formPanel.isSaveNext());
        viewModel.setIncludeStats(formPanel.isIncludeStats());
        viewModel.setNumGenInt(formPanel.getNumGenInt());
        viewModel.setNicknameChance(formPanel.getNicknameChanceInt());
        viewModel.setDetailsChance(formPanel.getDetailsChance());
        viewModel.setItemChance(formPanel.getItemChance());

        return viewModel;
    }

}
