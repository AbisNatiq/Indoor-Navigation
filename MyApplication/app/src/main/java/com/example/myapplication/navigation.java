package com.example.myapplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.DrawableWrapper;
import android.hardware.*;

import android.os.Build;
import android.os.Bundle;


import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class navigation extends Activity implements SensorEventListener, TextToSpeech.OnInitListener {
    private TextToSpeech mtts;
    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    int steps;
    String so;
    String de;
    TextView sr;
    TextView dt,stat;
    ImageView iv;
    private int status;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        count = (TextView) findViewById(R.id.count);
        sr = (TextView) findViewById(R.id.textView3);
        dt = (TextView) findViewById(R.id.textView4);
        stat=(TextView) findViewById(R.id.textView5);
        iv=(ImageView) findViewById(R.id.imageView);
        so=getIntent().getExtras().getString("sour");
        de=getIntent().getExtras().getString("dest");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        steps=-1;
        sr.setText(so);
        dt.setText(de);
        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    mtts.setLanguage(Locale.US);
                }
            }
        });

        }





@Override
    protected void onResume () {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause () {
        super.onPause();
        activityRunning = false;

// if you unregister the last listener, the hardware will stop detecting step events
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged (SensorEvent event){


        if (activityRunning) {
            steps = steps + 1;
            count.setText(String.valueOf(steps));
            //, "SECO", "TECO", "BECO", "LAB 1", "LAB 2", "LAB 3", "LAB 4", "LAB 5", "LAB 6", "LAB 7", "LAB 8
            if(so.equals("SECO")&&de.equals("TECO")) {

                if(steps==1) {
                    stat.setText("Walk straight for 30 steps");
                    iv.setImageResource(R.drawable.straight);
                    mtts.speak("Walk straight for 30 steps", TextToSpeech.QUEUE_FLUSH, null);
                }else if(steps==30){
                    mtts.speak("Your destination is arrived", TextToSpeech.QUEUE_FLUSH, null);
                    stat.setText("Your destination is arrived");
                }
            }else if(so.equals("TECO")&&de.equals("SECO")) {
                if(steps==1) {
                    stat.setText("Walk straight for 30 steps");
                    iv.setImageResource(R.drawable.straight);
                    mtts.speak("Walk straight for 30 steps", TextToSpeech.QUEUE_FLUSH, null);
                }else if(steps==30){
                    mtts.speak("Your destination is arrived", TextToSpeech.QUEUE_FLUSH, null);
                    stat.setText("Your destination is arrived");
                }
            }else if(so.equals("SECO")&&de.equals("BECO")){
                if(steps==1) {
                    stat.setText("take left and walk for 10 steps");
                    iv.setImageResource(R.drawable.left);
                    mtts.speak("take left and walk for 10 steps", TextToSpeech.QUEUE_FLUSH, null);
                }else if(steps==10){
                    mtts.speak("Your destination is arrived", TextToSpeech.QUEUE_FLUSH, null);
                    stat.setText("Your destination is arrived");
                }
            }else if(so.equals("BECO")&&de.equals("SECO")){
                if(steps==1) {
                    stat.setText("take right and walk for 10 steps");
                    iv.setImageResource(R.drawable.right);
                    mtts.speak("take right and walk for 10 steps", TextToSpeech.QUEUE_FLUSH, null);
                }else if(steps==10){
                    mtts.speak("Your destination of BECO is arrived", TextToSpeech.QUEUE_FLUSH, null);
                    stat.setText("Your destination is arrived");
                }
            }else if(so.equals("SECO")&&de.equals("LAB!")){
                if(steps==1) {
                    stat.setText("take right and walk for 30 steps");
                    iv.setImageResource(R.drawable.straight);
                    mtts.speak("walk straight for 30 steps", TextToSpeech.QUEUE_FLUSH, null);
                }else if(steps==29){
                    mtts.speak("take right", TextToSpeech.QUEUE_FLUSH, null);
                    iv.setImageResource(R.drawable.right);
                    stat.setText("take right");
                }else if(steps==31){
                    mtts.speak("walk straight for 20 steps", TextToSpeech.QUEUE_FLUSH, null);
                    iv.setImageResource(R.drawable.straight);
                    stat.setText("walk straight for 20 steps");
                }else if(steps==51){
                    mtts.speak("Your destination is arrived", TextToSpeech.QUEUE_FLUSH, null);
                    iv.setImageResource(R.drawable.straight);
                    stat.setText("Your destination is arrived");
                }
            }
        }


    }

    @Override
    public void onAccuracyChanged (Sensor sensor,int accuracy){
    }

    public void backclk(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }

    @Override
    public void onInit(int status) {
    }
}
