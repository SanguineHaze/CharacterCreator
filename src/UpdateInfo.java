import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UpdateInfo extends JFrame{
	
	private JEditorPane infoPane;
	private JScrollPane scp;
	private JButton okBtn;
	private JButton cancelBtn;
	private JPanel pan1;
	private JPanel pan2;
	
	public UpdateInfo(String info) {
		initiateComponents();
		infoPane.setText(info);		
	}
	
	private void initiateComponents(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("New Update Found!");
	}
	
}
