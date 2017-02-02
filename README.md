#CharacterCreator
##DnD 5e Character Creator by SanguineHaze

#Required items:
- Java must be installed (www.java.com)

#Planned Updates (As of Dec 9th, 2016):
[Note: These are unordered. They will be completed in order of whatever I wish to tackle first]

- Add Stat generation
	- Str, dex, con, int, wis, cha
	- Speed
	- Size (S M L)
	- Racial traits & bonuses
	- Class traits & bonuses (where applicable)
- Change Name generation so it is related to the chosen race
	- Include name length changes to match PHB/DMG racial name average lengths
	- Include clan names
		-Clan names (BLOCKED - Required: Race related naming)
- Change Nickname generation so it is based off of chosen race and chosen profession (where applicable)
	- Change "Child" nicknames to be 100% child oriented
- Change Motivations so that Child has a special set of motivations (Current motivations don't make much sense for children)

#Recent Updates
###Feb 2nd, 2017
- Fixed Bug: Bug noticed (2/2/2017): After successful generate, subrace list does not clear, and continues to duplicate entries.

###Dec 19th, 2016
- Refactor: Removed duplicate code which was being used in every class (sometimes multiple times) to build up the path to the sourceData folder & appropriate text file. This is now done in the ReadFromFile class. [25 chars now build in ~0.1 second]

###Dec 14, 2016
- Added Surnames
- Changed how nicknames are displayed (if it contains "the [Something]" it displays First Name / Last Name / Nickname. If not, it's First Name / Nickname / Last Name)
- Found & Corrected "save" bug where all results were being fed to the save Array even before it had been selected (Causing a save of every output since the program opened).
	- Found & corrected a subsequent bug where the Save list was not clearing.

###Dec 9, 2016
- Add user choice for Nickname chance (completed Dec 9/16)
- Add user choice for Additional Details chance (completed Dec 9/16)

#The initial plan (Begun Aug 24th, 2016 - Completed Nov 2, 2016):
Race > Subrace > M/F toggle > Name > Class || Job > defining features, personality, motivations
	-Features added beyond original idea: Swing GUI & a WriteToFile method (necessary for users & breaking out of IDE)
	-Features dropped: alignment (fuck alignment & the headaches it causes. My friends, be not distracted by such arbitrary definitions of good and evil!)