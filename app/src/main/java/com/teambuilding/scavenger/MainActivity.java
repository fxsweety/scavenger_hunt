package com.teambuilding.scavenger;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (sharedPreferencesHelper.getCount() == 0) {
            int[] intArray = {1,2,3,4,5,6,7,8,9};

            final int random = new Random().nextInt(intArray.length);
            sharedPreferencesHelper.setActivityIndex(random);
            TextView textView = findViewById(R.id.start_info);
            Button start = findViewById(R.id.start_button);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreferencesHelper.setCount(sharedPreferencesHelper.getCount()+1);
                    startActivityBasedOnIndex(random);
                }
            });
        } else {
            if (sharedPreferencesHelper.getCount() > 9) {
                // Winner Activity
            } else {
                startActivityBasedOnIndex(sharedPreferencesHelper.getActivityIndex());
            }
        }
    }


    private void startActivityBasedOnIndex(int index) {
        Intent intent =  new Intent(MainActivity.this, getActivity(index));
        startActivity(intent);
        finish();
    }

    private Class getActivity(int value){
        switch(value) {
            case 1:
                return CafeClueActivity.class;
            case 2:
                return ToddClueActivity.class;
            case 3:
                return StationeryClueActivity.class;
            case 4:
                return SamiaClueActivity.class;
            case 5:
                return CimCityClueActivity.class;
            case 6:
                return RebeccaClueActivity.class;
            case 7:
                return BrownBagActivity.class;
            case 8:
                return TraceyClueActivity.class;
            case 9:
                return TechBarActivity.class;
                }
                if (value > 9 ) {
                    getActivity( value-9);
                }
                return CafeClueActivity.class;
    }

}
