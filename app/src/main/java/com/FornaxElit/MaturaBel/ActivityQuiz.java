package com.FornaxElit.MaturaBel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.FornaxElit.MaturaBel.MainActivity.deleteCache;
import static com.FornaxElit.MaturaBel.MainActivity.remove_ads;


public class ActivityQuiz extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnOption1, btnOption2, btnOption3, btnOption4, btnPrevious, btnNext;
    TextView textViewQuestion, textViewCounter, textViewTestInfo;
    String testInfo = "";
    int answerNr;
    int currentQuestion = 0;
    int counterRightAnswered = 0;
    int progressBarNumber;
    int check;
    boolean checkAnswered = false , isBel = false;
    AdView adView;
    InterstitialAd interstitialAd;
    List<Question> questionsForAll = new ArrayList<>();
    List<Question> hristoBotevQuestions;
    List<Question> ivanVazovQuestions;
    List<Question> alekoKonstantinovQuestions;
    List<Question> penchoSlaveikovQuestions;
    List<Question> peyoQvorovQuestions;
    List<Question> elinPelinQuestions;
    List<Question> dimchoDebelqnovQuestions;
    List<Question> hristoSmirnenskiQuestions;
    List<Question> geoMilevQuestions;
    List<Question> atanasDalchevQuestions;
    List<Question> elisavetaBagrqnaQuestions;
    List<Question> yordanYovkovQuestions;
    List<Question> nikolaVapcarovQuestions;
    List<Question> dimityrDimovQuestions;
    List<Question> dimityrTalevQuestions;
    List<Question> belQuestions;
    List<Question> questionsCurrent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        belQuestions = quizDbHelper.belAllQuestions();
        hristoBotevQuestions = quizDbHelper.hristoBotevetAllQuestions();
        ivanVazovQuestions = quizDbHelper.ivanVazovAllQuestions();
        alekoKonstantinovQuestions = quizDbHelper.alekoKonstantinovQuestions();
        penchoSlaveikovQuestions = quizDbHelper.penchoSlaveikovQuestions();
        peyoQvorovQuestions = quizDbHelper.peyoQvorovQuestions();
        elinPelinQuestions = quizDbHelper.elinPelinQuestions();
        dimchoDebelqnovQuestions = quizDbHelper.dimchoDebelqnovTable();
        hristoSmirnenskiQuestions = quizDbHelper.hristoSmirnenskiQuestions();
        geoMilevQuestions = quizDbHelper.geoMilevQuestions();
        atanasDalchevQuestions = quizDbHelper.atanasDalchevQuestions();
        elisavetaBagrqnaQuestions = quizDbHelper.elisavetaBagrqnaQuestions();
        yordanYovkovQuestions = quizDbHelper.yordanYovkovQuestions();
        nikolaVapcarovQuestions = quizDbHelper.nikolaVapcarovQuestions();
        dimityrDimovQuestions = quizDbHelper.dimityrDimovQuestions();
        dimityrTalevQuestions = quizDbHelper.dimityrTalevQuestions();



        progressBar = findViewById(R.id.progressBar);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewTestInfo = findViewById(R.id.textViewTestInfo);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        textViewQuestion.setMovementMethod(new ScrollingMovementMethod());
        textViewQuestion.setScrollbarFadingEnabled(false);

        Intent intent = getIntent();
        try {
            check = intent.getIntExtra("check", 16);
            testInfo = intent.getStringExtra("test");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }



        textViewTestInfo.setText(testInfo);


        adView = findViewById(R.id.bannerAdViewQuiz);
        loadAd();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5283989799923871/6813405969");
        if(!remove_ads) {
            if (BuildConfig.DEBUG) {
                interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            } else {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        }


        switch (check){
            case 0:
                isBel = true;
                questionsCurrent.clear();
                Collections.shuffle(belQuestions);
                for(int i =0; i < 20; i++){
                    questionsCurrent.add(belQuestions.get(i));
                }
                break;
            case 1:
                questionsCurrent.clear();
                questionsCurrent.addAll(hristoBotevQuestions);
                break;
            case 2:
                questionsCurrent.clear();
                questionsCurrent.addAll(ivanVazovQuestions);
                break;
            case 3:
                questionsCurrent.clear();
                questionsCurrent.addAll(alekoKonstantinovQuestions);
                break;
            case 4:
                questionsCurrent.clear();
                questionsCurrent.addAll(penchoSlaveikovQuestions);
                break;
            case 5:
                questionsCurrent.clear();
                questionsCurrent.addAll(peyoQvorovQuestions);
                break;
            case 6:
                questionsCurrent.clear();
                questionsCurrent.addAll(elinPelinQuestions);
                break;
            case 7:
                questionsCurrent.clear();
                questionsCurrent.addAll(dimchoDebelqnovQuestions);
                break;
            case 8:
                questionsCurrent.clear();
                questionsCurrent.addAll(hristoSmirnenskiQuestions);
                break;
            case 9:
                questionsCurrent.clear();
                questionsCurrent.addAll(geoMilevQuestions);
                break;
            case 10:
                questionsCurrent.clear();
                questionsCurrent.addAll(atanasDalchevQuestions);
                break;
            case 11:
                questionsCurrent.clear();
                questionsCurrent.addAll(elisavetaBagrqnaQuestions);
                break;
            case 12:
                questionsCurrent.clear();
                questionsCurrent.addAll(yordanYovkovQuestions);
                break;
            case 13:
                questionsCurrent.clear();
                questionsCurrent.addAll(nikolaVapcarovQuestions);
                break;
            case 14:
                questionsCurrent.clear();
                questionsCurrent.addAll(dimityrDimovQuestions);
                break;
            case 15:
                questionsCurrent.clear();
                questionsCurrent.addAll(dimityrTalevQuestions);
                break;
            case 16:
                questionsCurrent.clear();
                questionsForAll.addAll(hristoBotevQuestions);
                questionsForAll.addAll(ivanVazovQuestions);
                questionsForAll.addAll(alekoKonstantinovQuestions);
                questionsForAll.addAll(penchoSlaveikovQuestions);
                questionsForAll.addAll(peyoQvorovQuestions);
                questionsForAll.addAll(elinPelinQuestions);
                questionsForAll.addAll(dimchoDebelqnovQuestions);
                questionsForAll.addAll(hristoSmirnenskiQuestions);
                questionsForAll.addAll(geoMilevQuestions);
                questionsForAll.addAll(atanasDalchevQuestions);
                questionsForAll.addAll(elisavetaBagrqnaQuestions);
                questionsForAll.addAll(yordanYovkovQuestions);
                questionsForAll.addAll(nikolaVapcarovQuestions);
                questionsForAll.addAll(dimityrDimovQuestions);
                questionsForAll.addAll(dimityrTalevQuestions);
                Collections.shuffle(questionsForAll);
                for(int i =0; i < 20; i++){
                    questionsCurrent.add(questionsForAll.get(i));
                }
                break;
        }

        Collections.shuffle(questionsCurrent);


        textViewCounter.setText((currentQuestion + 1) + "/" + questionsCurrent.size());

        progressBarNumber = 0;

        progressBar.setMax(questionsCurrent.size());
        progressBar.incrementProgressBy(0);
        //progressBar.setProgress(progressBarNumber);

        newQuestionLoad();


        //Clear cache when opening activity
        deleteCache(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAd();
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.small_owl_pic_transperent)
                .setTitle("Сигурни ли сте?")
                .setMessage("Не сте довършили теста! Сигурни ли сте, че искате да се върнете?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Не", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

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

    public void newQuestionLoad(){
        Question question;

        question = questionsCurrent.get(currentQuestion);

        textViewQuestion.setText(question.getQuestion());
        btnOption1.setText(question.getOption1());
        btnOption2.setText(question.getOption2());
        btnOption3.setText(question.getOption3());
        btnOption4.setText(question.getOption4());

        answerNr = question.getAnswerNr();

        currentQuestion++;
    }

    public void answer(View view){
        btnOption1.setClickable(false);
        btnOption2.setClickable(false);
        btnOption3.setClickable(false);
        btnOption4.setClickable(false);

        checkAnswered = true;

        int tagBtn1 = Integer.parseInt(btnOption1.getTag().toString());
        int tagBtn2 = Integer.parseInt(btnOption2.getTag().toString());
        int tagBtn3 = Integer.parseInt(btnOption3.getTag().toString());

        if(answerNr == Integer.parseInt(view.getTag().toString())){
            view.setBackground(ContextCompat.getDrawable(this ,R.drawable.custom_button_quiz_answered));
            counterRightAnswered++;
        }else {
            view.setBackground(ContextCompat.getDrawable(this ,R.drawable.custom_button_quiz_answered_wrong));
            //vibrate();

            if(answerNr == tagBtn1){
                btnOption1.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_button_quiz_answered));
            }else if(answerNr == tagBtn2){
                btnOption2.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_button_quiz_answered));
            }else if(answerNr == tagBtn3){
                btnOption3.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_button_quiz_answered));
            }else{
                btnOption4.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_button_quiz_answered));
            }
        }
    }

    public void nextQuestion(View view){
        //bellow line is for test only, remove if app crashes
        //dont delete, it works lel

        //uncomment to set text to something
        //i think it actually works
        Button button = (Button) view;

        textViewQuestion.scrollTo(0,0);
        if(checkAnswered) {
            if (currentQuestion < questionsCurrent.size()) {
                if(currentQuestion == questionsCurrent.size() - 1){
                    button.setText("Виж резултата");
                }
                loadNewQuestion();
                textViewCounter.setText(currentQuestion + "/" + questionsCurrent.size());

            } else {
                final Intent intent = new Intent(this, ActivityTestDone.class);
                intent.putExtra("counterRightAnswered", counterRightAnswered);
                intent.putExtra("allQuestions", questionsCurrent.size());
                intent.putExtra("checkForTest", check);
                intent.putExtra("testName", textViewTestInfo.getText().toString());
                intent.putExtra("isBel", isBel);
                finish();
                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }else{
                    startActivity(intent);
                }

                interstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        startActivity(intent);
                        if(!remove_ads) {
                            if (BuildConfig.DEBUG) {
                                interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                            } else {
                                interstitialAd.loadAd(new AdRequest.Builder().build());
                            }
                        }
                    }
                });

            }
            checkAnswered = false;
        }else{
            Toast.makeText(this, "Не сте избрали отговор!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadNewQuestion(){
        btnOption1.setClickable(true);
        btnOption2.setClickable(true);
        btnOption3.setClickable(true);
        btnOption4.setClickable(true);

        newQuestionLoad();


        btnOption1.setBackground(ContextCompat.getDrawable(this,R.drawable.button_main));
        btnOption2.setBackground(ContextCompat.getDrawable(this,R.drawable.button_main));
        btnOption3.setBackground(ContextCompat.getDrawable(this,R.drawable.button_main));
        btnOption4.setBackground(ContextCompat.getDrawable(this,R.drawable.button_main));


        //progressBarNumber++;
        progressBar.incrementProgressBy(1);

    }

    public void goToHomeMenu(View view){
        final Intent intent = new Intent(this, MainActivity.class);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.small_owl_pic_transperent)
                .setTitle("Сигурни ли сте?")
                .setMessage("Не сте довършили теста! Сигурни ли сте, че искате да се върнете?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Не", null)
                .show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        //finish();
        //startActivity(intent);

    }

    /*public void vibrate(){
        try{
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }}catch (Exception e){
            e.printStackTrace();
        }

    }*/
}
