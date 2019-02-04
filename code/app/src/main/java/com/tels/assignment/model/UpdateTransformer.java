package com.tels.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTransformer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("strength")
    @Expose
    private Integer strength;
    @SerializedName("intelligence")
    @Expose
    private Integer intelligence;
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @SerializedName("endurance")
    @Expose
    private Integer endurance;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("courage")
    @Expose
    private Integer courage;
    @SerializedName("firepower")
    @Expose
    private Integer firepower;
    @SerializedName("skill")
    @Expose
    private Integer skill;
    @SerializedName("team")
    @Expose
    private String team;

    public UpdateTransformer(Transformer mTransformer) {
        this.setId(mTransformer.getId());
        this.setName(mTransformer.getName());
        this.setStrength(mTransformer.getStrength());
        this.setIntelligence(mTransformer.getIntelligence());
        this.setSpeed(mTransformer.getSpeed());
        this.setEndurance(mTransformer.getEndurance());
        this.setRank(mTransformer.getRank());
        this.setCourage(mTransformer.getCourage());
        this.setFirepower(mTransformer.getFirepower());
        this.setSkill(mTransformer.getSkill());
        this.setTeam(mTransformer.getTeam());
    }

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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}
