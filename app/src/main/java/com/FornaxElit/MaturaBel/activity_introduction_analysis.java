package com.FornaxElit.MaturaBel;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.FornaxElit.MaturaBel.ui.main.CustomSelectionsPagerAdapter;

import com.FornaxElit.MaturaBel.ui.main.SectionsPagerAdapter;

import java.util.Random;

public class activity_introduction_analysis extends AppCompatActivity {

    InterstitialAd interstitialAd;
    Random random;
    int randInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_analysis);

        AppBarLayout appBarLayout = findViewById(R.id.appBar);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5283989799923871/9247997615");
        if(BuildConfig.DEBUG) {
            interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        }else {
            interstitialAd.loadAd(new AdRequest.Builder().build());
        }
        //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        Intent intent = getIntent();
        int check = intent.getIntExtra("check", 0);

        if(check < 3) {

            String headerIntro = null;
            String headerAnalysis = null;
            int textIntro = 0;
            int textAnalysis = 0;
            //String authorNameIntro = null;
            int n = 0;

            try {
                headerIntro = intent.getStringExtra("headerIntro");
                headerAnalysis = intent.getStringExtra("headerAnalysis");
                textIntro = intent.getIntExtra("textIntro" , 0);
                textAnalysis = intent.getIntExtra("textAnalysis", 0);

                //authorNameIntro = intent.getStringExtra("authorNameIntro");


                n = intent.getIntExtra("tab", 0);
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), headerIntro, textIntro, headerAnalysis, textAnalysis);
            viewPager.setAdapter(sectionsPagerAdapter);
            viewPager.setCurrentItem(n);
        }else if(check > 3){

            String textGrammer = null;
            String textPunct = null;
            String textSpell = null;

            Intent intent2 = getIntent();

            //either get the text from the intent as extras or set it here to be quicker using R.string.something
            //change everything to ask for ints -> constructors than fragments etc.

            try {


                textGrammer = intent2.getStringExtra("textGrammer");
                textPunct = intent2.getStringExtra("textPunct");
                textSpell = intent2.getStringExtra("textSpell");


                CustomSelectionsPagerAdapter custom = new CustomSelectionsPagerAdapter(this, getSupportFragmentManager());//, textGrammer, textPunct, textSpell);
                viewPager.setAdapter(custom);
            }catch (Exception e){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
        //random = new Random();
        //randInt = random.nextInt(3);



        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //25% chance to load add - bound - 5 - make it 20%

        random = new Random();
        randInt = random.nextInt(3);
        //Toast.makeText(this, Integer.toString(randInt), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        boolean isAdLoaded = intent.getBooleanExtra("isAdLoaded", true);
        if(isAdLoaded) {
            if (randInt == 1) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    super.onBackPressed();
                    //Toast.makeText(this, "Включете си интернета :)", Toast.LENGTH_SHORT).show();
                }

                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        finish();
                        if(BuildConfig.DEBUG) {
                            interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                        }else {
                            interstitialAd.loadAd(new AdRequest.Builder().build());
                        }
                       // interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                    }
                });
            } else {
                super.onBackPressed();
            }
        }else{
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                super.onBackPressed();
                //Toast.makeText(this, "Включете си интернета :)", Toast.LENGTH_SHORT).show();
            }

            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                    if(BuildConfig.DEBUG) {
                        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                    }else {
                        interstitialAd.loadAd(new AdRequest.Builder().build());
                    }
                   // interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }
            });
        }
    }


    //getTabAt
}