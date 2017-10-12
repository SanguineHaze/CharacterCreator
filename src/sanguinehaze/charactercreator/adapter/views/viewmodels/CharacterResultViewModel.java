package sanguinehaze.charactercreator.adapter.views.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharacterResultViewModel {

    private String name;
    private String race;
    private String subrace;
    private String sex;
    private String age;
    private String profession;
    private String motivation;
    private String personalityTraits;
    private String additionalDetail;
    private String additionalItem;
    private String speed;
    private String swimSpeed;
    private String flySpeed;
    private String strength;
    private String dexterity;
    private String constitution;
    private String intellect;
    private String wisdom;
    private String charisma;
    private List<String> languages;
    private List<String> extras;
    private List<String> extraChoice;

    public CharacterResultViewModel() {
        this.languages = new ArrayList<>();
        this.extras = new ArrayList<>();
        this.extraChoice = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Optional<String> getSubrace() {
        return Optional.ofNullable(subrace);
    }

    public void setSubrace(String subrace) {
        this.subrace = subrace;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Optional<String> getProfession() {
        return Optional.ofNullable(profession);
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Optional<String> getMotivation() {
        return Optional.ofNullable(motivation);
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getPersonalityTraits() {
        return personalityTraits;
    }

    public void setPersonalityTraits(String personalityTraits) {
        this.personalityTraits = personalityTraits;
    }

    public Optional<String> getAdditionalDetail() {
        return Optional.ofNullable(additionalDetail);
    }

    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }

    public Optional<String> getAdditionalItem() {
        return Optional.ofNullable(additionalItem);
    }

    public void setAdditionalItem(String additionalItem) {
        this.additionalItem = additionalItem;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Optional<String> getSwimSpeed() {
        return Optional.ofNullable(swimSpeed);
    }

    public void setSwimSpeed(String swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    public Optional<String> getFlySpeed() {
        return Optional.ofNullable(flySpeed);
    }

    public void setFlySpeed(String flySpeed) {
        this.flySpeed = flySpeed;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getDexterity() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        this.dexterity = dexterity;
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    public String getIntellect() {
        return intellect;
    }

    public void setIntellect(String intellect) {
        this.intellect = intellect;
    }

    public String getWisdom() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        this.wisdom = wisdom;
    }

    public String getCharisma() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        this.charisma = charisma;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public List<String> getExtraChoice() {
        return extraChoice;
    }

    public void setExtraChoice(List<String> extraChoice) {
        this.extraChoice = extraChoice;
    }
}
