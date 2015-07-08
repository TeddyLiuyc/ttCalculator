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

public class MinusScoreActivity extends Activity {

    //private static final String TAG = MainActivity.class.getSimpleName();

    int totalScore;
    int todayScore;
    int scoreChange;
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
        setContentView(R.layout.activity_minus_score);

        readSharedPreferences();// get the data from Shared Preferences

        initActionBar();

        findButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_minus_score, menu);
        return true;
    }

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

    void dialogBack() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MinusScoreActivity.this);
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

    void readSharedPreferences() {
        SharedPreferences mySharedPreferences =
                getSharedPreferences("personalInfo", Activity.MODE_PRIVATE);
        totalScore = mySharedPreferences.getInt("total score", 0);
        todayScore = mySharedPreferences.getInt("today score", 0);
    }

    void findButtons() {
        oneBtn = (Button) findViewById(R.id.minus1);
        twoBtn = (Button) findViewById(R.id.minus2);
        fiveBtn = (Button) findViewById(R.id.minus5);
        tenBtn = (Button) findViewById(R.id.minus10);
        twentyBtn = (Button) findViewById(R.id.minus20);
        fiftyBtn = (Button) findViewById(R.id.minus50);
        done = (Button) findViewById(R.id.doneMinus);
        userInput = (EditText) findViewById(R.id.userInputMinus);

        oneBtn.setOnClickListener(new buttonsListener());
        twoBtn.setOnClickListener(new buttonsListener());
        fiveBtn.setOnClickListener(new buttonsListener());
        tenBtn.setOnClickListener(new buttonsListener());
        twentyBtn.setOnClickListener(new buttonsListener());
        fiftyBtn.setOnClickListener(new buttonsListener());
        done.setOnClickListener(new buttonsListener());
    }

    private class buttonsListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            scoreChange = 0;
            switch (v.getId()) {
                case (R.id.minus1):
                    scoreChange = -1;
                    break;
                case (R.id.minus2):
                    scoreChange = -2;
                    break;
                case (R.id.minus5):
                    scoreChange = -5;
                    break;
                case (R.id.minus10):
                    scoreChange = -10;
                    break;
                case (R.id.minus20):
                    scoreChange = -20;
                    break;
                case (R.id.minus50):
                    scoreChange = -50;
                    break;
                case (R.id.doneMinus):
                    scoreChange = Integer.valueOf(userInput.getText().toString());
                default:
                    break;
            }
            dialog(scoreChange);

        }
    }

    protected void dialog(final int scoreChange) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MinusScoreActivity.this);

        builder.setTitle("确定要减" + scoreChange + "分吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
                Toast.makeText(MinusScoreActivity.this,
                        "减了" + (-scoreChange) + "分",
                        Toast.LENGTH_SHORT).show();

                backIntent();

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

    void backIntent() {
        intent = new Intent();
        intent.setClass(this, MainActivity.class);
        setResult(RESULT_OK, intent);
    }
}
