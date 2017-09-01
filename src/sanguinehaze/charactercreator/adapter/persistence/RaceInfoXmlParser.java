package sanguinehaze.charactercreator.adapter.persistence;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sanguinehaze.charactercreator.GenerateSourceData;
import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;

import java.util.ArrayList;

public class RaceInfoXmlParser {
    public ArrayList<RacialStatBlock> parseRacialStatSourceData(NodeList nodeList){

        ArrayList<RacialStatBlock> races = new ArrayList<>();

        //start looping through races
        for(int i = 0; i < nodeList.getLength(); i++){
            //set up items
            RacialStatBlock tempRace = new RacialStatBlock();
            Node raceNode = nodeList.item(i);
            NodeList raceList = raceNode.getChildNodes();

            //go through races child nodes
            for(int j = 0; j < raceList.getLength(); j++){
                Node cNode = raceList.item(j);
                //parse children
                switch(cNode.getNodeName().toLowerCase()){

                    case "name":
                            tempRace.setName(cNode.getTextContent());
                        break;
                    case "parentid":
                            tempRace.setParentId(cNode.getTextContent());
                            if(!tempRace.getParentId().equals("Not Set")){
                                tempRace.setSubrace(true);
                            }
                    case "size":
                            tempRace.setSize(cNode.getTextContent());
                        break;
                    case "speed":
                            tempRace.setSpeed(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "speedfly":
                            tempRace.setFlySpeed(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "speedswim":
                            tempRace.setSwimSpeed(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "language":
                            tempRace.addLanguage(cNode.getTextContent());
                        break;
                    case "bonusstr":
                            tempRace.setBonusStr(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "bonusdex":
                            tempRace.setBonusDex(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "bonuscon":
                            tempRace.setBonusCon(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "bonusint":
                            tempRace.setBonusInt(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "bonuswis":
                            tempRace.setBonusWis(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "bonuscha":
                            tempRace.setBonusCha(Integer.parseInt(cNode.getTextContent()));
                        break;
                    case "extra":
                            tempRace.addExtra(cNode.getTextContent());
                        break;
                    case "extrachoice":
                            tempRace.addExtraChoice(cNode.getTextContent());
                        break;
                    case "source":
                            tempRace.setSource(cNode.getTextContent());
                        break;
                }
            }
            races.add(tempRace);
        }

        return races;
    }
}
