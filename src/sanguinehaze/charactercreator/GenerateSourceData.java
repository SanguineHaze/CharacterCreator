package sanguinehaze.charactercreator;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import sanguinehaze.charactercreator.adapter.persistence.RaceInfoXmlParser;
import sanguinehaze.charactercreator.domain.dtos.AdultProfessionList;
import sanguinehaze.charactercreator.domain.dtos.ChildProfessionList;
import sanguinehaze.charactercreator.domain.dtos.DetailsList;
import sanguinehaze.charactercreator.domain.dtos.MotivationList;
import sanguinehaze.charactercreator.domain.dtos.ChildMotivationList;
import sanguinehaze.charactercreator.domain.dtos.NicknameList;
import sanguinehaze.charactercreator.domain.dtos.PersonalityList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import sanguinehaze.charactercreator.domain.dtos.RacialStatBlock;
import sanguinehaze.charactercreator.adapter.persistence.SourcePersistence;

public class GenerateSourceData {

    private static ArrayList<String> raceSourceStatic = new ArrayList<>();
    //TODO: Look into why this is here, it is being filled but never used.
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static ArrayList<String> subRaceSourceStatic = new ArrayList<>();
    // "Name" Section //
    private static ArrayList<String> nameData = new ArrayList<>();
    private static ArrayList<String> nameDataDwarfM = new ArrayList<>();

    private static ArrayList<String> beginningName = new ArrayList<>();
    private static ArrayList<String> beginningNameDwarfM = new ArrayList<>();

    private static ArrayList<String> middleName = new ArrayList<>();
    private static ArrayList<String> middleNameDwarfM = new ArrayList<>();

    private static ArrayList<String> endName = new ArrayList<>();
    private static ArrayList<String> endNameDwarfM = new ArrayList<>();

    private AdultProfessionList _adultProfessionList;
    private ChildProfessionList _childProfessionList;
    private MotivationList _motivationList;
    private ChildMotivationList _childMotivationList;
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
    public static ArrayList<RacialStatBlock> raceStatBlock = new ArrayList<>();
    private ArrayList<String> itemSourceData = new ArrayList<>();


    ////////GETTERS & SETTERS////////

    public static ArrayList<String> getRaceSourceStatic() {
        return raceSourceStatic;
    }

    private SourcePersistence sourcePersistence;
    private RaceInfoXmlParser raceInfoXmlParser;

    public AdultProfessionList getAdultProfessionList() {
        return this._adultProfessionList;
    }

    public ChildProfessionList getChildProfessionList() {
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

    public ArrayList<String> getBeginningNameDwarfM() {
        return beginningNameDwarfM;
    }

    public ArrayList<String> getMiddleNameDwarfM() {
        return middleNameDwarfM;
    }

    public ArrayList<String> getEndNameDwarfM() {
        return endNameDwarfM;
    }

    MotivationList getMotivationList() {
        return this._motivationList;
    }

    ChildMotivationList getChildMotivationList() {
        return this._childMotivationList;
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

    ArrayList<String> getItemSourceData() {
        return itemSourceData;
    }


    ////////CONSTRUCTOR////////
    public GenerateSourceData(
            SourcePersistence sourcePersistence,
            RaceInfoXmlParser raceInfoXmlParser){
        this.sourcePersistence = sourcePersistence;
        this.raceInfoXmlParser = raceInfoXmlParser;
        generateRacialStatsSourceData();
        raceSourceStatic.add(0, "Any Race");
        generateRaceData();
        generateSubraceNames();
        generateNameSourceData();
        generateNameSectionData();
        generateSourceData();
    }

    ////////METHODS////////
    private void generateRaceData() {
        for(RacialStatBlock raceEntry: GenerateSourceData.raceStatBlock){
            if(!raceEntry.isSubrace()){
                raceSourceStatic.add(raceEntry.getName());
            }
        }
    }

    private void generateSubraceNames(){
        subRaceSourceStatic.clear();
        for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
            if(entry.isSubrace()){
                subRaceSourceStatic.add(entry.getName());
            }
        }
    }

    private void generateNameSourceData(){
        //TODO: Expand this section to have different files for the different races.
        nameData = sourcePersistence.GetData("NameDefault");
        nameDataDwarfM = sourcePersistence.GetData("DwarfMaleName");
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

        for(String entry: nameDataDwarfM){
            if(entry.contains("BEGINNING:")){
                beginningNameDwarfM.add(entry);
            } else if(entry.contains("MIDDLE:")) {
                middleNameDwarfM.add(entry);
            } else if(entry.contains("END:")) {
                endNameDwarfM.add(entry);
            }
        }
    }

    private void generateSourceData(){

        _adultProfessionList = AdultProfessionList.of(DeserializeData(sourcePersistence.GetData("Professions")));
        _childProfessionList = ChildProfessionList.of(DeserializeData(sourcePersistence.GetData("ProfessionsChild")));

        _motivationList = MotivationList.of(sourcePersistence.GetData("Motivations"));
        _childMotivationList = ChildMotivationList.of(sourcePersistence.GetData("ChildMotivations"));
        _personalityList = PersonalityList.of(sourcePersistence.GetData("Personalities"));
        _nicknameList = NicknameList.of(sourcePersistence.GetData("Nicknames"));
        _detailsList = DetailsList.of(sourcePersistence.GetData("Details"));
        theLocalSourceData = sourcePersistence.GetData("TheLocalReplacement");
        favorToSourceData = sourcePersistence.GetData("OwesFavorTo");
        protectedBySourceData = sourcePersistence.GetData("ProtectedBy");
        mapToSourceData = sourcePersistence.GetData("MapTo");
        possessesASourceData = sourcePersistence.GetData("PossessesA");
        obsessedBySourceData = sourcePersistence.GetData("ObsessedBy");
        cursedBySourceData = sourcePersistence.GetData("Cursed");
        itemSourceData = sourcePersistence.GetData("Items");
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
            raceStatBlock.addAll(raceInfoXmlParser.parseRacialStatSourceData(raceTag));

            NodeList subraceTag = doc.getElementsByTagName("subRace");
            raceStatBlock.addAll(raceInfoXmlParser.parseRacialStatSourceData(subraceTag));

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}

