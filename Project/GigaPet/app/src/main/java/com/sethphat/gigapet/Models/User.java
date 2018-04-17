package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class User {
    // USER INFORMATION
    private int ID;
    private String PetName;
    private int Gold;
    private int BackgroundIMG = 0; // default background

    /** PET INFORMATION
     * 1 => DOG
     * 2 => CAT
     * 3 => HAMSTER
     */
    private int Type;
    private int Evolution;
    private int Heart;
    private int Experience;
    private int PetSkin;

    // PET Status
    private int Hunger;
    private int Thirsty;
    private int Fun;
    private int Hygiene;
    private int Energy;
    private int Bladder;

    // Play status
    private int LastTime;

    public User() {

    }

    public User(int ID, String petName, int gold, int backgroundIMG, int type, int evolution, int heart, int experience, int petSkin, int hunger, int thirsty, int fun, int hygiene, int energy, int bladder, int lastTime) {
        this.ID = ID;
        PetName = petName;
        Gold = gold;
        BackgroundIMG = backgroundIMG;
        Type = type;
        Evolution = evolution;
        Heart = heart;
        Experience = experience;
        PetSkin = petSkin;
        Hunger = hunger;
        Thirsty = thirsty;
        Fun = fun;
        Hygiene = hygiene;
        Energy = energy;
        Bladder = bladder;
        LastTime = lastTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public int getGold() {
        return Gold;
    }

    public void setGold(int gold) {
        Gold = gold;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getHeart() {
        return Heart;
    }

    public void setHeart(int heart) {
        Heart = heart;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public int getHunger() {
        return Hunger;
    }

    public void setHunger(int hunger) {
        Hunger = hunger;
    }

    public int getThirsty() {
        return Thirsty;
    }

    public void setThirsty(int thirsty) {
        Thirsty = thirsty;
    }

    public int getFun() {
        return Fun;
    }

    public void setFun(int fun) {
        Fun = fun;
    }

    public int getHygiene() {
        return Hygiene;
    }

    public void setHygiene(int hygiene) {
        Hygiene = hygiene;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getBladder() {
        return Bladder;
    }

    public void setBladder(int bladder) {
        Bladder = bladder;
    }

    public int getLastTime() {
        return LastTime;
    }

    public void setLastTime(int lastTime) {
        LastTime = lastTime;
    }

    public int getBackgroundIMG() {
        return BackgroundIMG;
    }

    public void setBackgroundIMG(int backgroundIMG) {
        BackgroundIMG = backgroundIMG;
    }

    public int getPetSkin() {
        return PetSkin;
    }

    public void setPetSkin(int petSkin) {
        PetSkin = petSkin;
    }

    public int getEvolution() {
        return Evolution;
    }

    public void setEvolution(int evolution) {
        Evolution = evolution;
    }
}
