import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TextPanel extends JPanel {

    private static final long serialVersionUID = 1560118535609406301L;
    
    private JTextArea textArea;

	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(outerBorder);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public void appendText(String text){
		textArea.append(text);
	}
	
	public void clearText(){
		textArea.setText(null);
	}
}