package com.tulsie.digitaltasbeeh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.tulsie.digitaltasbeeh.R;

public class MainActivity extends AppCompatActivity {

    private TextClock textClock, textAMPM, textDate;
    private TextView textView1;
    private TextView startButton, resetButton, stopButton;
    private ImageView imageView1;
    boolean isStart = false;
    int countNumber=0;

    AlertDialog alertDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textClock = findViewById(R.id.textClockId);
        textAMPM = findViewById(R.id.textAMPMId);
        textDate = findViewById(R.id.textDateId);
        textView1 = findViewById(R.id.textView);
        imageView1 = findViewById(R.id.clickImage);
        startButton = findViewById(R.id.settingsButton);
        resetButton = findViewById(R.id.resetButton);
        stopButton = findViewById(R.id.historyButton);


        Typeface myFont = Typeface.createFromAsset(getApplicationContext().getAssets(),"font/digital_font.ttf");

        textClock.setTypeface(myFont);
        textAMPM.setTypeface(myFont);
        textDate.setTypeface(myFont);
        textView1.setTypeface(myFont);
        startButton.setTypeface(myFont);
        resetButton.setTypeface(myFont);
        stopButton.setTypeface(myFont);


        /*When start Button is long pressed*/
        
        startButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showStartDialog();
                return false;
            }
        });

        //When stop button is long pressed
        stopButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showStopDialog();
                return false;
            }

            private void showStopDialog() {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.stop_button,null);
                alertDialog = new AlertDialog.Builder(MainActivity.this).setView(view).create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                TextView stopText = view.findViewById(R.id.stopText);
                Typeface myFont = Typeface.createFromAsset(getApplicationContext().getAssets(),"font/hind_siliguri.ttf");
                stopText.setTypeface(myFont);
                Button cancel_button = view.findViewById(R.id.stop_cancel_button);
                Button ok_button = view.findViewById(R.id.stop_ok_button);
                ok_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isStart=false;
                        Toast.makeText(MainActivity.this,"আপনি গননা বন্ধ করেছেন।", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        /* When reset Button is long pressed*/

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showResetDialog();
                return false;
            }

            private void showResetDialog() {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.reset_button,null);
                alertDialog = new AlertDialog.Builder(MainActivity.this).setView(view).create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                TextView resetText = view.findViewById(R.id.resetText);
                Typeface myFont = Typeface.createFromAsset(getApplicationContext().getAssets(),"font/hind_siliguri.ttf");
                resetText.setTypeface(myFont);
                Button cancel_button = view.findViewById(R.id.reset_cancel_button);
                Button ok_button = view.findViewById(R.id.reset_ok_button);
                ok_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countNumber=0;
                        textView1.setText(""+countNumber);
                        Toast.makeText(MainActivity.this,"পুনরায় শুরু করা হয়ে হয়েছে।", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });


        //When image Button is pressed
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isStart==true){
                    imageView1.setImageResource(R.drawable.clicked_button);
                        countNumber++;
                        textView1.setText(""+countNumber);
                    imageView1.setImageResource(R.drawable.normal_button);
                }
                else{

                }

            }
        });
    }


    public void showStartDialog(){


        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.cleanliness_dialogue,null);

        alertDialog = new AlertDialog.Builder(MainActivity.this).setView(view).create();
        alertDialog.show();
        alertDialog.setCancelable(false);


        TextView ajuText = view.findViewById(R.id.ajuText);
        Typeface myFont = Typeface.createFromAsset(getApplicationContext().getAssets(),"font/hind_siliguri.ttf");
        ajuText.setTypeface(myFont);

        Button cancel_button = view.findViewById(R.id.canceBtn);
        Button ok_button = view.findViewById(R.id.okBtn);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_Counting();
            }

            private void start_Counting() {
                isStart = true;
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this,"আপনি গননা শুরু করেছেন।", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_Dialogue();
            }

            private void cancel_Dialogue() {
                alertDialog.dismiss();
            }
        });


    }
}