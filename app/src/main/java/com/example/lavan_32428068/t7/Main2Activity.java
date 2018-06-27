package com.example.lavan_32428068.t7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    String selectedVoice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RadioGroup radioGroup = findViewById(R.id.radioGroup2);
        RadioButton avali =(RadioButton) findViewById(R.id.avali);
        RadioButton dovomi =(RadioButton) findViewById(R.id.dovomi);


        String vo=SharedPRF.read("sounds");
        if(vo !=null){
            selectedVoice=vo;

            if(vo.equals("avali")){
                avali.setChecked(true);
            }
            if(vo.equals("dovomi")){
                dovomi.setChecked(true);
            }

        }
        else{
            selectedVoice="avali";
            avali.setChecked(true);
        }

        Button ok = (Button) findViewById(R.id.ok);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.avali:
                        selectedVoice="avali";
                        SharedPRF.write("sounds",selectedVoice);
                        break;
                    case R.id.dovomi:
                        selectedVoice="dovomi";
                        SharedPRF.write("sounds",selectedVoice);
                        break;
                }

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("EXTRA_SELECTED_VOICE", selectedVoice);
                startActivity(intent);
                finish();
            }
        });

    }
}
