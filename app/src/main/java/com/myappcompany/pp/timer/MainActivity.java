package com.myappcompany.pp.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int minutes, sec;
    TextView text;
    SeekBar bar;
    Button go;
    CountDownTimer time;
    boolean active=false;

    public void reset(){
        text.setText("00:30");
        bar.setProgress(30);
        bar.setEnabled(true);
        time.cancel();
        go.setText("GO!");
        active=false;
    }
    public void onClick(View view){
        if(active)
        {
            reset();
        }
        else {
            go.setText("Reset");
            active = true;
            bar.setEnabled(false);
            time = new CountDownTimer(bar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Times Up!", Toast.LENGTH_SHORT).show();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mplayer.start();
                    reset();
                }
            }.start();
        }
    }

    public void updateTimer(int i){
        minutes=i/60;
        sec=i-(minutes*60);
        String secstr=Integer.toString(sec);
        String minstr=Integer.toString(minutes);
        if(sec<=9)
        {
            secstr="0"+secstr;
        }
        if(minutes<=9)
        {
            minstr="0"+minstr;
        }
        text.setText(minstr+":"+secstr);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=findViewById(R.id.bar);
        go=findViewById(R.id.button);
         text= findViewById(R.id.textView);
        int max=600;
        int startingpos=30;
        bar.setMax(max);
        bar.setProgress(startingpos);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
              }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
