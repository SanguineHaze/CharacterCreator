import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class RacePanel extends JPanel{

    RacePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Race Options");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
