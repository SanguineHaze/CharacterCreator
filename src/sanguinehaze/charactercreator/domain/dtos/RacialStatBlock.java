package sanguinehaze.charactercreator.domain.dtos;

import java.util.ArrayList;
import java.util.List;

public class RacialStatBlock {
    private String name;
    private String size;
    private String source;
    private String parentId;
    private List<String> language;
    private List<String> extra;
    private List<String> extraChoice;
    private int bonusStr;
    private int bonusDex;
    private int bonusCon;
    private int bonusInt;
    private int bonusWis;
    private int bonusCha;
    private int speed;
    private int flySpeed;
    private int swimSpeed;
    private boolean isSubrace;

    public RacialStatBlock() {
        name = "Not Set";
        parentId = "Not Set";
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
        language = new ArrayList<>();
        extra = new ArrayList<>();
        extraChoice = new ArrayList<>();
        source = "Not Set";
        isSubrace = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void addLanguage(String language) {
        this.language.add(language);
    }

    public List<String> getExtra() {
        return extra;
    }

    public void addExtra(String extra) {
        this.extra.add(extra);
    }

    public List<String> getExtraChoice() {
        return extraChoice;
    }

    public void addExtraChoice(String extraChoice) {
        this.extraChoice.add(extraChoice);
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    public int getSwimSpeed() {
        return swimSpeed;
    }

    public void setSwimSpeed(int swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    public boolean isSubrace() {
        return isSubrace;
    }

    public void setSubrace(boolean subrace) {
        isSubrace = subrace;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public void setExtra(List<String> extra) {
        this.extra = extra;
    }

    public void setExtraChoice(List<String> extraChoice) {
        this.extraChoice = extraChoice;
    }
}
