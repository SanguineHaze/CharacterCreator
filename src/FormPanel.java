import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel numGenLabel;
	private JLabel numGenLabel2;
	private JLabel raceLabel;
	private JLabel subRaceLabel;
	
	private JTextField numGenTextField;
	private JComboBox raceComboBox;
	private JComboBox subRaceComboBox;
	
	private FormListener formListener;
	private JButton setBtn;
	private int numGenInt;
	private int raceSelected;
	
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
		
		//FIELDS
		numGenTextField = new JTextField(10);
		
		int count = Race.raceStaticList.size();	
		String[] raceComboBoxItems = new String[count];
		for(int i = 0; i < count; i++){
			raceComboBoxItems[i] = Race.raceStaticList.get(i);
		}
		raceComboBox = new JComboBox(raceComboBoxItems);
		raceComboBox.setSelectedItem(0);
		
		int subRaceCount = SubRace.subRaceStaticList2.size();
		String[] subRaceCB = new String[subRaceCount];
		for(int i = 0; i < subRaceCount; i++){
			subRaceCB[i] = SubRace.subRaceStaticList2.get(i);
		}
		subRaceComboBox = new JComboBox(subRaceCB);
		subRaceComboBox.setSelectedItem(0);

		
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
		subRaceLabel.setVisible(true); // true false
		add(subRaceLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		subRaceComboBox.setVisible(true); // true false
		add(subRaceComboBox, gbc);
		
		// ROW 4
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
					
					//DEBUG TOOL: display numGenInt
					//System.out.println(numGenInt);
				}				
			}
		});//end setBtn ActionListener
	}//end FormPanel()
	
	public void setFormListener(FormListener listener){
		this.formListener = listener;
	}
	
}//end CLASS
