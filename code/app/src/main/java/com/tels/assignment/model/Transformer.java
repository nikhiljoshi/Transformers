package com.tels.assignment.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transformer {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("team")
    private String team;

    @SerializedName("strength")
    private Integer strength;

    @SerializedName("intelligence")
    private Integer intelligence;

    @SerializedName("speed")
    private Integer speed;

    @SerializedName("endurance")
    private Integer endurance;

    @SerializedName("rank")
    private Integer rank;

    @SerializedName("courage")
    private Integer courage;

    @SerializedName("firepower")
    private Integer firepower;

    @SerializedName("skill")
    private Integer skill;

    @SerializedName("team_icon")
    private String teamIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCourage() {
        return courage;
    }

    public void setCourage(Integer courage) {
        this.courage = courage;
    }

    public Integer getFirepower() {
        return firepower;
    }

    public void setFirepower(Integer firepower) {
        this.firepower = firepower;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public String getTeamIcon() {
        return teamIcon;
    }

    public void setTeamIcon(String teamIcon) {
        this.teamIcon = teamIcon;
    }

}