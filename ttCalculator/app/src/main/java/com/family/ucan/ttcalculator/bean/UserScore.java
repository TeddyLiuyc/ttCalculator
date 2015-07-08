package com.family.ucan.ttcalculator.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LiuYucan on 15-7-6.
 */

@DatabaseTable(tableName = "tableUserScore")
public class UserScore {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "totalScore")
    private int totalScore;
    @DatabaseField(columnName = "todayScore")
    private int todayScore;
    @DatabaseField(columnName = "lastScore")
    private int lastScore;

    public UserScore(){
    }

    public UserScore(String name, int totalScore, int todayScore, int lastScore){
        this.name = name;
        this.totalScore = totalScore;
        this.todayScore = todayScore;
        this.lastScore = lastScore;
    }

    public int getId(){
        return  id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getTotalScore(){
        return totalScore;
    }
    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }
    public int getTodayScore(){
        return todayScore;
    }
    public void setTodayScore(int todayScore){
        this.todayScore = todayScore;
    }
    public int getLastScore(){
        return lastScore;
    }
    public void setLastScore(int lastScore){
        this.lastScore = lastScore;
    }
}
