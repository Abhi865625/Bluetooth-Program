package com.example.blutoothprogram;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btntOn = findViewById(R.id.btnOn);
        Button btntOff = findViewById(R.id.btnOFF);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

        btntOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bAdapter == null) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
                } else {
                    if (!bAdapter.isEnabled()) {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            // Request permissions if not granted
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 1);
                            return;
                        }
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
                    }
                }
            }
        });

        btntOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // Request permissions if not granted
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 1);
                    return;
                }
                bAdapter.disable();
            }
        });
    }
}
