import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class RacePanel extends JPanel{

    private static final long serialVersionUID = 3992707341917530193L;
    
    protected JButton rpSaveBtn;
    private int boxRow = 0;
    protected ArrayList<String> selectedRaces = new ArrayList<>();
    
    RacePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Border innerBorder = BorderFactory.createTitledBorder("Race Options");
        setBorder(innerBorder);
        
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
            optionBox.setName(races.get(i));
            optionBox.addItemListener(boxListener);
            if(optionBox.getName().toLowerCase().equals("any race")){
                optionBox.setSelected(true);
            }
            
            gbc.gridy = boxRow;
            gbc.gridx = 0;
            add(optionBox, gbc);
            boxRow++;
            for(RacialStatBlock rSB: GenerateSourceData.raceStatBlock){
                if(rSB.parentID.toLowerCase().equals(races.get(i).toLowerCase())){
                    JCheckBox optionBox2 = new JCheckBox(rSB.name);
                    optionBox2.setName(rSB.name);
                    optionBox2.addItemListener(boxListener);
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
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(new JSeparator(), gbc);
            boxRow++;
        }
        
        // LAYOUT
        gbc.gridy = boxRow +1;
        add(rpSaveBtn, gbc);
        
    }
    // LISTENER
    ItemListener boxListener = new ItemListener(){
        public void itemStateChanged(ItemEvent e) {
            JCheckBox check = (JCheckBox)e.getSource();
            String name = check.getName();
            if(e.getStateChange() == ItemEvent.SELECTED){
                selectedRaces.add(name);
            } else if (e.getStateChange() == ItemEvent.DESELECTED){
                selectedRaces.remove(name);
            }
        }
    };
    
    // GETTERS & SETTERS
    public ArrayList<String> getSelectedRaces() {
        return selectedRaces;
    }
    public void setSelectedRaces(ArrayList<String> selectedRaces) {
        this.selectedRaces = selectedRaces;
    }
}
