package sanguinehaze.charactercreator;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import sanguinehaze.charactercreator.data.dtos.AdultProfessionList;
import sanguinehaze.charactercreator.data.dtos.ChildProfessionList;
import sanguinehaze.charactercreator.data.dtos.DetailsList;
import sanguinehaze.charactercreator.data.dtos.MotivationList;
import sanguinehaze.charactercreator.data.dtos.NicknameList;
import sanguinehaze.charactercreator.data.dtos.PersonalityList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sanguinehaze.charactercreator.persistence.SourcePersistence;

public class GenerateSourceData {

    private static ArrayList<String> raceSourceStatic = new ArrayList<>();
    //TODO: Look into why this is here, it is being filled but never used.
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static ArrayList<String> subRaceSourceStatic = new ArrayList<>();
    private AdultProfessionList _adultProfessionList;
    private ChildProfessionList _childProfessionList;
    private static ArrayList<String> nameData = new ArrayList<>();
    private static ArrayList<String> beginningName = new ArrayList<>();
    private static ArrayList<String> middleName = new ArrayList<>();
    private static ArrayList<String> endName = new ArrayList<>();
    private static ArrayList<String> ageRange = new ArrayList<>();
    private static ArrayList<String> ageRangeStatic = new ArrayList<>();
    private MotivationList _motivationList;
    private PersonalityList _personalityList;
    private NicknameList _nicknameList;
    private DetailsList _detailsList;
    //Data for "Details" Subsection //
    private static ArrayList<String> theLocalSourceData = new ArrayList<>();
    private static ArrayList<String> favorToSourceData = new ArrayList<>();
    private static ArrayList<String> protectedBySourceData = new ArrayList<>();
    private static ArrayList<String> mapToSourceData = new ArrayList<>();
    private static ArrayList<String> possessesASourceData = new ArrayList<>();
    private static ArrayList<String> obsessedBySourceData = new ArrayList<>();
    private static ArrayList<String> cursedBySourceData = new ArrayList<>();
    static ArrayList<RacialStatBlock> raceStatBlock = new ArrayList<>();
    private ArrayList<String> itemSourceData = new ArrayList<>();


    ////////GETTERS & SETTERS////////

    static ArrayList<String> getRaceSourceStatic() {
        return raceSourceStatic;
    }

    private SourcePersistence _sourcePersistence;

    AdultProfessionList getAdultProfessionList() {
        return this._adultProfessionList;
    }

    ChildProfessionList getChildProfessionList() {
        return this._childProfessionList;
    }

    public ArrayList<String> getBeginningName() {
        return beginningName;
    }

    public ArrayList<String> getMiddleName() {
        return middleName;
    }

    public ArrayList<String> getEndName() {
        return endName;
    }

    ArrayList<String> getAgeRange() {
        return ageRange;
    }

    ArrayList<String> getAgeRangeStatic() {
        return ageRangeStatic;
    }

    MotivationList getMotivationList() {
        return this._motivationList;
    }

    PersonalityList getPersonalityList() {
        return this._personalityList;
    }

    NicknameList getNicknameList() {
        return this._nicknameList;
    }

    DetailsList getDetailsList() {
        return this._detailsList;
    }

    ArrayList<String> getTheLocalSourceData() {
        return theLocalSourceData;
    }

    ArrayList<String> getFavorToSourceData() {
        return favorToSourceData;
    }

    ArrayList<String> getProtectedBySourceData() {
        return protectedBySourceData;
    }

    ArrayList<String> getMapToSourceData() {
        return mapToSourceData;
    }

    ArrayList<String> getPossessesASourceData() {
        return possessesASourceData;
    }

    ArrayList<String> getObsessedBySourceData() {
        return obsessedBySourceData;
    }

    ArrayList<String> getCursedBySourceData() {
        return cursedBySourceData;
    }

    public ArrayList<String> getItemSourceData() {
        return itemSourceData;
    }


    ////////CONSTRUCTOR////////
    public GenerateSourceData(SourcePersistence sourcePersistence){
        _sourcePersistence = sourcePersistence;
        generateRacialStatsSourceData();
        raceSourceStatic.add(0, "Any Race");
        generateRaceData();
        generateSubraceNames();
        generateNameSourceData();
        generateNameSectionData();
        generateAgeData();
        generateSourceData();
    }

    ////////METHODS////////
    private void generateRaceData() {
        for(RacialStatBlock raceEntry: GenerateSourceData.raceStatBlock){
            if(!raceEntry.isSubrace){
                raceSourceStatic.add(raceEntry.name);
            }
        }
    }

