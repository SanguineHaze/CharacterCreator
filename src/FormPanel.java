import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel numGenLabel;
	private JLabel raceLabel;
	
	private JTextField numGenTextField;
	private JComboBox raceComboBox;
	
	private FormListener formListener;
	private JButton setBtn;
	private int numGenInt;
	private String userRace;
	
	public String getUserRace() {
		return userRace;
	}

	public int getNumGenInt() {
		return numGenInt;
	}

	public FormPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 280;
		setPreferredSize(dim);
		
		//LABELS
		numGenLabel = new JLabel("# of NPCs: ");
		raceLabel = new JLabel("Race:");
		
		//FIELDS
		numGenTextField = new JFormattedTextField();
		numGenTextField.setColumns(7);
		String[] raceCBA = {"Aarakocra", "Aasimar", "Dragonborn", "Dwarf", "Elf", "Gnome", "Genasi", "Goliath", "Half-Orc", "Human", "Halfling", "Half-Elf", "Teifling"};
		raceComboBox = new JComboBox(raceCBA);
		
		setBtn = new JButton("Set Changes!");
		
		Border innerBorder = BorderFactory.createTitledBorder("Generator Options");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		// ROW 1
		add(numGenLabel);
		add(numGenTextField);
		
		// ROW 2
		add(raceLabel);
		add(raceComboBox);
		
		// ROW 3
		add(setBtn);

		
		setBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if(clicked == setBtn){
					String numGenString = numGenTextField.getText();
					if(!(numGenString.isEmpty())){
						numGenInt = Integer.parseInt(numGenString);
					}					
					int userRace2 = raceComboBox.getSelectedIndex();
					System.out.println(userRace2);
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
