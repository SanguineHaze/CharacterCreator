import java.util.ArrayList;

public class RacialStatBlock {
	String name, size, source, parentID;
	ArrayList<String> language, extra, extraChoice;
	int bonusStr, bonusDex, bonusCon, bonusInt, bonusWis, bonusCha, speed, flySpeed, swimSpeed;
	int  baseValue = 10;
	static RacialStatBlock builtStats = new RacialStatBlock();
	
	public RacialStatBlock(){
		name = "Not Set";
		parentID = "Not Set";
		size = "Not Set";
		speed = 0;
		flySpeed = 0;
		swimSpeed = 0;
		bonusStr = 0;
		bonusDex = 0;
		bonusCon = 0;
		bonusInt = 0;
		bonusWis = 0;
		bonusCha = 0;
		language = new ArrayList<String>();
		extra = new ArrayList<String>();
		extraChoice = new ArrayList<String>();
		source = "Not Set";
	}
	
	public RacialStatBlock(String chosenRace, String chosenSubRace){
		generateRacialStats(chosenRace, chosenSubRace);
	}
	
	///METHODS///
	public void generateRacialStats(String chosenRace, String chosenSubRace){
		//System.out.println("Chosen Race: " + chosenRace);
		RacialStatBlock tempRace = new RacialStatBlock();
		RacialStatBlock tempSubrace = new RacialStatBlock();
		
		int raceSpeed = 0, 
				subraceSpeed = 0, 
				raceFlySpeed = 0, 
				subraceFlySpeed = 0, 
				raceSwimSpeed = 0, 
				subraceSwimSpeed = 0;
		ArrayList<String> raceLanguage = new ArrayList<String>(), 
				subraceLanguage = new ArrayList<String>(), 
				raceExtra = new ArrayList<String>(), 
				subraceExtra = new ArrayList<String>(),
				builtExtra = new ArrayList<String>(),
				builtExtraChoice = new ArrayList<String>();
		
		for (RacialStatBlock entry: GenerateSourceData.raceStatBlock){
			if(entry.getName().toLowerCase().equals(chosenRace.toLowerCase())) {
				//RacialStatBlock tempRace = new RacialStatBlock();
				
				tempRace.setBonusStr(entry.bonusStr);
				tempRace.setBonusDex(entry.bonusDex);
				tempRace.setBonusCon(entry.bonusCon);
				tempRace.setBonusInt(entry.bonusInt);
				tempRace.setBonusWis(entry.bonusWis);
				tempRace.setBonusCha(entry.bonusCha);
				
				tempRace.setSpeed(entry.speed);
				tempRace.setSwimSpeed(entry.swimSpeed);
				tempRace.setFlySpeed(entry.flySpeed);
				tempRace.setLanguage(entry.language);
				tempRace.setExtra(entry.extra);
				
				raceSpeed = tempRace.speed;
				raceFlySpeed = tempRace.flySpeed;
				raceSwimSpeed = tempRace.swimSpeed;
				raceLanguage = tempRace.language;
				raceExtra = tempRace.extra;
				
				/*System.out.println("\n" + "Race: " + entry.getName());
				System.out.println("\t" + "Source: " + entry.getSource());
				System.out.println("\t" + "Size: " + entry.getSize());
				System.out.println("\t" + "Speed: " + entry.getSpeed());
				System.out.println("\t" + "Fly Speed: " + entry.getFlySpeed());
				System.out.println("\t" + "Swim Speed: " + entry.getSwimSpeed());
				System.out.println("\t" + "Languages: " + entry.getLanguage());
				System.out.println("\t" + "Bonus Strength: " + entry.getBonusStr());
				System.out.println("\t" + "Bonus Dexterity: " + entry.getBonusDex());
				System.out.println("\t" + "Bonus Constitution: " + entry.getBonusCon());
				System.out.println("\t" + "Bonus Intelligence: " + entry.getBonusInt());
				System.out.println("\t" + "Bonus Wisdom: " + entry.getBonusWis());
				System.out.println("\t" + "Bonus Charisma: " + entry.getBonusCha());
				System.out.println("\t" + "Extra: " + entry.getExtra());
				System.out.println("\t" + "Extra Choice: " + entry.getExtraChoice());*/
			}
		}
		for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
			if(entry.getName().toLowerCase().equals(chosenSubRace.toLowerCase())){
				//RacialStatBlock tempSubrace = new RacialStatBlock();
				
				tempSubrace.setBonusStr(entry.bonusStr);
				tempSubrace.setBonusDex(entry.bonusDex);
				tempSubrace.setBonusCon(entry.bonusCon);
				tempSubrace.setBonusInt(entry.bonusInt);
				tempSubrace.setBonusWis(entry.bonusWis);
				tempSubrace.setBonusCha(entry.bonusCha);

				tempSubrace.setSpeed(entry.speed);
				tempSubrace.setSwimSpeed(entry.swimSpeed);
				tempSubrace.setFlySpeed(entry.flySpeed);
				tempSubrace.setLanguage(entry.language);
				tempSubrace.setExtra(entry.extra);
				
				subraceSpeed = tempSubrace.speed;
				subraceFlySpeed = tempSubrace.flySpeed;
				subraceSwimSpeed = tempSubrace.swimSpeed;
				subraceLanguage = tempSubrace.language;
				subraceExtra = tempSubrace.extra;

				/*System.out.println("\n" + "Subrace: " + entry.getName());
				System.out.println("\t" + "Parent: " + entry.getParentID());
				System.out.println("\t" + "Source: " + entry.getSource());
				System.out.println("\t" + "Size: " + entry.getSize());
				System.out.println("\t" + "Speed: " + entry.getSpeed());
				System.out.println("\t" + "Fly Speed: " + entry.getFlySpeed());
				System.out.println("\t" + "Swim Speed: " + entry.getSwimSpeed());
				System.out.println("\t" + "Languages: " + entry.getLanguage());
				System.out.println("\t" + "Bonus Strength: " + entry.getBonusStr());
				System.out.println("\t" + "Bonus Dexterity: " + entry.getBonusDex());
				System.out.println("\t" + "Bonus Constitution: " + entry.getBonusCon());
				System.out.println("\t" + "Bonus Intelligence: " + entry.getBonusInt());
				System.out.println("\t" + "Bonus Wisdom: " + entry.getBonusWis());
				System.out.println("\t" + "Bonus Charisma: " + entry.getBonusCha());
				System.out.println("\t" + "Extra: " + entry.getExtra());
				System.out.println("\t" + "Extra Choice: " + entry.getExtraChoice());*/
			}
		}
		
