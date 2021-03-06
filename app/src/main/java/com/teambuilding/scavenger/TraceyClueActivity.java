package com.teambuilding.scavenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class TraceyClueActivity extends QRCodeScannerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clueText.setText("This manager has been at comcast for 14 years. Ask them about your wifi devices; they are all ears!");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if(result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                if (result.getContents().contains("tracey")) {
                    if (SharedPreferencesHelper.getInstance().getCount() < 9) {
                        SharedPreferencesHelper.getInstance().setCount(SharedPreferencesHelper.getInstance().getCount() + 1);
                        Log.d("Sharedpref Count", String.valueOf(SharedPreferencesHelper.getInstance().getCount()));
                        SharedPreferencesHelper.getInstance().setActivityIndex(9);
                        Toast.makeText(this, "You got it.. Good Job..Lets get to the next one", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(TraceyClueActivity.this, TechBarActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }, 4000);
                    } else {
                        //Success Activity
                        Intent i = new Intent(TraceyClueActivity.this, SuccessActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(this, "Sorry not the right one.. Try again with the right QR code", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
