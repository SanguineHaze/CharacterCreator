import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel numGenLabel;
	private JTextField numGenTextField;
	private FormListener formListener;
	private JButton setBtn;
	private int numGenInt;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 280;
		setPreferredSize(dim);
		
		numGenLabel = new JLabel("# of NPCs: ");
		numGenTextField = new JFormattedTextField();
		numGenTextField.setColumns(10);
		
		setBtn = new JButton("Set Changes!");
		
		Border innerBorder = BorderFactory.createTitledBorder("Generator Options");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		add(numGenLabel);
		add(numGenTextField);
		add(setBtn);
		
		setBtn.addActionListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				numGenInt = e.getNumGenInt();
				System.out.println(numGenInt);
			}
		});
	}
	
	public void setFormListener(FormListener listener){
		this.formListener = listener;
	}
	
}//end CLASS
