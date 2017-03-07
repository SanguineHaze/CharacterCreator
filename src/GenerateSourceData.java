import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenerateSourceData {
	
	protected ArrayList<String> raceSourcePreStatic = new ArrayList<String>();
	protected static ArrayList<String> raceSourceStatic = new ArrayList<String>();
	protected ArrayList<String> subraceSourcePreStatic = new ArrayList<String>();
	protected static ArrayList<String> subraceSourceStatic = new ArrayList<String>();
	protected ArrayList<String> adultProfessionSourcePreStatic = new ArrayList<String>();
	protected static ArrayList<String> adultProfessionSourceStatic = new ArrayList<String>();
	protected ArrayList<String> childProfessionSourcePreStatic = new ArrayList<String>();
	protected static ArrayList<String> childProfessionSourceStatic = new ArrayList<String>();
	protected static ArrayList<String> nameData = new ArrayList<String>();
	protected static ArrayList<String> beginningName = new ArrayList<String>();
	protected static ArrayList<String> middleName = new ArrayList<String>();
	protected static ArrayList<String> endName = new ArrayList<String>();
	protected static ArrayList<String> ageRange = new ArrayList<String>();
	protected static ArrayList<String> ageRangeStatic = new ArrayList<String>();
	protected static ArrayList<String> motivationSourceData = new ArrayList<String>();
	protected static ArrayList<String> personalitySourceData = new ArrayList<String>();
	protected static ArrayList<String> nicknameSourceData = new ArrayList<String>();
	protected static ArrayList<String> detailsSourceData = new ArrayList<String>();
	//Data for "Details" Subsection //
	protected static ArrayList<String> theLocalSourceData = new ArrayList<String>();
	protected static ArrayList<String> favorToSourceData = new ArrayList<String>();
	protected static ArrayList<String> protectedBySourceData = new ArrayList<String>();
	protected static ArrayList<String> mapToSourceData = new ArrayList<String>();
	protected static ArrayList<String> possessesASourceData = new ArrayList<String>();
	protected static ArrayList<String> obsessedBySourceData = new ArrayList<String>();
	protected static ArrayList<String> cursedBySourceData = new ArrayList<String>();
	protected static ArrayList<RacialStatBlock> raceStatBlock = new ArrayList<RacialStatBlock>();
	private NodeList raceTag;
	private NodeList subraceTag;
	
	
	////////GETTERS & SETTERS////////

	public static ArrayList<String> getRaceSourceStatic() {
		return raceSourceStatic;
	}

	////////CONSTRUCTOR////////
	GenerateSourceData(){
		raceSourceStatic.add(0, "Any Race");
		generateRaceData();
		generateSubraceData();
		generateProfessionData();
		generateNameSourceData();
		generateNameSectionData();
		generateAgeData();
		generateMotivationSourceData();
		generatePersonalitySourceData();
		generateNicknameSourceData();
		generateDetailsSourceData();
		generateRacialStatsSourceData();
	}
	
	////////METHODS////////
	private void generateRaceData() {
		File targetFile = new File("Race.txt");
   	 
   	 try {
   		    ReadFromFile file = new ReadFromFile(targetFile);
   		    
   		    raceSourcePreStatic = file.OpenFile();
   		    //System.out.println(raceStaticPreList);
   		    for(String s: raceSourcePreStatic){
   		    	raceSourceStatic.add(s.replace("RACE:", ""));
   		    }
   		    //DEBUG TOOL: Check to see that the list is being created
   		    /*
   		    System.out.println("Race List:");
   		    for(String out: raceList){
   		        System.out.println(out);
   		    }
   		    */
   		   
   		} catch (Exception e) {
   		    //Default error message
   		    System.out.println(e.getMessage());
   		}
	}
	
	private void generateSubraceData() {
		File subRaceTargetFile = new File("Subrace.txt"); 	
		
		try {
			ReadFromFile file = new ReadFromFile(subRaceTargetFile);
			
			subraceSourcePreStatic.clear(); //Ensure subrace list in UI is never duplicated.
			subraceSourceStatic.clear(); //2nd step to ensure no duplicates.
			
			subraceSourcePreStatic = file.OpenFile();
			for(String lineEntry: subraceSourcePreStatic){
				String[] line = lineEntry.split(":");
				subraceSourceStatic.add(line[1]);
			}
			//DEBUG TOOL: Check to see that the list is being created
            /*System.out.println("Sub-Race List:");
			for(String out: subraceSourcePreStatic){
				System.out.println(out);
			}*/

		} catch (Exception e) {
            //Default error message
            System.out.println(e.getMessage());
        }
	}
	
	private void generateProfessionData(){
		
		File childProfessionSourceFile = new File("ProfessionsChild.txt");
		File adultProfessionSourceFile = new File("Professions.txt");
		
		try {
			
			ReadFromFile file = new ReadFromFile(adultProfessionSourceFile);
			
			adultProfessionSourcePreStatic = file.OpenFile();
			for(String s: adultProfessionSourcePreStatic){
				String[] line = s.split(":");
				adultProfessionSourceStatic.add(line[1]);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			ReadFromFile file = new ReadFromFile(childProfessionSourceFile);
			
			childProfessionSourcePreStatic = file.OpenFile();
			for(String s: childProfessionSourcePreStatic){
				String[] line = s.split(":");
				childProfessionSourceStatic.add(line[1]);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void generateNameSourceData(){
		//The master list containing all Name options
		File nameListTargetFile = new File ("NameDefault.txt");
		//TODO: Expand this section to have different files for the different races. Should be able to if/else if this, the same as subrace.
		try {
			ReadFromFile file = new ReadFromFile(nameListTargetFile);
			nameData = file.OpenFile();
			
			//DEBUG TOOL: Check to see that the list is being created
		    /*
		    System.out.println("Name List:");
		    for(String out: nameList){
		        System.out.println(out);
		    }
		    */
			
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}
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
		
		//DEBUG TOOL: Check to see what's made it to the lists
		/*
		System.out.println("Sub-Race List:");
		for(String out: beginningName){
			System.out.println(out);
		}
		for(String out: middleName){
			System.out.println(out);
		}
		for(String out: endName){
			System.out.println(out);
		}		
		*/
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
	
	private void generateMotivationSourceData(){
		File motivationListTargetFile = new File("Motivations.txt");
		
		try {
			ReadFromFile file = new ReadFromFile(motivationListTargetFile);
			motivationSourceData = file.OpenFile();
			
			//DEBUG TOOL: Check to see that the list is being created
		    /*
		    System.out.println("Motivation List:");
		    for(String out: motivationList){
		        System.out.println(out);
		    }
		    */
			
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}
	}
	
	private void generatePersonalitySourceData() {
		File personalityListTargetFile = new File("Personalities.txt");
		
		try {
			ReadFromFile file = new ReadFromFile(personalityListTargetFile);
			personalitySourceData = file.OpenFile();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void generateNicknameSourceData(){
		File nicknameListTargetFile = new File("Nicknames.txt");
		
		try{
			ReadFromFile file = new ReadFromFile(nicknameListTargetFile);
			nicknameSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}//End Try/Catch
		
		//DEBUG TOOL: Check to see that the list is being created
	    /*
	    System.out.println("Nickname List:");
	    for(String out: nicknameList){
	        System.out.println(out);
	    }
	    */
	}
	
	private void generateDetailsSourceData(){
		File detailsListTargetFile = new File ("Details.txt");
		try {
			ReadFromFile file = new ReadFromFile(detailsListTargetFile);
			detailsSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File theLocalReplacementTargetFile = new File("TheLocalReplacement.txt");
		try{
			ReadFromFile file = new ReadFromFile(theLocalReplacementTargetFile);
			theLocalSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File favorToReplacementTargetFile = new File("OwesFavorTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(favorToReplacementTargetFile);
			favorToSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File protectedByReplacementFile = new File("ProtectedBy.txt");
		try{
			ReadFromFile file = new ReadFromFile(protectedByReplacementFile);
			protectedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File mapToReplacementFile = new File("MapTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(mapToReplacementFile);
			mapToSourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File possessesAReplacementFile = new File("PossessesA.txt");
		try{
			ReadFromFile file = new ReadFromFile(possessesAReplacementFile);
			possessesASourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File obsessedByReplacementFile = new File("ObsessedBy.txt");
		try {
			ReadFromFile file = new ReadFromFile(obsessedByReplacementFile);
			obsessedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File cursedReplacementFile = new File("Cursed.txt");
		try{
			ReadFromFile file = new ReadFromFile(cursedReplacementFile);
			cursedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
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
		//System.out.println("-----------------------------------------------------");
		
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
					if(!"parentID".isEmpty()){
						tempRace.setParentID(cNode.getTextContent());
					}
				case "size": 
					if(!"size".isEmpty()){
						tempRace.setSize(cNode.getTextContent());
					}
					break;
				case "speed": 
					if(!"speed".isEmpty()){
						tempRace.addSpeed(cNode.getTextContent());
					}
					break;
				case "language": 
					if("language".isEmpty()){
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
		///DEBUG TOOL - STATBLOCKS///
		/*for(RacialStatBlock thisRace: raceStatBlock){
			System.out.println("\n" + "Race: " + thisRace.getName());
			System.out.println("\t" + "Parent: " + thisRace.getParentID());
			System.out.println("\t" + "Source: " + thisRace.getSource());
			System.out.println("\t" + "Size: " + thisRace.getSize());
			System.out.println("\t" + "Speed: " + thisRace.getSpeed());
			System.out.println("\t" + "Languages: " + thisRace.getLanguage());
			System.out.println("\t" + "Bonus Strength: " + thisRace.getBonusStr());
			System.out.println("\t" + "Bonus Dexterity: " + thisRace.getBonusDex());
			System.out.println("\t" + "Bonus Constitution: " + thisRace.getBonusCon());
			System.out.println("\t" + "Bonus Intelligence: " + thisRace.getBonusInt());
			System.out.println("\t" + "Bonus Wisdom: " + thisRace.getBonusWis());
			System.out.println("\t" + "Bonus Charisma: " + thisRace.getBonusCha());
			System.out.println("\t" + "Extra: " + thisRace.getExtra());
			System.out.println("\t" + "Extra Choice: " + thisRace.getExtraChoice());
		}*/
		
	}
}

