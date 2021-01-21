package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
    ImageView imageView;
    Spinner source;
    Spinner destination;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView2);
        source = (Spinner) findViewById(R.id.spinner);
        destination = (Spinner) findViewById(R.id.spinner2);
        String[] place_s = {"select source", "SECO", "TECO", "BECO", "LAB 1", "LAB 2", "LAB 3", "LAB 4", "LAB 5", "LAB 6", "LAB 7", "LAB 8"};
        ArrayAdapter<String> adapter_s = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, place_s);
        String[] place_d = {"select destination", "SECO", "TECO", "BECO", "LAB 1", "LAB 2", "LAB 3", "LAB 4", "LAB 5", "LAB 6", "LAB 7", "LAB 8"};
        ArrayAdapter<String> adapter_d = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, place_d);
        source.setAdapter(adapter_s);
        destination.setAdapter(adapter_d);
    }

    public void btnClick(View v) {
        String s = source.getSelectedItem().toString();
        String d = destination.getSelectedItem().toString();
        if (s=="select source"||d=="select destination"||s==d||d==s) {
            Toast.makeText(this, "Select Valid Source or Destination...!!!!!!", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(this, navigation.class);
            i.putExtra("sour", s);
            i.putExtra("dest", d);
            startActivity(i);
        }
    }
}