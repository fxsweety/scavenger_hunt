package com.teambuilding.scavenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesHelper.getInstance().Initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        if (SharedPreferencesHelper.getInstance().getCount() == 0) {
            int[] intArray = {1,2,3,4,5,6,7,8,9};

            final int random =  ThreadLocalRandom.current().nextInt(1, 9);
            SharedPreferencesHelper.getInstance().setActivityIndex(random);
            TextView textView = findViewById(R.id.start_info);
            Button start = findViewById(R.id.start_button);

            if(System.currentTimeMillis() >= 1540776600000l) {
                start.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
            } else {
                start.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferencesHelper.getInstance().setCount(SharedPreferencesHelper.getInstance().getCount()+1);
                    startActivityBasedOnIndex(random);
                }
            });
        } else {
            if (SharedPreferencesHelper.getInstance().getCount() >= 9) {
                // Winner Activity
                Intent intent =  new Intent(MainActivity.this, SuccessActivity.class);
                startActivity(intent);
                finish();

            } else {
                startActivityBasedOnIndex(SharedPreferencesHelper.getInstance().getActivityIndex());
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
                return CafeClueActivity.class;
    }

}
