import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
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
		
				//Not Working
				int count = Race.raceStaticList.size();	
				String[] raceComboBoxItems = new String[count];
				for(int i = 0; i < count; i++){
					raceComboBoxItems[i] = Race.raceStaticList.get(i);
				}
				raceComboBox = new JComboBox(raceComboBoxItems);

		
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
					numGenInt = Integer.parseInt(numGenString);
					
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
