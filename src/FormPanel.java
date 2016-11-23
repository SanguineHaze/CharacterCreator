import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	ArrayList<String> subRaceTempList = new ArrayList<String>();
	protected boolean itemChanged;
	protected String[] subRaceCB;
	
	private JLabel numGenLabel;
	private JLabel numGenLabel2;
	private JLabel raceLabel;
	private JLabel subRaceLabel;
	private JLabel sexLabel;
	
	private JTextField numGenTextField;
	private JComboBox raceComboBox;
	private JComboBox subRaceComboBox;
	
	private FormListener formListener;
	private JButton setBtn;
	private int numGenInt;
	private int raceSelected;
	private String subRaceSelected = "";
	private JRadioButton sexM;
	static String sexMString = "M";
	private JRadioButton sexF;
	static String sexFString = "F";
	private JRadioButton sexR;
	static String sexRString = "Random";
	private String sexSelected = "";

	
	
	public String getSexSelected() {
		return sexSelected;
	}

	public String getSubRaceSelected() {
		return subRaceSelected;
	}

	public void setSubRaceSelected(String subRaceSelected) {
		this.subRaceSelected = subRaceSelected;
	}

	public int getRaceSelected() {
		return raceSelected;
	}

	public int getNumGenInt() {
		return numGenInt;
	}

	public FormPanel() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Race.raceStaticList.add(0, "Any Race"); //Set up the list to allow for a Random race
		
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		//LABELS
		numGenLabel = new JLabel("# of NPCs:");
		numGenLabel2 = new JLabel("(0 for Default)");
		raceLabel = new JLabel("Race:");
		subRaceLabel = new JLabel("Sub-Race:");
		sexLabel = new JLabel("Sex:");
		
		//FIELDS
		numGenTextField = new JTextField(10);
			//RACE ComboBox
			int count = Race.raceStaticList.size();	
			String[] raceComboBoxItems = new String[count];
			for(int i = 0; i < count; i++){
				raceComboBoxItems[i] = Race.raceStaticList.get(i);
			}
			raceComboBox = new JComboBox(raceComboBoxItems);
			raceComboBox.setSelectedItem(0);
			
			//SUBRACE ComboBox
			int subRaceCount = SubRace.subRaceStaticList2.size();
			subRaceCB = new String[subRaceCount];
			for(int i = 0; i < subRaceCount; i++){
				subRaceCB[i] = SubRace.subRaceStaticList2.get(i);
			}
			subRaceComboBox = new JComboBox(subRaceCB);
			subRaceComboBox.setSelectedItem(0);
			
			raceComboBox.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent a) {
					JComboBox raceComboBox = (JComboBox) a.getSource();
					String selected = (String) raceComboBox.getSelectedItem();
					
					//DEBUG TOOL
					//System.out.println(selected); //Check what object is selected
					
					if(!("Any Race".equals(selected))){
						//DEBUG TOOL
						//System.out.println("SubRace ActionListener If!(AnyRace); Selected item: " + selected);
						SubRace.subRaceStaticList2.add(0, "Any " + selected);
						int subRaceCount = SubRace.subRaceStaticList2.size();
						subRaceCB = new String[subRaceCount];
						//Set up a filtered list of SubRaces when Race is selected
						for (String entry: SubRace.subRaceStaticList2){
							//DEBUG TOOL
							//System.out.println("ForLoop; " + "Entry item: "+ entry + " | Selected item: "+selected);
							
							if(entry.toLowerCase().contains(selected.toLowerCase())) { 
								subRaceTempList.add(entry);
								//DEBUG TOOL
								//System.out.println("SubRace TempList added: " + subRaceTempList);
							} else if (!(entry.toLowerCase().contains(selected.toLowerCase()))){
								subRaceTempList.remove(entry);
							} 
						}
						
						int subRaceTLCount = subRaceTempList.size();
						subRaceCB = new String[subRaceTLCount];
						for(int i = 0; i < subRaceTLCount; i++){
							subRaceCB[i] = subRaceTempList.get(i);
							
							//DEBUG TOOL
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
							gbc.gridx = 1;
							gbc.gridy = 2;
							gbc.insets = new Insets(0,0,0,0);
							gbc.anchor = GridBagConstraints.FIRST_LINE_START;
							add(subRaceComboBox, gbc);
							revalidate();
							repaint();							
						} else if (subRaceTempList.isEmpty()){
							subRaceComboBox.setEnabled(false);
						}//end If subRaceTempList Is Not empty
					} else if ("Any Race".equals(selected)){
						subRaceComboBox.setEnabled(false);
					}//end If NOT "Any Race" selected				
				}//end ActionPerformed (raceComboBox)
			});//end Action Listener (raceComboBox)			
		
		sexM = new JRadioButton(sexMString);
		sexF = new JRadioButton(sexFString);
		sexR = new JRadioButton(sexRString);
		setBtn = new JButton("Set Changes!");
		
		Border innerBorder = BorderFactory.createTitledBorder("Generator Options");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(0, 0, 0, 5);
		
		// ROW 1 - # NPCs
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;		
		add(numGenLabel, gbc);
		
		gbc.gridx = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(numGenTextField, gbc);
		
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, 5);
		add(numGenLabel2, gbc);
		
		
		// ROW 2 - RACE
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(raceLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(raceComboBox, gbc);
		
		// ROW 3 - SUBRACE
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(subRaceLabel, gbc);		
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		subRaceComboBox.setEnabled(false);
		add(subRaceComboBox, gbc);

		
		// ROW 4 - SEX
		gbc.weightx = 0;
		gbc.weighty = 0.1;
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(sexLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(sexF, gbc);
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(sexF);
		gbc.gridx = 2;
		sexGroup.add(sexM);
		add(sexM, gbc);
		gbc.gridx = 3;
		sexGroup.add(sexR);		
		add(sexR, gbc);
		
		//ROW 5
		gbc.weightx = 1;
		gbc.weighty = 3;
		
		gbc.gridx = 1;
		gbc.gridy = 5;
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
					
					//DEBUG TOOL: display numGenInt
					//System.out.println(numGenInt);
					
				}				
			}
		});//end setBtn ActionListener
		
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
		
		sexF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton clicked = (JRadioButton) e.getSource();
				if(clicked == sexR){
					sexSelected = "";
					//DEBUG TOOL
					//System.out.println(sexSelected);					
				} 
			}
		});
		
	}//end FormPanel()
	
	public void setFormListener(FormListener listener){
		this.formListener = listener;
	}
	
}//end CLASS
