import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpdateNotice extends JFrame {
    private static final long serialVersionUID = -8305252079471718733L;
    private JPanel pan1;
    private JLabel label;
    
    public UpdateNotice() {
        setTitle("HazeGaming NPC Generator");
        setLayout(new BorderLayout());
        setJMenuBar(null);
        pan1 = new JPanel();
        label = new JLabel("Checking for Updates...");
        pan1.add(label, BorderLayout.SOUTH);
        add(pan1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300,150);
    }
    public void disposeUpdate(){
        this.dispose();
    }
}