		//SPEED
		int totalSpeed = raceSpeed + subraceSpeed;
		builtStats.setSpeed(totalSpeed);
		
		//FLY SPEED
		int totalFlySpeed = raceFlySpeed + subraceFlySpeed;
		builtStats.setFlySpeed(totalFlySpeed);
		
		//SWIM SPEED
		int totalSwimSpeed = raceSwimSpeed + subraceSwimSpeed;
		builtStats.setSwimSpeed(totalSwimSpeed);
		
		//STR
		int totalStr = baseValue + tempRace.bonusStr + tempSubrace.bonusStr;
		builtStats.setBonusStr(totalStr);
		
		//DEX
		int totalDex = baseValue + tempRace.bonusDex + tempSubrace.bonusDex;
		builtStats.setBonusDex(totalDex);
		
		//CON
		int totalCon = baseValue + tempRace.bonusCon + tempSubrace.bonusCon;
		builtStats.setBonusCon(totalCon);
		
		//INT
		int totalInt = baseValue + tempRace.bonusInt + tempSubrace.bonusInt;
		builtStats.setBonusInt(totalInt);
		
		//WIS
		int totalWis = baseValue + tempRace.bonusWis + tempSubrace.bonusWis;
		builtStats.setBonusWis(totalWis);
		
		//CHA
		int totalCha = baseValue + tempRace.bonusCha + tempSubrace.bonusCha;
		builtStats.setBonusCha(totalCha);
		
		//LANGUAGE
		if(subraceLanguage.size() >= 1){
			for(String entry: subraceLanguage){
				if(!raceLanguage.contains(entry)){
					//System.out.println("Current Built Lang: " + raceLanguage);
					//System.out.println("Adding: " + entry);
					raceLanguage.add(entry);
				}
			}
		}		
		builtStats.setLanguage(raceLanguage);
		//System.out.println("Test Languages: " + builtStats.language);
		
		//EXTRA
		for(String entry: raceExtra){
			if(!builtExtra.contains(entry)){
				builtExtra.add(entry);
			}
			for(String entry2: subraceExtra){
				if(!builtExtra.contains(entry2)){
					builtExtra.add(entry2);
				}
			}
		}
		
		builtStats.setExtra(builtExtra);
		//System.out.println("BuiltStats - Extras: " + builtStats.extra);
		
		//EXTRACHOICE
		for(String entry: tempRace.extraChoice){
			if(!builtExtraChoice.contains(entry)){
				builtExtraChoice.add(entry);
			}
			for(String entry2: tempSubrace.extraChoice){
				if(!builtExtraChoice.contains(entry2)){
					builtExtraChoice.add(entry2);
				}
			}
		}
	}
	
	///GETTERS & SETTERS///
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed2) {
		this.speed = speed2;
	}
	public int getFlySpeed() {
		return flySpeed;
	}
	public void setFlySpeed(int flyspeed) {
		this.flySpeed = flyspeed;
	}
	public int getSwimSpeed() {
		return swimSpeed;
	}
	public void setSwimSpeed(int swimspeed) {
		this.swimSpeed = swimspeed;
	}
	public ArrayList<String> getLanguage() {
		return language;
	}
	public void setLanguage(ArrayList<String> language) {
		this.language = language;
	}
	public void addLanguage(String lang){
		language.add(lang);
	}
	public ArrayList<String> getExtra() {
		return extra;
	}
	public void setExtra(ArrayList<String> extra) {
		this.extra = extra;
	}
	public void addExtra(String ext){
		extra.add(ext);
	}
	public int getBonusStr() {
		return bonusStr;
	}
	public void setBonusStr(int bonusStr) {
		this.bonusStr = bonusStr;
	}
	public int getBonusDex() {
		return bonusDex;
	}
	public void setBonusDex(int bonusDex) {
		this.bonusDex = bonusDex;
	}
	public int getBonusCon() {
		return bonusCon;
	}
	public void setBonusCon(int bonusCon) {
		this.bonusCon = bonusCon;
	}
	public int getBonusInt() {
		return bonusInt;
	}
	public void setBonusInt(int bonusInt) {
		this.bonusInt = bonusInt;
	}
	public int getBonusWis() {
		return bonusWis;
	}
	public void setBonusWis(int bonusWis) {
		this.bonusWis = bonusWis;
	}
	public int getBonusCha() {
		return bonusCha;
	}
	public void setBonusCha(int bonusCha) {
		this.bonusCha = bonusCha;
	}
	public ArrayList<String> getExtraChoice() {
		return extraChoice;
	}
	public void setExtraChoice(ArrayList<String> extraChoice) {
		this.extraChoice = extraChoice;
	}	
	public void addExtraChoice(String extChoice) {
		extraChoice.add(extChoice);
	}
}
