package com.example.administrator.lightsoutgame;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Struct;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button[] mButtons;
    private TextView titleTestView;
    private TextView changeTheTurns;
    private int count = 0;
    int[] nums = new int[7];
    int countOne;
    int countZero;
    int score = 1000;
    private TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTestView = findViewById(R.id.title);
        changeTheTurns = findViewById(R.id.textview);
        Score = findViewById(R.id.score);

        mButtons = new Button[7];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);
        //updateView();
    }

    private void updateView() {
        System.out.print("2333");
    }

    private int RandomNumberBuilt(){
        Random random=new Random();
        int randomNumber = random.nextInt(2);
        return randomNumber;
    }

    public void pressStart(View view) {
        for(int i = 0;i< 7; i++){
            //String Stag = view.getTag().toString();
            //int tag = Integer.parseInt(Stag);
            mButtons[i].setText(RandomNumberBuilt()+"");
            countOne = 0;
            countZero = 0;
            count = 0;
            score = 1000;
            Score.setText("Your Score:");
        }
    }

    public void pressTurns(View view) {
        //This method should  When the user clicks a button, that button and its two neighboring buttons change from 0
        //to 1, or vice versa.
        // Clicking the first or last button toggles only two buttons; there is no wraparound.
        String Stag = view.getTag().toString();
        int tag = Integer.parseInt(Stag);
        if(tag==0){
            int num = Integer.parseInt(String.valueOf(mButtons[tag].getText()));
            int num2 = Integer.parseInt(String.valueOf(mButtons[tag+1].getText()));
            if( num == 0){
                mButtons[tag].setText(String.valueOf(num +1));
                if(num2 == 0){
                    mButtons[tag+1].setText(String.valueOf(num2 + 1));
                }
                if (num2 ==1){
                    mButtons[tag+1].setText(String.valueOf(num2 - 1));
                }

            }
            if(num == 1){
                mButtons[tag].setText(String.valueOf(num-1));
                if(num2 == 0){
                    mButtons[tag+1].setText(String.valueOf(num2 + 1));
                }
                if(num2 == 1){
                    mButtons[tag+1].setText(String.valueOf(num2 - 1));
                }
            }

        }else if(tag == 6){
            int num = Integer.parseInt(String.valueOf(mButtons[tag].getText()));
            int num2 = Integer.parseInt(String.valueOf(mButtons[tag-1].getText()));
            if( num == 0){
                mButtons[tag].setText(String.valueOf(num +1));
                if(num2 == 0){
                    mButtons[tag-1].setText(String.valueOf(num2 +1));
                }
                if(num2 == 1){
                    mButtons[tag-1].setText(String.valueOf(num2 -1));
                }

            }
            if(num == 1){
                mButtons[tag].setText(String.valueOf(num-1));
                if(num2 == 0){
                    mButtons[tag-1].setText(String.valueOf(num2 +1));
                }
                if(num2 == 1){
                    mButtons[tag-1].setText(String.valueOf(num2 -1));
                }
            }
        }else{
            int num = Integer.parseInt(String.valueOf(mButtons[tag].getText()));
            int num1 = Integer.parseInt(String.valueOf(mButtons[tag-1].getText()));
            int num2 = Integer.parseInt(String.valueOf(mButtons[tag+1].getText()));
            if( num == 0){
                mButtons[tag].setText(String.valueOf(num +1));
                if(num1 == 0){
                    mButtons[tag-1].setText(String.valueOf(num1 +1));
                }
                else if(num1 == 1){
                    mButtons[tag-1].setText(String.valueOf(num1 - 1));
                }
                if(num2 == 0){
                    mButtons[tag+1].setText(String.valueOf(num2 +1));
                }
                else if(num2 == 1){
                    mButtons[tag+1].setText(String.valueOf(num2 - 1));
                }
            }
            else if(num == 1){
                mButtons[tag].setText(String.valueOf(num-1));
                if(num1 == 0){
                    mButtons[tag-1].setText(String.valueOf(num1 +1));
                }
                else if(num1 == 1){
                    mButtons[tag-1].setText(String.valueOf(num1 - 1));
                }
                if(num2 == 0){
                    mButtons[tag+1].setText(String.valueOf(num2 +1));
                }
                else if(num2 == 1){
                    mButtons[tag+1].setText(String.valueOf(num2 - 1));
                }
            }
        }
        checkIfWin();
    }

    private void checkIfWin() {
        countOne=0;
        countZero = 0;
        for(int i = 0; i< 7; i ++){
            nums[i] = Integer.parseInt(String.valueOf(mButtons[i].getText()));
            if(nums[i] == 0){
                countZero ++;
            }else {
                countOne ++;
            }
        }
        count ++;
        score =score - 10;
        Score.setText("Your Score:" + score);
        if(countOne == 7 || countZero == 7 ){
            changeTheTurns.setText("YOU WIN!");
        }else{
            //changeTheTurns.setText(getString(R.id.textview ,count ));
            changeTheTurns.setText("This your "+ count + " truns!");
        }
    }
}
