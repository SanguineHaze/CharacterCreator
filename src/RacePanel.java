import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class RacePanel extends JPanel{

    private static final long serialVersionUID = 3992707341917530193L;
    
    protected JButton rpSaveBtn;
    private int boxRow = 0;
    
    RacePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Race Options");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 0);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        rpSaveBtn = new JButton("Save");

        int count = GenerateSourceData.getRaceSourceStatic().size();
        for(int i=0; i<count; i++){
            int subraceCount = 0;
            ArrayList<String> races = GenerateSourceData.getRaceSourceStatic();
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.gridwidth = 1;
            //gbc.weighty = 0;
            gbc.fill = GridBagConstraints.NONE;
            int boxCol = 0;
            JCheckBox optionBox = new JCheckBox(races.get(i));
            gbc.gridy = boxRow;
            gbc.gridx = 0;
            add(optionBox, gbc);
            boxRow++;
            for(RacialStatBlock rSB: GenerateSourceData.raceStatBlock){
                if(rSB.parentID.toLowerCase().equals(races.get(i).toLowerCase())){
                    JCheckBox optionBox2 = new JCheckBox(rSB.name);
                    gbc.gridy = boxRow;
                    gbc.gridx = boxCol;
                    add(optionBox2, gbc);
                    boxCol++;
                    subraceCount++;
                    if(subraceCount == 4){
                        subraceCount = 0;
                        boxRow++;
                        boxCol = 0;
                    }
                }
            }            
            boxRow++;
            gbc.gridy = boxRow;
            gbc.gridx = 0;
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(new JSeparator(), gbc);
            boxRow++;
        }
        
        // Layout
        gbc.gridy = boxRow +1;
        add(rpSaveBtn, gbc);
    }
}
