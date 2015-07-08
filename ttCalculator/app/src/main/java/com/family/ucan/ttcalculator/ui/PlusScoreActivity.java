package com.family.ucan.ttcalculator.ui;

import android.app.ActionBar;
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
import android.widget.EditText;
import android.widget.Toast;

import com.family.ucan.ttcalculator.R;

public class PlusScoreActivity extends Activity {

    //private static final String TAG = MainActivity.class.getSimpleName();

    int totalScore;
    int todayScore;
    Button oneBtn;
    Button twoBtn;
    Button fiveBtn;
    Button tenBtn;
    Button twentyBtn;
    Button fiftyBtn;
    Intent intent;
    EditText userInput;
    Button done;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_score);

        readSharedPreferences();// get the data from Shared Preferences

        initActionBar();// initial the back button on actionBar

        findButtons();// find views by ids
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_score, menu);
        return true;
    }

    // back to MainActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //back to last activity
        switch (item.getItemId()) {
            case android.R.id.home:
                dialogBack();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // show the back button in the Action Bar
    void initActionBar() {
        //display the back button in the ActionBar
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // dialog to make sure whether go back or not
    void dialogBack() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlusScoreActivity.this);
        builder.setTitle("确定要离开吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
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

    // get the data
    void readSharedPreferences() {
        SharedPreferences mySharedPreferences =
                getSharedPreferences("personalInfo", Activity.MODE_PRIVATE);
        totalScore = mySharedPreferences.getInt("total score", 0);
        todayScore = mySharedPreferences.getInt("today score", 0);
    }

    // find views by ids
    void findButtons() {
        oneBtn = (Button) findViewById(R.id.plus1);
        twoBtn = (Button) findViewById(R.id.plus2);
        fiveBtn = (Button) findViewById(R.id.plus5);
        tenBtn = (Button) findViewById(R.id.plus10);
        twentyBtn = (Button) findViewById(R.id.plus20);
        fiftyBtn = (Button) findViewById(R.id.plus50);
        done = (Button) findViewById(R.id.doneAdd);
        userInput = (EditText) findViewById(R.id.userInputAdd);

        oneBtn.setOnClickListener(new buttonsListener());
        twoBtn.setOnClickListener(new buttonsListener());
        fiveBtn.setOnClickListener(new buttonsListener());
        tenBtn.setOnClickListener(new buttonsListener());
        twentyBtn.setOnClickListener(new buttonsListener());
        fiftyBtn.setOnClickListener(new buttonsListener());
        done.setOnClickListener(new buttonsListener());
    }

    // wait buttons for click
    private class buttonsListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int scoreChange = 0;
            switch (v.getId()) {
                case (R.id.plus1):
                    scoreChange = 1;
                    break;
                case (R.id.plus2):
                    scoreChange = 2;
                    break;
                case (R.id.plus5):
                    scoreChange = 5;
                    break;
                case (R.id.plus10):
                    scoreChange = 10;
                    break;
                case (R.id.plus20):
                    scoreChange = 20;
                    break;
                case (R.id.plus50):
                    scoreChange = 50;
                    break;
                case (R.id.doneAdd):
                    scoreChange = Integer.valueOf(userInput.getText().toString());
                default:
                    break;
            }
            dialog(scoreChange);

        }
    }

    // show dialog
    protected void dialog(final int scoreChange) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlusScoreActivity.this);

        builder.setTitle("确定要加" + scoreChange + "分吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {// positive
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();// cancel dialog
                // renew the preferences
                SharedPreferences mySharedPreferences = getSharedPreferences("personalInfo",
                        Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                totalScore += scoreChange;
                todayScore += scoreChange;
                editor.putInt("total score", totalScore);
                editor.putInt("today score", todayScore);
                editor.commit();
                Toast.makeText(PlusScoreActivity.this,
                        "加了" + scoreChange + "分",
                        Toast.LENGTH_SHORT).show();

                backIntent();

                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {// negative
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    // back to MainActivity
    void backIntent() {
        intent = new Intent();
        intent.setClass(this, MainActivity.class);
        setResult(RESULT_OK, intent);
    }
}
