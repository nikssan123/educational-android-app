package com.FornaxElit.MaturaBel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


//SET on exit activity ad listener to load new activity


public class ActivityAuthors extends AppCompatActivity {

    String name; //the name of the whole page -> name of author
    int scrollTo;  //pass this to Activit_Lit_Bg to know where exactly to scroll there
    TextView textViewAuthorName;
    ConstraintLayout constraintLayout;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

        adView = findViewById(R.id.bannerAdViewAuthors);
        textViewAuthorName = findViewById(R.id.textViewAuthorNameForHisWork);
        constraintLayout = findViewById(R.id.conLayout);

        hideStatusBar();

        /*
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
        */
        loadAd();


        name = getAuthorName();
        textViewAuthorName.setText(name);

        // LayoutInflater layoutInflater = LayoutInflater.from(this);
        //R.layout.authors_scroll_view_layout is the layout for Hristo Botev!!!
        // layoutInflater.inflate(R.layout.authors_scroll_view_layout, constraintLayout);



        //Make differences between authors and fill the content

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAd();
    }



    public void loadAd(){
       // AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
      //  adView.loadAd(adRequest);
        AdRequest adRequest;
        if(BuildConfig.DEBUG){
            adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        }else{
            adRequest = new AdRequest.Builder().build();
        }
        adView.loadAd(adRequest);
    }

    private String getAuthorName(){

        Intent intent = getIntent(); //get the intent to receive information about author's name
        LayoutInflater layoutInflater = LayoutInflater.from(this);  //inflate the custom layout -> each author has hiw own layout


        // REMARK!
        // make it start counting the scroll from 0 to scroll the item to the bottom of the rv, and start from 1 to scroll the item to the middle of the screen
        try {
            switch (intent.getStringExtra("name")) {
                case "Христо Ботев":
                    name = "Христо Ботев";
                    scrollTo = 1;
                    //R.layout.authors_scroll_view_layout is the layout for Hristo Botev!!!
                    layoutInflater.inflate(R.layout.authors_scroll_view_layout, constraintLayout);
                    break;
                case "Иван Вазов":
                    layoutInflater.inflate(R.layout.ivan_vazon_information_layout,constraintLayout);
                    name = "Иван Вазов";
                    scrollTo = 2;
                    break;
                case "Алеко Константинов":
                    name = "Алеко Константинов";
                    scrollTo = 3;
                    layoutInflater.inflate(R.layout.aleko_konstantinov_info_layout, constraintLayout);
                    break;
                case "Пенчо Славейков":
                    name = "Пенчо Славейков";
                    scrollTo = 4;
                    layoutInflater.inflate(R.layout.pencho_slaveikov_info_layout,constraintLayout);
                    break;
                case "Пейо Яворов":
                    name = "Пейо Яворов";
                    scrollTo = 5;
                    layoutInflater.inflate(R.layout.peyo_qvorov_info_layout, constraintLayout);
                    break;
                case "Елин Пелин":
                    name = "Елин Пелин";
                    scrollTo = 6;
                    layoutInflater.inflate(R.layout.elin_pelin_info_layout, constraintLayout);
                    break;
                case "Димчо Дебелянов":
                    name = "Димчо Дебелянов";
                    scrollTo = 7;
                    layoutInflater.inflate(R.layout.dimcho_debelqnova_info_layout, constraintLayout);
                    break;
                case "Христо Смирненски":
                    name = "Христо Смирненски";
                    scrollTo = 8;
                    layoutInflater.inflate(R.layout.hristo_smirnenski_info_layout, constraintLayout);
                    break;
                case "Гео Милев":
                    name = "Гео Милев";
                    scrollTo = 9;
                    layoutInflater.inflate(R.layout.geo_milev_info_layout, constraintLayout);
                    break;
                case "Атанас Далчев":
                    name = "Атанас Далчев";
                    scrollTo = 10;
                    layoutInflater.inflate(R.layout.atanas_dalchev_info_layout, constraintLayout);
                    break;
                case "Елисавета Багряна":
                    name = "Елисавета Багряна";
                    scrollTo = 11;
                    layoutInflater.inflate(R.layout.elisaveta_bagrqna_info_layout, constraintLayout);
                    break;
                case "Йордан Йовков":
                    name = "Йордан Йовков";
                    scrollTo = 12;
                    layoutInflater.inflate(R.layout.jordan_jovkov_info_layout, constraintLayout);
                    break;
                case "Никола Вапцаров":
                    name = "Никола Вапцаров";
                    scrollTo = 13;
                    layoutInflater.inflate(R.layout.nikola_vapcarov_info_layout, constraintLayout);
                    break;
                case "Димитър Димов":
                    name = "Димитър Димов";
                    scrollTo = 14;
                    layoutInflater.inflate(R.layout.dimityr_dimov_info_layout, constraintLayout);
                    break;
                case "Димитър Талев":
                    name = "Димитър Талев";
                    scrollTo = 14;
                    layoutInflater.inflate(R.layout.dimityr_talev_info_layout, constraintLayout);
                    break;


            }
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "No author found", Toast.LENGTH_SHORT).show();
        }

        return name;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void hideStatusBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
    }

    public void goToAuthorActivityWithAnalysis(View view){
        Intent intent = new Intent(this, Activity_Lit_Bg.class);
        intent.putExtra("activityCheck", 4);
        intent.putExtra("scrollTo", scrollTo);
        startActivity(intent);
        //Toast.makeText(this, "fine", Toast.LENGTH_SHORT).show();
    }


    public void goToHomeMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
       // finish();
    }


}
