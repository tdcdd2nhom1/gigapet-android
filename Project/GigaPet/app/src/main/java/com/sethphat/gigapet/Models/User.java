package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class User {
    // USER INFORMATION
    private int ID;
    private String PetName;
    private int Gold;

    /** PET INFORMATION
     * 1 => DOG
     * 2 => CAT
     * 3 => HAMSTER
     */
    private int Type;
    private int Heart;
    private int Experience;

    // PET Status
    private int Hunger;
    private int Thirsty;
    private int Fun;
    private int Hygiene;
    private int Enegy;
    private int Bladder;

    // Play status
    private int LastTime;

    public User() {

    }

    public User(int ID, String petName, int gold, int type, int heart, int experience, int hunger, int thirsty, int fun, int hygiene, int enegy, int bladder, int lastTime) {
        this.ID = ID;
        PetName = petName;
        Gold = gold;
        Type = type;
        Heart = heart;
        Experience = experience;
        Hunger = hunger;
        Thirsty = thirsty;
        Fun = fun;
        Hygiene = hygiene;
        Enegy = enegy;
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

    public int getEnegy() {
        return Enegy;
    }

    public void setEnegy(int enegy) {
        Enegy = enegy;
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
}
