package sanguinehaze.charactercreator.adapter.views;

import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.adapter.persistence.repositories.AgeRepo;
import sanguinehaze.charactercreator.domain.dtos.AdultProfessionList;
import sanguinehaze.charactercreator.domain.dtos.Age;
import sanguinehaze.common.utilities.TypeValidationUtil;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

    private static final long serialVersionUID = -8719247449665634249L;

    //DECLARATIONS
    ArrayList<String> subRaceTempList = new ArrayList<>();
    ArrayList<String> lastSelected = new ArrayList<>();

    protected boolean itemChanged;
    protected String[] subRaceCB;
    private String[] ageCB;

    private JTextField numGenTextField;
    private JTextField nicknameChance;
    private JTextField detailsChanceField;
    private JTextField itemChanceField;
    private JComboBox<String> ageComboBox;
    private JComboBox<String> professionComboBox;

    JButton raceBtn;
    private int numGenInt;

    private JRadioButton sexM;
    private JRadioButton sexF;
    private JRadioButton sexR;

    private int raceSelected;
    private String subRaceSelected = "";
    private String sexSelected = "";
    private String ageSelected = "";
    private String professionSelected = "";
    private boolean saveNext;
    private boolean includeStats = true;

    private int nicknameChanceInt = -1;
    private int detailsChance;
    private int itemChance;

    //THE MAGIC HAPPENS HERE. ALSO, HERE THERE BE DRAGONS.
    public FormPanel(
            GenerateSourceData data,
            AgeRepo ageRepo) {

        List<Age> allAges = ageRepo.getAllAges();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension dim = new Dimension();
        dim.setSize(230, 400);
        setPreferredSize(dim);

        //LABELS
        JLabel numGenLabel = new JLabel("# of NPCs (0 for Default):");
        JLabel sexLabel = new JLabel("Sex:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel professionLabel = new JLabel("Profession:");
        JLabel nicknameLabel = new JLabel("Nickname Chance (0 to 100):");
        JLabel detailsChanceLabel = new JLabel("Details Chance (0 to 100):");
        JLabel itemChanceLabel = new JLabel("Item Chance (0 to 100):");

        //FIELDS
        numGenTextField = new JTextField(10);
        numGenTextField.setColumns(10);
        nicknameChance = new JTextField(10);
        nicknameChance.setColumns(10);
        detailsChanceField = new JTextField(10);
        detailsChanceField.setColumns(10);
        itemChanceField = new JTextField(10);
        itemChanceField.setColumns(10);

        String sexMString = "M";
        sexM = new JRadioButton(sexMString);
        String sexFString = "F";
        sexF = new JRadioButton(sexFString);
        String sexRString = "Random";
        sexR = new JRadioButton(sexRString);
        raceBtn = new JButton("Race Options");

        getAgeCheckBoxArray(allAges);

        ageComboBox = new JComboBox<>(ageCB);
        AdultProfessionList adultProfessionSourceStatic = data.getAdultProfessionList();
        adultProfessionSourceStatic.sortAlphabetically();
        adultProfessionSourceStatic.addToTop("Any Profession");
        int professionCount = adultProfessionSourceStatic.size();
        String[] professionCB = new String[professionCount];
        for(int pi=0; pi < professionCount; pi++){
            professionCB[pi] = adultProfessionSourceStatic.get(pi);
        }
        professionComboBox = new JComboBox<>(professionCB);

        JCheckBox saveCheckBox = new JCheckBox("Save Next Results");
        JCheckBox generateStats = new JCheckBox("Generate Stats");


        //SET BORDERS! MAKE BOXES!
        Border innerBorder = BorderFactory.createTitledBorder("Generator Options");
        setBorder(innerBorder);

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 0, 0, 5);

        // ROW 1 - # NPCs
        gbc.anchor = GridBagConstraints.RELATIVE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(numGenLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(numGenTextField, gbc);

        // ROW 2 - RACE
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,5,0,0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(raceBtn, gbc);

        // ROW 4 - SEX
        gbc.weightx = 0;
        gbc.weighty = 0.1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START; //Line up the Label to the radio buttons
        gbc.insets = new Insets(0,5,0,0);
        add(sexLabel, gbc);

        ButtonGroup sexGroup = new ButtonGroup(); //group the selections
        sexGroup.add(sexF);
        sexGroup.add(sexM);
        sexGroup.add(sexR);

        gbc.gridy = 4;
        gbc.insets = new Insets(0,0,0,2); //Add a bit of padding
        sexR.setMargin(gbc.insets);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST; //sexF to display LEFT column
        add(sexF, gbc);
        gbc.insets = new Insets(0,0,0,20); //Add a bunch of padding to sexM (displays on right)
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST; //sexM to display RIGHT column
        add(sexM, gbc);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; //sexR to display CENTER column | This prevents overlaps or collisions.
        sexR.setSelected(true);
        add(sexR, gbc);

        //ROW 5 - AGE
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(ageComboBox, gbc);

        //ROW 6 - PROFESSION
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(professionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(professionComboBox, gbc);

        //ROW 7 - NICKNAMES
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(nicknameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nicknameChance, gbc);


        //ROW 8 - ADDITIONAL DETAILS
        gbc.gridx = 0;
        gbc.gridy= 11;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(detailsChanceLabel, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(detailsChanceField, gbc);

        //ITEMS
        gbc.gridx = 0;
        gbc.gridy= 13;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(itemChanceLabel, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 14;
        add(itemChanceField, gbc);

        //ROW 9 - GENERATE STATS
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(generateStats, gbc);
        generateStats.setSelected(true);

        //ROW 9 - SAVE BOX
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveCheckBox, gbc);

        sexM.addActionListener(e -> {
            JRadioButton clicked = (JRadioButton) e.getSource();
            if(clicked == sexM){
                sexSelected = "Male";
            }
        });

        sexF.addActionListener(e -> {
            JRadioButton clicked = (JRadioButton) e.getSource();
            if(clicked == sexF){
                sexSelected = "Female";
            }
        });

        sexR.addActionListener(e -> {
            JRadioButton clicked = (JRadioButton) e.getSource();
            if(clicked == sexR){
                sexSelected = "";
            }
        });

        saveCheckBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.DESELECTED){
                saveNext = false;
            } else if (e.getStateChange() == ItemEvent.SELECTED){
                saveNext = true;
            }
        });

        generateStats.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.DESELECTED){
                includeStats = false;
            } else if (e.getStateChange() == ItemEvent.SELECTED){
                includeStats = true;
            }
        });

    }

    private void getAgeCheckBoxArray(List<Age> allAges) {
        ageCB = new String[allAges.size()];

        //Will not work
        //Index 0 is set to Any Age, then promptly overwritten by ii 0 = child.
        //Blair: For now, removed the +1 to size, and added Any Age to InMemoryAgeRepo.
        //We can revisit this concept later, as I don't see a workaround without an ArrayList
        
        //ageCB[0] = "Any Age";

        for(int ii = 0; ii < allAges.size(); ii++){
            ageCB[ii] = allAges.get(ii).getName();
        }
    }

    //GETTERS & SETTERS//
    void getFormChanges() {
        String numGenString = numGenTextField.getText();
        if (!(numGenString.isEmpty())) {
            numGenInt = Integer.parseInt(numGenString);
        }

        ageSelected = ageComboBox.getSelectedItem().toString();
        professionSelected = professionComboBox.getSelectedItem().toString();
        String nicknameChanceTransform = nicknameChance.getText();
        if (!(nicknameChanceTransform.isEmpty())) {
            if (TypeValidationUtil.isValidNumber(nicknameChanceTransform)) {
                nicknameChanceInt = Integer.parseInt(nicknameChanceTransform);
            }
        }
        String detailsChanceTransform = detailsChanceField.getText();
        if (!(detailsChanceTransform.isEmpty())) {
            if (TypeValidationUtil.isValidNumber(detailsChanceTransform)) {
                detailsChance = Integer.parseInt(detailsChanceTransform);
            }
        }

        String itemChanceTransform = itemChanceField.getText();
        if (!(itemChanceTransform.isEmpty())) {
            if (TypeValidationUtil.isValidNumber(itemChanceTransform)) {
                itemChance = Integer.parseInt(itemChanceTransform);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public int getItemChance() {
        return itemChance;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isIncludeStats() {
        return includeStats;
    }

    public void setIncludeStats(boolean includeStats) {
        this.includeStats = includeStats;
    }

    @SuppressWarnings("WeakerAccess")
    public int getDetailsChance() {
        return detailsChance;
    }

    @SuppressWarnings("WeakerAccess")
    public int getNicknameChanceInt() {
        return nicknameChanceInt;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isSaveNext() {
        return saveNext;
    }

    public String getProfessionSelected() {
        return professionSelected;
    }

    public String getAgeSelected() {
        return ageSelected;
    }

    public String getSexSelected() {
        return sexSelected;
    }

    public String getSubRaceSelected() {
        return subRaceSelected;
    }

    public int getRaceSelected() {
        return raceSelected;
    }

    @SuppressWarnings("WeakerAccess")
    public int getNumGenInt() {
        return numGenInt;
    }

    public void setSubRaceSelected(String subRaceSelected) {
        this.subRaceSelected = subRaceSelected;
    }

}
