package sanguinehaze.charactercreator.adapter.views.viewmodels;

public class CharacterGenerationRequestViewModel {

    private int itemChance;
    private boolean includeStats;
    private int detailsChance;
    private int nicknameChance;
    private boolean isSaveNext;
    private String selectedProfession;
    private String selectedAge;
    private String selectedSex;
    private String selectedSubrace;
    private int numGenInt;
    private String selectedRace;

    public int getItemChance() {
        if (this.itemChance > 100 || this.itemChance < 0) {
            return 25;
        }
        return itemChance;
    }

    public void setItemChance(int itemChance) {
        this.itemChance = itemChance;
    }

    public boolean isIncludeStats() {
        return includeStats;
    }

    public void setIncludeStats(boolean includeStats) {
        this.includeStats = includeStats;
    }

    public int getDetailsChance() {
        if (this.detailsChance > 100 || this.detailsChance < 0) {
            return 25;
        }
        return detailsChance;
    }

    public void setDetailsChance(int detailsChance) {
        this.detailsChance = detailsChance;
    }

    public int getNicknameChance() {
        if (this.nicknameChance > 100 || this.nicknameChance < 100) {
            return 25;
        }
        return nicknameChance;
    }

    public void setNicknameChance(int nicknameChance) {
        this.nicknameChance = nicknameChance;
    }

    public boolean isSaveNext() {
        return isSaveNext;
    }

    public void setSaveNext(boolean saveNext) {
        isSaveNext = saveNext;
    }

    public String getSelectedProfession() {
        return selectedProfession;
    }

    public void setSelectedProfession(String selectedProfession) {
        this.selectedProfession = selectedProfession;
    }

    public String getSelectedAge() {
        return selectedAge;
    }

    public void setSelectedAge(String selectedAge) {
        this.selectedAge = selectedAge;
    }

    public String getSelectedSex() {
        return selectedSex;
    }

    public void setSelectedSex(String selectedSex) {
        this.selectedSex = selectedSex;
    }

    public String getSelectedSubrace() {
        return selectedSubrace;
    }

    public void setSelectedSubrace(String selectedSubrace) {
        this.selectedSubrace = selectedSubrace;
    }

    public String getSelectedRace() {
        return selectedRace;
    }

    public void setSelectedRace(String selectedRace) {
        this.selectedRace = selectedRace;
    }

    public int getNumGenInt() {
        if (this.numGenInt == 0) {
            return 25;
        }
        return numGenInt;
    }

    public void setNumGenInt(int numGenInt) {
        this.numGenInt = numGenInt;
    }
}
