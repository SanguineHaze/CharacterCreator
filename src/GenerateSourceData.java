import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import persistence.SourcePersistence;

public class GenerateSourceData {
	
	protected ArrayList<String> raceSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> raceSourceStatic = new ArrayList<>();
	protected ArrayList<String> subraceSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> subraceSourceStatic = new ArrayList<>();
	protected ArrayList<String> adultProfessionSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> adultProfessionSourceStatic = new ArrayList<>();
	protected ArrayList<String> childProfessionSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> childProfessionSourceStatic = new ArrayList<>();
	protected static ArrayList<String> nameData = new ArrayList<>();
	protected static ArrayList<String> beginningName = new ArrayList<>();
	protected static ArrayList<String> middleName = new ArrayList<>();
	protected static ArrayList<String> endName = new ArrayList<>();
	protected static ArrayList<String> ageRange = new ArrayList<>();
	protected static ArrayList<String> ageRangeStatic = new ArrayList<>();
	protected static ArrayList<String> motivationSourceData = new ArrayList<>();
	protected static ArrayList<String> personalitySourceData = new ArrayList<>();
	protected static ArrayList<String> nicknameSourceData = new ArrayList<>();
	protected static ArrayList<String> detailsSourceData = new ArrayList<>();
	protected static ArrayList<String> itemsSourceData = new ArrayList<>();
	//Data for "Details" Subsection //
	protected static ArrayList<String> theLocalSourceData = new ArrayList<>();
	protected static ArrayList<String> favorToSourceData = new ArrayList<>();
	protected static ArrayList<String> protectedBySourceData = new ArrayList<>();
	protected static ArrayList<String> mapToSourceData = new ArrayList<>();
	protected static ArrayList<String> possessesASourceData = new ArrayList<>();
	protected static ArrayList<String> obsessedBySourceData = new ArrayList<>();
	protected static ArrayList<String> cursedBySourceData = new ArrayList<>();
	protected static ArrayList<RacialStatBlock> raceStatBlock = new ArrayList<>();
	private NodeList raceTag;
	private NodeList subraceTag;
	
    ////////GETTERS & SETTERS////////

    public static ArrayList<String> getRaceSourceStatic() {
        return raceSourceStatic;
    }

    private SourcePersistence _sourcePersistance;

    ////////CONSTRUCTOR////////
    GenerateSourceData(SourcePersistence sourcePersistance){
        _sourcePersistance = sourcePersistance;
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

    public void generateSubraceNames(){
        subraceSourceStatic.clear();
        for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
            if(entry.isSubrace){
                subraceSourceStatic.add(entry.name);
            }
        }
    }

    private void generateNameSourceData(){
        //TODO: Expand this section to have different files for the different races.
        nameData = _sourcePersistance.GetData("NameDefault");
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

        adultProfessionSourceStatic = DeserializeData(_sourcePersistance.GetData("Professions"));
        childProfessionSourceStatic = DeserializeData(_sourcePersistance.GetData("ProfessionsChild"));

        motivationSourceData = _sourcePersistance.GetData("Motivations");
        personalitySourceData = _sourcePersistance.GetData("Personalities");
        nicknameSourceData = _sourcePersistance.GetData("Nicknames");
        detailsSourceData = _sourcePersistance.GetData("Details");
        theLocalSourceData =_sourcePersistance.GetData("TheLocalReplacement");
        favorToSourceData = _sourcePersistance.GetData("OwesFavorTo");
        protectedBySourceData = _sourcePersistance.GetData("ProtectedBy");
        mapToSourceData = _sourcePersistance.GetData("MapTo");
        possessesASourceData = _sourcePersistance.GetData("PossessesA");
        obsessedBySourceData = _sourcePersistance.GetData("ObsessedBy");
        cursedBySourceData = _sourcePersistance.GetData("Cursed");
        itemsSourceData = _sourcePersistance.GetData("Items");
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

            raceTag = doc.getElementsByTagName("race");
            parseRacialStatSourceData(raceTag);

            subraceTag = doc.getElementsByTagName("subRace");
            parseRacialStatSourceData(subraceTag);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void parseRacialStatSourceData(NodeList nodeList){

        NodeList nList = nodeList;

        //start looping through races
        for(int i = 0; i < nList.getLength(); i++){
            //set up items
            RacialStatBlock tempRace = new RacialStatBlock();
            Node raceNode = nList.item(i);
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