    private void generateSubraceNames(){
        subRaceSourceStatic.clear();
        for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
            if(entry.isSubrace){
                subRaceSourceStatic.add(entry.name);
            }
        }
    }

    private void generateNameSourceData(){
        //TODO: Expand this section to have different files for the different races.
        nameData = _sourcePersistence.GetData("NameDefault");
    }

    private void generateNameSectionData(){
        for(String entry: nameData){
            if(entry.contains("BEGINNING:")){
                beginningName.add(entry);
            } else if(entry.contains("MIDDLE:")) {
                middleName.add(entry);
            } else if(entry.contains("END:")) {
                endName.add(entry);
            }
        }
    }

    private void generateAgeData(){
        ageRangeStatic.add("Child");
        ageRangeStatic.add("Young Adult");
        ageRangeStatic.add("Adult");
        ageRangeStatic.add("Old");
        ageRangeStatic.add("Very Old");

        ageRange.add("Child");
        ageRange.add("Young Adult");
        ageRange.add("Adult");
        ageRange.add("Old");
        ageRange.add("Very Old");
    }

    private void generateSourceData(){

        _adultProfessionList = AdultProfessionList.of(DeserializeData(_sourcePersistence.GetData("Professions")));
        _childProfessionList = ChildProfessionList.of(DeserializeData(_sourcePersistence.GetData("ProfessionsChild")));

        _motivationList = MotivationList.of(_sourcePersistence.GetData("Motivations"));
        _personalityList = PersonalityList.of(_sourcePersistence.GetData("Personalities"));
        _nicknameList = NicknameList.of(_sourcePersistence.GetData("Nicknames"));
        _detailsList = DetailsList.of(_sourcePersistence.GetData("Details"));
        theLocalSourceData = _sourcePersistence.GetData("TheLocalReplacement");
        favorToSourceData = _sourcePersistence.GetData("OwesFavorTo");
        protectedBySourceData = _sourcePersistence.GetData("ProtectedBy");
        mapToSourceData = _sourcePersistence.GetData("MapTo");
        possessesASourceData = _sourcePersistence.GetData("PossessesA");
        obsessedBySourceData = _sourcePersistence.GetData("ObsessedBy");
        cursedBySourceData = _sourcePersistence.GetData("Cursed");
        itemSourceData = _sourcePersistence.GetData("Items");
    }

    private ArrayList<String> DeserializeData(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for(String s: list){
            String[] line = s.split(":");
            newList.add(line[1]);
        }
        return newList;
    }

    private void generateRacialStatsSourceData(){
        String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator).replace("%20", " ");

        try{
            File xmlSourceFile = new File(filesDirectory + "sourceData" + File.separator + "RaceOptions.xml");
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(xmlSourceFile);
            doc.getDocumentElement().normalize();

            NodeList raceTag = doc.getElementsByTagName("race");
            parseRacialStatSourceData(raceTag);

            NodeList subraceTag = doc.getElementsByTagName("subRace");
            parseRacialStatSourceData(subraceTag);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void parseRacialStatSourceData(NodeList nodeList){

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
                        if(!"name".isEmpty()){
                            tempRace.setName(cNode.getTextContent());
                        }
                        break;
                    case "parentid":
                        if(!"parentid".isEmpty()){
                            tempRace.setParentID(cNode.getTextContent());
                            if(!tempRace.parentID.equals("Not Set")){
                                tempRace.setIsSubrace(true);
                            }
                        }
                    case "size":
                        if(!"size".isEmpty()){
                            tempRace.setSize(cNode.getTextContent());
                        }
                        break;
                    case "speed":
                        if(!"speed".isEmpty()){
                            tempRace.setSpeed(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "speedfly":
                        if(!"speedfly".isEmpty()){
                            tempRace.setFlySpeed(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "speedswim":
                        if(!"speedswim".isEmpty()){
                            tempRace.setSwimSpeed(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "language":
                        if(!"language".isEmpty()){
                            tempRace.addLanguage(cNode.getTextContent());
                        }
                        break;
                    case "bonusstr":
                        if(!"bonusstr".isEmpty()){
                            tempRace.setBonusStr(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "bonusdex":
                        if(!"bonusdex".isEmpty()){
                            tempRace.setBonusDex(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "bonuscon":
                        if(!"bonuscon".isEmpty()){
                            tempRace.setBonusCon(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "bonusint":
                        if(!"bonusint".isEmpty()){
                            tempRace.setBonusInt(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "bonuswis":
                        if(!"bonuswis".isEmpty()){
                            tempRace.setBonusWis(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "bonuscha":
                        if(!"bonuscha".isEmpty()){
                            tempRace.setBonusCha(Integer.parseInt(cNode.getTextContent()));
                        }
                        break;
                    case "extra":
                        if(!"extra".isEmpty()){
                            tempRace.addExtra(cNode.getTextContent());
                        }
                        break;
                    case "extrachoice":
                        if(!"extrachoice".isEmpty()){
                            tempRace.addExtraChoice(cNode.getTextContent());
                        }
                        break;
                    case "source":
                        if(!"source".isEmpty()){
                            tempRace.setSource(cNode.getTextContent());
                        }
                        break;
                }
            }
            raceStatBlock.add(tempRace);
        }
    }


}

