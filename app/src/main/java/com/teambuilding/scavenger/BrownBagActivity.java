package com.teambuilding.scavenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BrownBagActivity extends QRCodeScannerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clueText.setText("You can use my room for presentation but you need get your lunch.. where am I ?");

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
                if (result.getContents().contains("stationery")) {
                    Toast.makeText(this, "You got it.. Good Job..Lets get to the next one", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(BrownBagActivity.this, TraceyClueActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 4000);
                } else {
                    Toast.makeText(this, "Sorry not the right one.. Try again with the right QR code", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}