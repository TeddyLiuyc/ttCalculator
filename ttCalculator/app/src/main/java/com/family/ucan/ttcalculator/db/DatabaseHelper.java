package com.family.ucan.ttcalculator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.family.ucan.ttcalculator.bean.UserScore;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by LiuYucan on 15-7-6.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    private static final String DB_NAME = "information.db";
    private Dao<UserScore, Integer> userScoreDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, UserScore.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource,
                          int i, int i1) {
        try
        {
            TableUtils.dropTable(connectionSource, UserScore.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context)
    {
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public Dao<UserScore, Integer> getUserScoreDao() throws SQLException
    {
        if (userScoreDao == null)
        {
            userScoreDao = getDao(UserScore.class);
        }
        return userScoreDao;
    }

    @Override
    public void close()
    {
        super.close();
        userScoreDao = null;
    }
}
