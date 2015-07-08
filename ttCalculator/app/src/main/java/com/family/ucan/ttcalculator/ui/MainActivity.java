package com.family.ucan.ttcalculator.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.family.ucan.ttcalculator.bean.UserScore;
import com.family.ucan.ttcalculator.R;
import com.family.ucan.ttcalculator.db.UserScoreDao;

public class MainActivity extends Activity {

    int totalScore;
    int todayScore;
    TextView totalScoreView;
    TextView todayScoreView;
    Button historyBtn;
    Button plusScoreBtn;
    Button minusScoreBtn;
    Button resetBtn;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;
    UserScoreDao userScoreDao;
    UserScore userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPreferences = getSharedPreferences("personalInfo",
                Activity.MODE_PRIVATE);
        editor = mySharedPreferences.edit();

        initValues();// initial all values;

        clickPlusBtn();// if click the plus button
        clickMinusBtn();// if click the minus button
        clickResetBtn();// if click the reset button
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // initial values and find ids.
    void initValues() {
        totalScoreView = (TextView) findViewById(R.id.totalScore);
        todayScoreView = (TextView) findViewById(R.id.todayScore);
        historyBtn = (Button) findViewById(R.id.history);
        plusScoreBtn = (Button) findViewById(R.id.addScore);
        minusScoreBtn = (Button) findViewById(R.id.minusScore);
        resetBtn = (Button) findViewById(R.id.reset);
        displayScores();// set displays
    }

    // display on screen
    void displayScores() {
        readSharedPreferences();// get the data from Shared Preferences
        totalScoreView.setText(Integer.toString(totalScore));
        todayScoreView.setText(Integer.toString(todayScore));
    }

    // go to Plus activity
    void clickPlusBtn() {
        plusScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PlusScoreActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    // go to Minus activity
    void clickMinusBtn() {
        minusScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MinusScoreActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    // reset the scores
    void clickResetBtn() {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
    }

    // show alert dialog for reset
    protected void alertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("确定要重置吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.clear().commit();
                displayScores();
                Toast.makeText(MainActivity.this, "分数已重置", Toast.LENGTH_SHORT).show();
                dialog.dismiss();// cancel dialog
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    // read the data
    void readSharedPreferences() {
        totalScore = mySharedPreferences.getInt("total score", 0);
        todayScore = mySharedPreferences.getInt("today score", 0);
        Toast.makeText(this, "累计分数：" + totalScore + "\n" +
                        "今日分数：" + todayScore,
                Toast.LENGTH_SHORT).show();
    }

    // get result from plus or minus activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                displayScores();
                break;
            default:
                break;
        }
    }
}
