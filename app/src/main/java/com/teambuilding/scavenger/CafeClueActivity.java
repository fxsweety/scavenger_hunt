package com.teambuilding.scavenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CafeClueActivity extends QRCodeScannerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clueText.setText("I want to get a coffee but I'm overlooking the uptown beer garden");

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
                if (result.getContents().contains("cafe")) {
                    if (sharedPreferencesHelper.getCount() < 9) {
                        sharedPreferencesHelper.setCount(sharedPreferencesHelper.getCount() + 1);
                        Log.d("Sharedpref Count", String.valueOf(sharedPreferencesHelper.getCount()));
                        Toast.makeText(this, "You got it.. Good Job..Lets get to the next one", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(CafeClueActivity.this, ToddClueActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }, 4000);
                    } else {
                        //Success Activity
                    }
                } else {
                    Toast.makeText(this, "Sorry not the right one.. Try again with the right QR code", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
