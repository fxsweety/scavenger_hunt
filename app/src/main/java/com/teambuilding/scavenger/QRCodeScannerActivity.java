package com.teambuilding.scavenger;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class QRCodeScannerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CAMERA_PERMISSION = 20;
    private IntentIntegrator qrScan;
    protected Button scan;
    protected TextView clueText;
    SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_scanner);

        scan = findViewById(R.id.scan);
        clueText = findViewById(R.id.clue_text);

        findViewById(R.id.cameraView).setVisibility(View.GONE);
        qrScan = new IntentIntegrator(this);
        scan.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    qrScan.initiateScan();

//                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(QRCodeScannerActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        ActivityCompat.requestPermissions(QRCodeScannerActivity.this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}


