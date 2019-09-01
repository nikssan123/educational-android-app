package com.FornaxElit.MaturaBel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.FornaxElit.MaturaBel.MainActivity.remove_ads;

public class EmailActiviti extends AppCompatActivity {

    AdView adView;
    TextView textViewSubject, textViewMessage;
    EditText editTextSubject, editTextMessage;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_activiti);

        textViewSubject = findViewById(R.id.textViewSubject);
        textViewMessage = findViewById(R.id.textViewMessage);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextMessage = findViewById(R.id.editTextMessage);
        btnSubmit = findViewById(R.id.submit);

        adView = findViewById(R.id.bannerAdViewEmail);
        loadAd();


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAd();
    }

    public void loadAd(){
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        //adView.loadAd(adRequest);
        if(!remove_ads) {
            AdRequest adRequest;
            if (BuildConfig.DEBUG) {
                adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            } else {
                adRequest = new AdRequest.Builder().build();
            }
            adView.loadAd(adRequest);
        }
    }

    public void openEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fornax.elit@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, editTextSubject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, editTextMessage.getText().toString());

        try {
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailActiviti.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
