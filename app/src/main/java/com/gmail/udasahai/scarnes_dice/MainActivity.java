package com.gmail.udasahai.scarnes_dice;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;



    int user_score = 0;
    int user_round_score = 0;

    int comp_score = 0;
    int comp_round_score = 0;
    Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roll_dice(View view) {

        TextView txt = (TextView) findViewById(R.id.textView5);
        txt.setText("Your Chance");

         btn1 = (Button) findViewById(R.id.button);
         btn2 = (Button) findViewById(R.id.button2);
         btn3 = (Button) findViewById(R.id.button3);


            int randomNum = dice_roll();

            if (randomNum == 0) {
                user_score = 0;
                user_round_score = 0;
                set_score();
                txt = (TextView) findViewById(R.id.textView5);
                txt.setText("Computer Chance");
                btn1.setEnabled(false);
                btn2.setEnabled(false);

                Runnable runnableCode = new Runnable() {
                    @Override
                    public void run() {


                        computer_turn();

                    }
                };

                handler.postDelayed(runnableCode, 1500);

               // computer_turn();
            } else {
                user_round_score += randomNum + 1;
            }


    }


    public void hold(View view)
    {
        //btn3.setEnabled(false);
        user_score += user_round_score;
        user_round_score=0;
        set_score();
         user_round_score=0;

        TextView txt = (TextView) findViewById(R.id.textView5);
        txt.setText("Computer Chance");

        btn1.setEnabled(false);
        btn2.setEnabled(false);

        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {



                computer_turn();

            }
        };

        handler.postDelayed(runnableCode, 500);



    }

    public void set_score()
    {
        String str = "User Score: " + user_score + " Computer Score: " + comp_score;
        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(str);
    }




    public void Reset_Button(View view)
    {
        TextView txt = (TextView) findViewById(R.id.textView5);
        txt.setText("Reset");

        ImageView dice_view = (ImageView) findViewById(R.id.imageView);
        dice_view.setImageResource(getResources().getIdentifier("dice1","drawable",getPackageName()));

        user_score = user_round_score = comp_round_score = comp_score =0;
        set_score();

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }


    public void computer_turn()
    {

        do {

            int  val = dice_roll();
            try {

                Thread.sleep(4000);

            } catch (Exception e){
                // handle exception
            }
//            try {
//
//                TimeUnit.MILLISECONDS.sleep(500);
//
//            } catch (InterruptedException e) {
//                //Handle exception
//            }



            if (val == 0) {
                comp_score = 0;
                comp_round_score = 0;
                break;
            } else {
                comp_round_score += val + 1;
            }
        } while (comp_round_score <= 10);

        comp_score += comp_round_score;
        comp_round_score=0;

        btn1.setEnabled(true);
        btn2.setEnabled(true);

        TextView txt = (TextView) findViewById(R.id.textView5);
        txt.setText("Your Chance");

        set_score();


    }

    public int dice_roll()
    {
        Random rand = new Random();
        int randomNum  = rand.nextInt(6);

        if ( randomNum == 0)
            randomNum = 0;


        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(getResources().getIdentifier("dice1","drawable",getPackageName()));
        list.add(getResources().getIdentifier("dice2","drawable",getPackageName()));
        list.add(getResources().getIdentifier("dice3","drawable",getPackageName()));
        list.add(getResources().getIdentifier("dice4","drawable",getPackageName()));
        list.add(getResources().getIdentifier("dice5","drawable",getPackageName()));
        list.add(getResources().getIdentifier("dice6","drawable",getPackageName()));



        ImageView dice_view = (ImageView) findViewById(R.id.imageView);

        Handler handler = new Handler();
// Define the code block to be executed
        dice_view.setImageResource(list.get(randomNum));


        return randomNum;


    }

}

