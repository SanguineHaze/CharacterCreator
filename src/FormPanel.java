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
	
	//DECLARATIONS
	ArrayList<String> subRaceTempList = new ArrayList<>();
	ArrayList<String> lastSelected = new ArrayList<>();
	
	protected boolean itemChanged;
	protected String[] subRaceCB;
	protected String[] ageCB;
	protected String[] professionCB;
	
	private JLabel numGenLabel;
	private JLabel numGenLabel2;
	private JLabel raceLabel;
	private JLabel subRaceLabel;
	private JLabel sexLabel;
	private JLabel ageLabel;
	private JLabel professionLabel;
	private JLabel nicknameLabel;
	private JLabel nicknameLabel2;
	private JLabel detailsChanceLabel;
	private JLabel detailsChanceLabel2;
	
	private JTextField numGenTextField;
	private JTextField nicknameChance;
	private JTextField detailsChanceField;
	private JComboBox raceComboBox;
	private JComboBox subRaceComboBox;
	private JComboBox ageComboBox;
	private JComboBox professionComboBox;	
	
	private JButton setBtn;
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
	private ArrayList<String> ageRange = GenerateSourceData.ageRangeStatic;
	
	//VALIDATION
	static boolean isValidNumber(String val) {
		// return output if characters are digits
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
	public FormPanel() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		Dimension dimTF = getPreferredSize();
		dimTF.width = 8;
		setPreferredSize(dim);
		
		//LABELS
		numGenLabel = new JLabel("# of NPCs (0 for Default):");
		raceLabel = new JLabel("Race:");
		subRaceLabel = new JLabel("Sub-Race:");
		sexLabel = new JLabel("Sex:");
		ageLabel = new JLabel("Age:");
		professionLabel = new JLabel("Profession:");
		nicknameLabel = new JLabel("Nickname Chance (0 to 100):");
		detailsChanceLabel = new JLabel("Details Chance (0 to 100):");
		
		//FIELDS
		numGenTextField = new JTextField(10);
		numGenTextField.setColumns(10);
		nicknameChance = new JTextField(8);
		nicknameChance.setColumns(8);
		detailsChanceField = new JTextField(8);
		detailsChanceField.setColumns(8);
		
		//RACE ComboBox
		int count = GenerateSourceData.getRaceSourceStatic().size();	
		String[] raceComboBoxItems = new String[count];
		for(int i = 0; i < count; i++){
			raceComboBoxItems[i] = GenerateSourceData.getRaceSourceStatic().get(i);
		}
		raceComboBox = new JComboBox(raceComboBoxItems);
		raceComboBox.setSelectedItem(0);
			
		//SUBRACE ComboBox
		GenerateSourceData.subraceSourceStatic.add(0, "Any Sub-Race");
		int subRaceCount = GenerateSourceData.subraceSourceStatic.size();
		subRaceCB = new String[subRaceCount];
		for(int i = 0; i < subRaceCount; i++){
			subRaceCB[i] = GenerateSourceData.subraceSourceStatic.get(i);
		}			
		subRaceComboBox = new JComboBox(subRaceCB);
		subRaceComboBox.setSelectedItem(0);
		
		raceComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				JComboBox raceComboBox = (JComboBox) a.getSource();
				String selected = (String) raceComboBox.getSelectedItem();
				
				GenerateSourceData.subraceSourceStatic.add(0, "Any " + selected);
								
				subRaceTempList.clear();
				//DEBUG TOOL
				//System.out.println(selected); //Check what object is selected
				
				if(!("Any Race".equals(selected))){
					//DEBUG TOOL
					//System.out.println("SubRace ActionListener If!(AnyRace); Selected item: " + selected);
					
					int subRaceCount = GenerateSourceData.subraceSourceStatic.size();
					subRaceCB = new String[subRaceCount];
					//Set up a filtered list of SubRaces when Race is selected
					for(RacialStatBlock rSB: GenerateSourceData.raceStatBlock){
						if(rSB.parentID.toLowerCase().equals(selected.toLowerCase())){
							subRaceTempList.add(rSB.name);
						}
					}
						
					subRaceTempList.add(0, "Any " + selected);
					int subRaceTLCount = subRaceTempList.size();
					subRaceCB = new String[subRaceTLCount];
					for(int i = 0; i < subRaceTLCount; i++){
						subRaceCB[i] = subRaceTempList.get(i);
						
						//DEBUG TOOL - SUBRACE COMBOBOX
						//System.out.println("SubRace ComboBox Items: " + subRaceCB[i]);
					}
					
					if(!(subRaceTempList.isEmpty())){
						subRaceComboBox.setEnabled(true);
						//Remove and rebuild the SubRace list
						remove(subRaceComboBox);
						subRaceComboBox = new JComboBox(subRaceCB);
						subRaceComboBox.setSelectedItem(0);	
						gbc.weightx = 1;
						gbc.weighty = 0.1;
						gbc.gridx = 0;
						gbc.gridy = 5;
						gbc.insets = new Insets(0,5,0,0);
						gbc.anchor = GridBagConstraints.FIRST_LINE_START;
						add(subRaceComboBox, gbc);
						revalidate();
						repaint();							
					} else if (subRaceTempList.isEmpty()){
						subRaceComboBox.setEnabled(false);
					} else if ("Any Race".equals(selected)){
						subRaceComboBox.setEnabled(false);
					}
				} else if("Any Race".equals(selected)){
					remove(subRaceComboBox);
					add(subRaceComboBox);
					subRaceComboBox.setSelectedIndex(0);
					subRaceComboBox.setEnabled(false);
				}
				
				GenerateSourceData.subraceSourceStatic.remove(0); //Remove the entry we added earlier
			}			
		});		
		
		sexM = new JRadioButton(sexMString);
		sexF = new JRadioButton(sexFString);
		sexR = new JRadioButton(sexRString);
		setBtn = new JButton("Set Changes!");
		
		ageRange.add(0, "Any Age");
		int ageCount = ageRange.size();
		ageCB = new String[ageCount];
		for(int ii = 0; ii < ageCount; ii++){
			ageCB[ii] = ageRange.get(ii);
		}
		ageComboBox = new JComboBox(ageCB);
		
		GenerateSourceData.adultProfessionSourceStatic.sort(null); //Sort the list alphabetically
		GenerateSourceData.adultProfessionSourceStatic.add(0, "Any Profession"); //Fuck up the sorting immediately after!		
		int professionCount = GenerateSourceData.adultProfessionSourceStatic.size();
		professionCB = new String[professionCount];
		for(int pi=0; pi < professionCount; pi++){
			professionCB[pi] = GenerateSourceData.adultProfessionSourceStatic.get(pi);
		}
		professionComboBox = new JComboBox(professionCB);
		
		saveCheckBox = new JCheckBox("Save Next Results");
		generateStats = new JCheckBox("Generate Stats");
		
		
		//SET BORDERS! MAKE BOXES!
		Border innerBorder = BorderFactory.createTitledBorder("Generator Options");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 0, 0, 5);
		
		// ROW 1 - # NPCs
		gbc.anchor = GridBagConstraints.RELATIVE;
		gbc.gridx = 0; //Row 1 is actually Row 0. Don't question it, just add to it.
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
		add(raceLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0,5,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(raceComboBox, gbc);
		
		// ROW 3 - SUBRACE
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0,5,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(subRaceLabel, gbc);		
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0,5,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		subRaceComboBox.setEnabled(false);
		add(subRaceComboBox, gbc);

		
		// ROW 4 - SEX
		gbc.weightx = 0;
		gbc.weighty = 0.1;
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.LINE_START; //Line up the Label to the radio buttons
		gbc.insets = new Insets(0,5,0,0);
		add(sexLabel, gbc);
		
		ButtonGroup sexGroup = new ButtonGroup(); //group the selections
		sexGroup.add(sexF);
		sexGroup.add(sexM);
		sexGroup.add(sexR);
		
		gbc.gridy = 7;
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
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(ageComboBox, gbc);
		
		//ROW 6 - PROFESSION
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(professionLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 11;
		add(professionComboBox, gbc);
		
		//ROW 7 - NICKNAMES
		gbc.gridx = 0;
		gbc.gridy = 12;
		add(nicknameLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(nicknameChance, gbc);
		
		
		//ROW 8 - ADDITIONAL DETAILS 
		gbc.gridx = 0;
		gbc.gridy= 14;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(detailsChanceLabel, gbc);
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 15;
		add(detailsChanceField, gbc);
		
		//ROW 9 - GENERATE STATS	
		gbc.gridx = 0;
		gbc.gridy = 16;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(generateStats, gbc);
		generateStats.setSelected(true);
		
		//ROW 9 - SAVE BOX	
		gbc.gridx = 0;
		gbc.gridy = 17;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(saveCheckBox, gbc);
		
		
		//ROW 9 - SET BUTTON
		gbc.weightx = 1;
		gbc.weighty = 3;
		
		gbc.gridx = 0;
		gbc.gridy = 18;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(setBtn, gbc);

		setBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if(clicked == setBtn){
					String numGenString = numGenTextField.getText();
					if(!(numGenString.isEmpty())){
						numGenInt = Integer.parseInt(numGenString);
					}
					raceSelected = raceComboBox.getSelectedIndex();
					subRaceSelected = subRaceComboBox.getSelectedItem().toString();
					ageSelected = ageComboBox.getSelectedItem().toString();
					professionSelected = professionComboBox.getSelectedItem().toString();
					String nicknameChanceTransform = nicknameChance.getText();
					if(!(nicknameChanceTransform.isEmpty())){
						if(isValidNumber(nicknameChanceTransform)){
							nicknameChanceInt = Integer.parseInt(nicknameChanceTransform);
						} 
					} // no need to handle unset or invalid Nickname chance here - we can do that in MainFrame when Generate is clicked.
					String detailsChanceTransform = detailsChanceField.getText();
					if(!(detailsChanceTransform.isEmpty())){
						if(isValidNumber(detailsChanceTransform)){
							detailsChance = Integer.parseInt(detailsChanceTransform);
						}
					} //again, no need to handle unset or invalid Details here. We'll do this in MainFrame the same as Nickname.
					
					//DEBUG TOOL: display numGenInt
					//System.out.println(numGenInt);
					
				}				
			}
		});
		
		sexM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton clicked = (JRadioButton) e.getSource();
				if(clicked == sexM){
					sexSelected = "Male";
					//DEBUG TOOL
					//System.out.println(sexSelected);					
				} 
			}
		});
		
		sexF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton clicked = (JRadioButton) e.getSource();
				if(clicked == sexF){
					sexSelected = "Female";
					//DEBUG TOOL
					//System.out.println(sexSelected);					
				} 
			}
		});
		
		sexR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton clicked = (JRadioButton) e.getSource();
				if(clicked == sexR){
					sexSelected = ""; //Blank sex forces the constructor in Name.java to flip a boolean to choose randomly.
					//DEBUG TOOL
					//System.out.println(sexSelected);					
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
	
}//end CLASS
