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
	
    private static final long serialVersionUID = -8171551293250183053L;
    
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
		
		pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		
		pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		
		infoPane = new JEditorPane();
		infoPane.setContentType("text/html");
		
		scp = new JScrollPane();
		scp.setViewportView(infoPane);
		
		okBtn = new JButton("Update");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateInfo.this.dispose();
			}
		});
		
		//set up Pan2
		pan2.add(okBtn);
		pan2.add(cancelBtn);
		pan1.add(pan2, BorderLayout.SOUTH);
		pan1.add(scp, BorderLayout.CENTER);
		this.add(pan1);
		pack();
		this.setSize(450, 300);
		setVisible(true);
		toFront();
	}
	
	private void update(){
		String[] run = {"java","-jar","Updater/update.jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
	}
	
}
