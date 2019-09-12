package com.FornaxElit.MaturaBel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.FornaxElit.MaturaBel.MainActivity.remove_ads;

public class ActivityTestDone extends AppCompatActivity {

    TextView textViewScore, textViewReview;
    int counterRightAnswered, allQuestions, checkForTest;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        textViewScore = findViewById(R.id.textViewInfoScore);
        textViewReview = findViewById(R.id.textViewRecenziq);
        adView = findViewById(R.id.bannerAdViewQuizDone);
        loadAd();

        Intent intent = getIntent();

        counterRightAnswered = intent.getIntExtra("counterRightAnswered", 0);
        allQuestions = intent.getIntExtra("allQuestions", 0);
        checkForTest = intent.getIntExtra("checkForTest", 0);

        textViewScore.setText(counterRightAnswered + "/" + allQuestions);

        if(counterRightAnswered < 6){
            textViewReview.setText("Опа ... я пробвай пак :)");
        }else if(counterRightAnswered <11){
            textViewReview.setText("Не е зле, но пробвай да пререшиш теста!");
        }else if(counterRightAnswered < 16){
            textViewReview.setText("Добра работа! Ама може и по-добре :)");
        } else if(counterRightAnswered < 20){
            textViewReview.setText("Браво! Трябва още малко усилие обаче :)");
        }else if(counterRightAnswered == 20){
            textViewReview.setText("Отлчино! Матура-Матата");
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        loadAd();
        super.onResume();
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

    public void goToHomeMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void tryAgain(View view){
        Intent getIntent = getIntent();
        String authorName = getIntent.getStringExtra("testName");
        Intent intent = new Intent(this, ActivityQuiz.class);
        intent.putExtra("check", checkForTest);
        intent.putExtra("test", authorName);
        finish();
        startActivity(intent);
    }

    public void viewAllTests(View view){
        Intent intent;
        Intent getIntent = getIntent();
        boolean isBel = getIntent.getBooleanExtra("isBel", false);
        if(isBel){
            intent = new Intent(this, MainActivity.class);
        }else {
            intent = new Intent(this, Activity_Lit_Bg.class);
        }
        intent.putExtra("activityCheck", 1);
        intent.putExtra("check", 1);
        finish();
        startActivity(intent);
    }
}
