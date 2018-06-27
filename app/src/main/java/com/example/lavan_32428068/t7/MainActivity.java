package com.example.lavan_32428068.t7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    String selectedVoice="avali";
    int h, m;
    int i = 0;
    private TextView hours, minutes;
    private List<Integer> sounds = new ArrayList<>();
    private int loc = 0;
    int[] sounds1 = {
            0, R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4, R.raw.s5,
            R.raw.s6, R.raw.s7, R.raw.s8, R.raw.s9, R.raw.s10,
            R.raw.s11, R.raw.s12, R.raw.s13, R.raw.s14, R.raw.s15,
            R.raw.s16, R.raw.s17, R.raw.s18, R.raw.s19, R.raw.s20,
    };
    int[] my_sounds1={
            0, R.raw.my_s1, R.raw.my_s2, R.raw.my_s3, R.raw.my_s4, R.raw.my_s5,
            R.raw.my_s6, R.raw.my_s7, R.raw.my_s8, R.raw.my_s9, R.raw.my_s10,
            R.raw.my_s11, R.raw.my_s12, R.raw.my_s13, R.raw.my_s14, R.raw.my_s15,
            R.raw.my_s16, R.raw.my_s17, R.raw.my_s18, R.raw.my_s19, R.raw.my_s20,
    };
    int[] sounds1o = {
            0, R.raw.s1o, R.raw.s2o, R.raw.s3o, R.raw.s4o, R.raw.s5o,
            R.raw.s6o, R.raw.s7o, R.raw.s8o, R.raw.s9o, R.raw.s10o,
            R.raw.s11o, R.raw.s12o, R.raw.s13o, R.raw.s14o, R.raw.s15o,
            R.raw.s16o, R.raw.s17o, R.raw.s18o, R.raw.s19o, R.raw.s20o,
    };
    int[] my_sounds1o={
            0, R.raw.my_s1o, R.raw.my_s2o, R.raw.my_s3o, R.raw.my_s4o, R.raw.my_s5o,
            R.raw.my_s6o, R.raw.my_s7o, R.raw.my_s8o, R.raw.my_s9o, R.raw.my_s10o,
            R.raw.my_s11o, R.raw.my_s12o, R.raw.my_s13o, R.raw.my_s14o, R.raw.my_s15o,
            R.raw.my_s16o, R.raw.my_s17o, R.raw.my_s18o, R.raw.my_s19o, R.raw.my_s20o,
    };
    int[] sounds10o = {
            0, R.raw.s10o, R.raw.s20o, R.raw.s30o, R.raw.s40o, R.raw.s50o,
    };
    int[] my_sounds10o = {
            0, R.raw.my_s10o, R.raw.my_s20o, R.raw.my_s30o, R.raw.my_s40o, R.raw.my_s50o,
    };
    int[] sounds10 = {
            0, R.raw.s10, R.raw.s20, R.raw.s30, R.raw.s40, R.raw.s50,
    };
    int[] my_sounds10 = {
            0, R.raw.my_s10, R.raw.my_s20, R.raw.my_s30, R.raw.my_s40, R.raw.my_s50,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hours = (TextView) findViewById(R.id.hours);
        minutes = (TextView) findViewById(R.id.minutes);
        Button say = (Button) findViewById(R.id.say);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button  selected= (Button) findViewById(R.id.selected);

        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    return;
                }

                if(selectedVoice.equals("avali")){
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.saat);
                } else{
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.my_saat);
                }

                loc = 0;
                mediaPlayer.setOnCompletionListener(MainActivity.this);
                mediaPlayer.start();
            }
        });
        Typeface t =  Typeface.createFromAsset(getAssets(), "digital7.ttf");
        hours.setTypeface(t);
        minutes.setTypeface(t);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sounds = new ArrayList<>();
                switch (checkedId) {
                    case R.id.radio24h:
                        sayTime(false);
                        break;
                    case R.id.radio12h:
                        sayTime(true);
                        break;
                }
                sounds.add(0);
            }
        });



        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this /*getBaseContext()*/, Main2Activity.class);
                startActivity(intent);
                finish();

            }
        });






   String getIntenValue = getIntent().getStringExtra("EXTRA_SELECTED_VOICE");

        selectedVoice=getIntenValue;


    }
    public void sayTime(boolean is12) {

        Date d = new Date();
        h = d.getHours();
        m = d.getMinutes();
        if (is12 && h > 12) {
            h -= 12;
        }
        String hs = String.format("%02d", h);
        String ms = String.format("%02d", m);
        hours.setText(hs);
        minutes.setText(ms);

        if (m == 0) {


            if(selectedVoice.equals("avali")){
                sounds.add(sounds1[h]);
            }else{
                sounds.add(my_sounds1[h]);
            }


        } else {
            if (h < 20) {
                if(selectedVoice.equals("avali")){
                    sounds.add(sounds1o[h]);
                }else{
                    sounds.add(my_sounds1o[h]);
                }


            } else {
                int h10 = h / 10;
                int h1 = h % 10;
                if(selectedVoice.equals("avali")){
                    sounds.add(h1 == 0 ? sounds10[h10] : sounds10o[h10]);
                }else{
                    sounds.add(h1 == 0 ? my_sounds10[h10] : my_sounds10o[h10]);
                }

                if (h1 != 0)

                    if(selectedVoice.equals("avali")){
                        sounds.add(sounds1[h1]);
                    }else{
                        sounds.add(my_sounds1[h1]);
                    }

            }
            if (m < 20) {

                if(selectedVoice.equals("avali")){
                    sounds.add(sounds1[m]);
                }else{
                    sounds.add(my_sounds1[m]);
                }

            }
            else {
                int m10 = m / 10;
                int m1 = m % 10;


                if(selectedVoice.equals("avali")){
                    sounds.add(m1 == 0 ? sounds10[m10] : sounds10o[m10]);
                }else{
                    sounds.add(m1 == 0 ? my_sounds10[m10] : my_sounds10o[m10]);
                }


                if (m1 != 0)

                    if(selectedVoice.equals("avali")){
                        sounds.add(sounds1[m1]);
                    }else{
                        sounds.add(my_sounds1[m1]);
                    }

            }
            if(selectedVoice.equals("avali")){
                sounds.add(R.raw.daghigheh);
            }else{
                sounds.add(R.raw.my_daghighe);
            }


        }
        final SharedPreferences sp = getSharedPreferences("my_saat", MODE_PRIVATE);
        sp.edit().putInt("my_saat", h).apply();
    }
    MediaPlayer mediaPlayer;
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (loc < sounds.size() && sounds.get(loc) != 0) {
            mediaPlayer = MediaPlayer.create(this, sounds.get(loc));
            loc++;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();
        }
    }


}


