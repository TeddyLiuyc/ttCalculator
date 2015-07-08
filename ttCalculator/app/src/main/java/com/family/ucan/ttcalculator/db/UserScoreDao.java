package com.family.ucan.ttcalculator.db;

import android.content.Context;

import com.family.ucan.ttcalculator.bean.UserScore;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LiuYucan on 15-7-6.
 */
public class UserScoreDao {

    private Context context;

    public UserScoreDao(Context context){
        this.context = context;
    }

    public void addUserScore(UserScore userScore){
        try{
            DatabaseHelper.getHelper(context).getUserScoreDao().create(userScore);
        }catch(SQLException e){
        }
    }

    public void deleteUserScore(int id){
        try{
            DatabaseHelper.getHelper(context).getUserScoreDao().deleteById(id);
        }catch (SQLException e){
        }
    }

    public void updateUserScore(UserScore userScore){
        try{
            DatabaseHelper.getHelper(context).getUserScoreDao().update(userScore);
        }catch (SQLException e){
        }
    }

    public List<UserScore> findUserScore() {
        List<UserScore> result = null;
        try {
            result = DatabaseHelper.getHelper(context).getUserScoreDao().queryForAll();
        } catch (SQLException e) {
        }
        return result;
    }
}
