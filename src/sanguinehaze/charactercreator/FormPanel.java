package sanguinehaze.charactercreator;

import sanguinehaze.charactercreator.data.dtos.AdultProfessionList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
    protected String[] ageCB;
    protected String[] professionCB;

    private JLabel numGenLabel;
    private JLabel sexLabel;
    private JLabel ageLabel;
    private JLabel professionLabel;
    private JLabel nicknameLabel;
    private JLabel detailsChanceLabel;

    private JTextField numGenTextField;
    private JTextField nicknameChance;
    private JTextField detailsChanceField;
    private JComboBox<String> ageComboBox;
    private JComboBox<String> professionComboBox;

    protected JButton raceBtn;
    private int numGenInt;

    private JCheckBox saveCheckBox;
    private JCheckBox generateStats;

    private JRadioButton sexM;
    static String sexMString = "M";
    private JRadioButton sexF;
    static String sexFString = "F";
    private JRadioButton sexR;
    static String sexRString = "Random";

    private int raceSelected;
    private String subRaceSelected = "";
    private String sexSelected = "";
    private String ageSelected = "";
    private String professionSelected = "";
    private boolean saveNext;
    private boolean includeStats = true;

    protected int nicknameChanceInt = -1;
    protected int detailsChance;
    private ArrayList<String> ageRange;

    //VALIDATION
    static boolean isValidNumber(String val) {
        Boolean output = false;
        try {
            Integer.parseInt(val);
            output = true;
        } catch (Exception ex) {
            output = false;
        }
        return output;
    }

    //THE MAGIC HAPPENS HERE. ALSO, HERE THERE BE DRAGONS.
    public FormPanel(GenerateSourceData data) {

        ageRange = data.getAgeRangeStatic();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension dim = new Dimension();
        dim.setSize(230, 400);
        setPreferredSize(dim);

        //LABELS
        numGenLabel = new JLabel("# of NPCs (0 for Default):");
        sexLabel = new JLabel("Sex:");
        ageLabel = new JLabel("Age:");
        professionLabel = new JLabel("Profession:");
        nicknameLabel = new JLabel("Nickname Chance (0 to 100):");
        detailsChanceLabel = new JLabel("Details Chance (0 to 100):");

        //FIELDS
        numGenTextField = new JTextField(10);
        numGenTextField.setColumns(10);
        nicknameChance = new JTextField(10);
        nicknameChance.setColumns(10);
        detailsChanceField = new JTextField(10);
        detailsChanceField.setColumns(10);

        sexM = new JRadioButton(sexMString);
        sexF = new JRadioButton(sexFString);
        sexR = new JRadioButton(sexRString);
        raceBtn = new JButton("Race Options");

        ageRange.add(0, "Any Age");
        int ageCount = ageRange.size();
        ageCB = new String[ageCount];
        for(int ii = 0; ii < ageCount; ii++){
            ageCB[ii] = ageRange.get(ii);
        }
        ageComboBox = new JComboBox<String>(ageCB);
        AdultProfessionList adultProfessionSourceStatic = data.getAdultProfessionList();
        adultProfessionSourceStatic.sortAlphabetically();
        adultProfessionSourceStatic.addToTop("Any Profession");
        int professionCount = adultProfessionSourceStatic.size();
        professionCB = new String[professionCount];
        for(int pi=0; pi < professionCount; pi++){
            professionCB[pi] = adultProfessionSourceStatic.get(pi);
        }
        professionComboBox = new JComboBox<String>(professionCB);

        saveCheckBox = new JCheckBox("Save Next Results");
        generateStats = new JCheckBox("Generate Stats");


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

        //ROW 9 - GENERATE STATS
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(generateStats, gbc);
        generateStats.setSelected(true);

        //ROW 9 - SAVE BOX
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveCheckBox, gbc);

        sexM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton clicked = (JRadioButton) e.getSource();
                if(clicked == sexM){
                    sexSelected = "Male";
                }
            }
        });

        sexF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton clicked = (JRadioButton) e.getSource();
                if(clicked == sexF){
                    sexSelected = "Female";
                }
            }
        });

        sexR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton clicked = (JRadioButton) e.getSource();
                if(clicked == sexR){
                    sexSelected = "";
                }
            }
        });

        saveCheckBox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED){
                    saveNext = false;
                } else if (e.getStateChange() == ItemEvent.SELECTED){
                    saveNext = true;
                }
            }
        });

        generateStats.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED){
                    includeStats = false;
                } else if (e.getStateChange() == ItemEvent.SELECTED){
                    includeStats = true;
                }
            }
        });

    }

    //GETTERS & SETTERS//
    public void getFormChanges() {
        String numGenString = numGenTextField.getText();
        if (!(numGenString.isEmpty())) {
            numGenInt = Integer.parseInt(numGenString);
        }

        ageSelected = ageComboBox.getSelectedItem().toString();
        professionSelected = professionComboBox.getSelectedItem().toString();
        String nicknameChanceTransform = nicknameChance.getText();
        if (!(nicknameChanceTransform.isEmpty())) {
            if (isValidNumber(nicknameChanceTransform)) {
                nicknameChanceInt = Integer.parseInt(nicknameChanceTransform);
            }
        }
        String detailsChanceTransform = detailsChanceField.getText();
        if (!(detailsChanceTransform.isEmpty())) {
            if (isValidNumber(detailsChanceTransform)) {
                detailsChance = Integer.parseInt(detailsChanceTransform);
            }
        }
    }

    public boolean isIncludeStats() {
        return includeStats;
    }

    public void setIncludeStats(boolean includeStats) {
        this.includeStats = includeStats;
    }

    public int getDetailsChance() {
        return detailsChance;
    }

    public int getNicknameChanceInt() {
        return nicknameChanceInt;
    }

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

    public int getNumGenInt() {
        return numGenInt;
    }

    public void setSubRaceSelected(String subRaceSelected) {
        this.subRaceSelected = subRaceSelected;
    }

}
