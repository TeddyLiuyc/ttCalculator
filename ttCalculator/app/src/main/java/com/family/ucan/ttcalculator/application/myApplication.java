package com.family.ucan.ttcalculator.application;

import android.app.Application;

import com.family.ucan.ttcalculator.db.UserScoreDao;

/**
 * Created by LiuYucan on 15-7-6.
 */
public class myApplication extends Application {
    UserScoreDao userScoreDao = new UserScoreDao(this);
}
