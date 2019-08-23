package com.FornaxElit.MaturaBel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import static com.FornaxElit.MaturaBel.MainActivity.showSnackbar;


//SET on exit activity ad listener to load new activity


public class Activity_Lit_Bg extends AppCompatActivity {

    List<itemForRv> myList = new ArrayList<>();
    List<ItemForRvForAuthorWor> itemList = new ArrayList<>();
    ConstraintLayout constraintLayout;
    private AdView adView;
    SearchView searchView;
    RecyclerView recyclerView;
    String authorName;
    String authorWork;
    Adapter adapter;
    boolean isActiveTestForAll = false, isAdLoaded = true;
    boolean isNetworkAvailable;//, isAlertDialogSeen;
    static boolean isAlertDialogSeen = false;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__lit__bg);

        constraintLayout = findViewById(R.id.constrainLayoutLitBg);
        adView = findViewById(R.id.bannerAdViewAuthorList);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.rvListActivity2);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5283989799923871/4378814315");
        if(BuildConfig.DEBUG) {
            interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        }else {
            interstitialAd.loadAd(new AdRequest.Builder().build());
        }
       // interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());


        isNetworkAvailable = isNetworkAvailable();

        if(!isNetworkAvailable){
            showSnackbar(constraintLayout);
        }

        //isAlertDialogSeen = false;
        hideStatusBar();

        Intent intentCheck = getIntent();
        int check = intentCheck.getIntExtra("check", 0);
        int activityCheck = intentCheck.getIntExtra("activityCheck", 0);


        if(activityCheck < 3) {
            loadAd();
            searchView.setEnabled(true);

            if (check == 1) {
                check = 0;
                //display the alert dialog only once
                if(!isAlertDialogSeen) {
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.small_owl_pic_transperent)
                            .setTitle("Информация")
                            .setMessage("Изберете автор за тест!")
                            .setPositiveButton("Разбрах", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    isAlertDialogSeen = true;
                                }
                            })
                            .setNegativeButton("Върнете се", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //isAlertDialogSeen = true;
                                    finish();
                                }
                            })
                            .show();
                }

                createListItems();

                adapter = new Adapter(this, myList, 1);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));


                //check if it is the test or material page and add or remove the test for all
                if(!isActiveTestForAll) {
                    myList.add(0, new itemForRv("Сборен тест", R.drawable.owl_full_transperent));
                    adapter.notifyDataSetChanged();
                    isActiveTestForAll = true;
                }


                 searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                       foundTest(s);
                        searchView.clearFocus();

                        return true;
                    }
                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                 });


            }else{
                createListItems();
                Adapter adapter = new Adapter(this, myList, 3);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                //remove if its not the page with tests
                if(isActiveTestForAll){
                    myList.remove(0);
                    adapter.notifyDataSetChanged();
                    isActiveTestForAll = false;
                }
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        //Toast.makeText(Activity_Lit_Bg.this, searchView.getQuery(), Toast.LENGTH_SHORT).show();
                        hideStatusBar();
                        authorFound(searchView.getQuery().toString());
                        searchView.clearFocus();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        hideStatusBar();
                        return true;
                    }
                });


            }

            searchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideStatusBar();
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    hideStatusBar();
                    return true;
                }
            });

        }else if(activityCheck > 3){
            loadAd();

            CustomAdapter customAdapter = new CustomAdapter(this, itemList);
            recyclerView.setAdapter(customAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            Intent intent = getIntent();
            final int scrollTo = intent.getIntExtra("scrollTo", 0);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollToPosition(scrollTo);

                }
            }, 200);


            String[] hrist_botev_work = {"\"Майце си\"", "\"Към брата си\"", "\"Елегия\"", "\"Борба\"", "\"До моето първо либе\"", "\"На прощаване\"", "\"Хаджи Димитър\"", "\"Моята Молитва\"", "\"Обесването на Васил Левски\""};
            String[] ivan_vazov_work = {"\"Левски\"", "\"Кочо\"", "\"Паисий\"", "\"Опълченците на шипка\"", "\"Отечество любезно, как хубаво си ти\"", "\"Българският език\"", "\"Линее нашто поколенье\"", "\"При Рилския манастир\"", "\"Елате ни вижте\"", "\"Дядо Йоцо гледа\"", "\"Чичовци\"", "\"Под игото\""};
            String[] aleko_konstantinov_work = {"\"Разни хора, разни идеали\"", "\"Бай Ганьо\""};
            String[] pencho_sleveikov_work = {"\"Cis moll\"", "\"Ни лъх не дъхва над полени\"", "\"Спи езерото\"", "\"Самотен гроб в самотен кът\"", "\"Ралица\""};
            String[] peyo_qvorov_work = {"\"Градушка\"", "\"Заточеници\"", "\"Ще бъдеш в бяло\"", "\"Две хубави очи\"", "\"Стон\"", "\"Две души\"", "\"Сенки\"", "\"Песента на човека\"", "\"Маска\""};
            String[] elin_pelin_work = {"\"Ветрената мелница\"", "\"На оня свят\"", "\"Задушница\"", "\"Косачи\"", "\"Андрешко\"", "\"Мечтатели\"", "\"Занемелите камбани\"", "\"Чорба от греховете на отец Никодим\"", "\"Гераците\""};
            String[] dimcho_debelqnov_work = {"\"Черна песен\"", "\"Пловдив\"", "\"Да се завърнеш в бащината къща\"", "\"Помниш ли, помниш ли\"", "\"Спи градът\"", "\"Миг\"", "\"Един убит\"", "\"Сиротна песен\"", "\"Тиха победа\""};
            String[] hristo_smirnenski_work = {"\"Да бъде ден!\"", "\"Ний\"", "\"Йохан\"", "\"Юноша\"", "\"Стария музикант\"", "\"Цветарка\"", "\"Зимни вечери\""};
            String[] geo_milev_work = {"\"Септември\""};
            String[] atanas_dalchev_work = {"\"Прозорец\"", "\"Болница\"", "\"Стаята\"", "\"Къщата\"", "\"Повест\"", "\"Книгите\"", "\"Камък\"", "\"Дяволско\""};
            String[] elisaveta_bagrqna_work = {"\"Кукувица\"", "\"Стихии\"", "\"Потомка\"", "\"Вечната\""};
            String[] jordan_jovkov_work = {"\"Песента на колелетата\"", "\"Последна радост\"", "\"Шибил\"", "\"През чумавото\"", "\"Индже\"", "\"Албена\"", "\"Другоселец\"", "\"Серафим\""};
            String[] nikola_vapcarov_work = {"\"Вяра\"", "\"Писмо\"", "\"Песен за човека\"", "\"Сън\"", "\"История\"", "\"Завод\"", "\"Кино\"", "\"Прощално\"", "\"Борбата е безмилостно жестока\"", };
            String[] dimity_dimov_work = {"\"Тютюн\""};
            String[] dimity_talev_work = {"\"Железният светилник\""};


            itemList.add(new ItemForRvForAuthorWor(hrist_botev_work, R.drawable.hristo_botev, "Христо Ботев"));
            itemList.add(new ItemForRvForAuthorWor(ivan_vazov_work, R.drawable.ivan_vazon, "Иван Вазов"));
            itemList.add(new ItemForRvForAuthorWor(aleko_konstantinov_work, R.drawable.aleko, "Алеко\n Константинов"));
            itemList.add(new ItemForRvForAuthorWor(pencho_sleveikov_work, R.drawable.pencho_slaveikov, "Пенчо Славейков"));
            itemList.add(new ItemForRvForAuthorWor(peyo_qvorov_work, R.drawable.peyo_qvorov, "Пейо Яворов"));
            itemList.add(new ItemForRvForAuthorWor(elin_pelin_work, R.drawable.elin_pelin, "Елин Пелин"));
            itemList.add(new ItemForRvForAuthorWor(dimcho_debelqnov_work, R.drawable.dimcho_debelqnov, "Димчо Дебелянов"));
            itemList.add(new ItemForRvForAuthorWor(hristo_smirnenski_work, R.drawable.hristo_smirneski, "Христо Смирненски"));
            itemList.add(new ItemForRvForAuthorWor(geo_milev_work, R.drawable.geo_milev, "Гео Милев"));
            itemList.add(new ItemForRvForAuthorWor(atanas_dalchev_work, R.drawable.atanas_dalchev, "Атанас Далчев"));
            itemList.add(new ItemForRvForAuthorWor(elisaveta_bagrqna_work, R.drawable.elisaveta_bagrqna, "Елисавета Багряна"));
            itemList.add(new ItemForRvForAuthorWor(jordan_jovkov_work, R.drawable.jordan_jovkov, "Йордан Йовков"));
            itemList.add(new ItemForRvForAuthorWor(nikola_vapcarov_work, R.drawable.nikola_vapcarov, "Никола Вапцаров"));
            itemList.add(new ItemForRvForAuthorWor(dimity_dimov_work, R.drawable.dimatyr_dimov, "Димитър Димов"));
            itemList.add(new ItemForRvForAuthorWor(dimity_talev_work, R.drawable.dimityr_talev, "Димитър Талев"));


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    final int position = findPosition(s.toLowerCase());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.scrollToPosition(position);

                        }
                    }, 200);
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

    }

    @Override
    protected void onResume() {
        hideStatusBar();
        loadAd();
        super.onResume();
    }

    public boolean isNetworkAvailable() {
        //if(Build.VERSION.SDK_INT >= 19) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        /*}else {
            return true;
        }*/
    }

    public void createListItems(){

        //you can add one extra item on top to create test for everyone
        //myList.add(new itemForRv("Сборен тест", R.drawable.owl_pic_transperent));
        myList.add(new itemForRv("Христо Ботев", R.drawable.hristo_botev));
        myList.add(new itemForRv("Иван Вазов", R.drawable.ivan_vazon));
        myList.add(new itemForRv("Алеко\n Константинов", R.drawable.aleko));
        myList.add(new itemForRv("Пенчо Славейков", R.drawable.pencho_slaveikov));
        myList.add(new itemForRv("Пейо Яворов", R.drawable.peyo_qvorov));
        myList.add(new itemForRv("Елин Пелин", R.drawable.elin_pelin));
        myList.add(new itemForRv("Димчо Дебелянов", R.drawable.dimcho_debelqnov));
        myList.add(new itemForRv("Христо Смирненски", R.drawable.hristo_smirneski));
        myList.add(new itemForRv("Гео Милев", R.drawable.geo_milev));
        myList.add(new itemForRv("Атанас Далчев", R.drawable.atanas_dalchev));
        myList.add(new itemForRv("Елисавета Багряна", R.drawable.elisaveta_bagrqna));
        myList.add(new itemForRv("Йордан Йовков",R.drawable.jordan_jovkov));
        myList.add(new itemForRv("Никола Вапцаров", R.drawable.nikola_vapcarov));
        myList.add(new itemForRv("Димитър Димов", R.drawable.dimatyr_dimov));
        myList.add(new itemForRv("Димитър Талев", R.drawable.dimityr_talev));
    }

    public int findPosition(String name){
        int position = 0;
        switch (name){
            case "христо ботев":
            case "ботев":
            case "botev":
            case "hristo botev":
                position = 0;
                break;
            case "иван вазов":
            case "вазов":
            case "ivan vazov":
            case "vazov":
                position = 1;
                break;
            case "алеко константинов":
            case "aleko konstantinov":
            case "алеко":
            case "aleko":
                position = 2;
                break;
            case "пенчо славейков":
            case "славейков":
            case "pencho slaveikov":
            case "slaveikov":
                position = 3;
                break;
            case "пейо яворов":
            case "яворов":
            case "peyo qvorov":
            case "pejo qvoror":
            case "qvorov":
                position = 4;
                break;
            case "елин пелин":
            case "elin pelin":
                position = 5;
                break;
            case "димчо дебелянов":
            case "димчо":
            case "дебелянов":
            case "dimcho debelqnov":
            case "dimcho":
            case "debelqnov":
                position = 6;
                break;
            case "христо смирненски":
            case "смирненски":
            case "hristo smirnenski":
            case "smirnenski":
                position = 7;
                break;
            case "гео милев":
            case "geo milev":
            case "geo":
            case "гео":
            case "milev":
            case "милев":
                position = 8;
                break;
            case "атанас далчев":
            case "далчев":
            case "atanas dalchev":
            case "dalchev":
                position = 9;
                break;
            case "елисавета багряна":
            case "елисавета":
            case "багряна":
            case "elisaveta bagrqna":
            case "elisaveta":
            case "bagrqna":
                position = 10;
                break;
            case "йордан йовков":
            case "йовков":
            case "jordan jovkov":
            case "yordan yovkov":
            case "yovkov":
            case "jovkov":
                position = 11;
                break;
            case "никола вапцаров":
            case "вапцаров":
            case "nikola vapcarov":
            case "vapcarov":
                position = 12;
                break;
            case "димитър димов":
            case "димов":
            case "dimityr dimov":
            case "dimov":
                position = 13;
                break;
            case "димитър талев":
            case "талев":
            case "dimityr talev":
            case "talev":
                position = 14;
                break;
            default:
                Toast.makeText(this, "Невалидно име", Toast.LENGTH_SHORT).show();
        }

        return position;
    }


    public void authorFound(String authorName){

        Intent intent = new Intent(this, ActivityAuthors.class);

        switch (authorName.toLowerCase()){
            case "христо ботев":
            case "ботев":
            case "hristo botev":
            case "botev":
                intent.putExtra("name", "Христо Ботев");
                startActivity(intent);
                //Toast.makeText(this, "Hristo Botew", Toast.LENGTH_SHORT).show();
                break;
            case "иван вазов":
            case "вазов":
            case "ivan vazov":
            case "vazov":
                intent.putExtra("name", "Иван Вазов");
                startActivity(intent);
                //Toast.makeText(this, "Ivan Vazov", Toast.LENGTH_SHORT).show();
                break;
            case "алеко\n константинов":
            case "алеко константинов":
            case "aleko konstantinov":
            case "алеко":
            case "aleko":
                intent.putExtra("name", "Алеко Константинов");
                startActivity(intent);
                //Toast.makeText(this, "Aleko", Toast.LENGTH_SHORT).show();
                break;
            case "пенчо славейков":
            case "славейков":
            case "pencho slaveikov":
            case "slaveikov":
                intent.putExtra("name", "Пенчо Славейков");
                startActivity(intent);
                break;
            case "пейо яворов":
            case "яворов":
            case "peyo qvorov":
            case "pejo qvoror":
            case "qvorov":
                intent.putExtra("name", "Пейо Яворов");
                startActivity(intent);
                break;
            case "елин пелин":
            case "elin pelin":
                intent.putExtra("name", "Елин Пелин");
                startActivity(intent);
                //Toast.makeText(this, "Elin Pelin", Toast.LENGTH_SHORT).show();
                break;
            case "димчо дебелянов":
            case "димчо":
            case "dimcho":
            case "дебелянов":
            case "debelqnov":
            case "dimcho debelqnov":
                intent.putExtra("name", "Димчо Дебелянов");
                startActivity(intent);
                break;
            case "христо смирненски":
            case "смирненски":
            case "hristo smirnenski":
            case "smirnenski":
                intent.putExtra("name", "Христо Смирненски");
                startActivity(intent);
                //Toast.makeText(this, "Hristo Smirneski", Toast.LENGTH_SHORT).show();
                break;
            case "гео милев":
            case "geo milev":
            case "geo":
            case "гео":
            case "milev":
            case "милев":
                intent.putExtra("name", "Гео Милев");
                startActivity(intent);
                break;
            case "атанас далчев":
            case "далчев":
            case "atanas dalchev":
            case "dalchev":
                intent.putExtra("name", "Атанас Далчев");
                startActivity(intent);
                break;
            case "елисавета багряна":
            case "елисавета":
            case "багряна":
            case "elisaveta bagrqna":
            case "elisaveta":
            case "bagrqna":
                intent.putExtra("name", "Елисавета Багряна");
                startActivity(intent);
                break;
            case "йордан йовков":
            case "йовков":
            case "jordan jovkov":
            case "yordan yovkov":
            case "jovkov":
            case "yovkov":
                intent.putExtra("name", "Йордан Йовков");
                startActivity(intent);
                break;
            case "никола вапцаров":
            case "вапцаров":
            case "nikola vapcarov":
            case "vapcarov":
                intent.putExtra("name", "Никола Вапцаров");
                startActivity(intent);
                break;
            case "димитър димов":
            case "димов":
            case "dimityr dimov":
            case "dimov":
                intent.putExtra("name", "Димитър Димов");
                startActivity(intent);
                break;
            case "димитър талев":
            case "талев":
            case "dimityr talev":
            case "talev":
                intent.putExtra("name", "Димитър Талев");
                startActivity(intent);
                break;

            default:
                Toast.makeText(this, "Невалидно име", Toast.LENGTH_SHORT).show();
        }


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

    public void loadAd(){
       // AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        //adView.loadAd(adRequest);
        AdRequest adRequest;
        if(BuildConfig.DEBUG){
            adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        }else{
            adRequest = new AdRequest.Builder().build();
        }
        adView.loadAd(adRequest);
    }


    public void viewContent(View view){

        //Toast.makeText(this, view.getTag().toString(), Toast.LENGTH_SHORT).show();
        Button button = (Button) view;
        String check = button.getText().toString();
        String authorName = view.getTag().toString().toLowerCase();
        if(check.equals("Материали")) {
            authorFound(authorName);
        }else{
            foundTest(authorName);
        }

    }

    public void introduction(View view){
        authorName = view.getTag().toString();
        String[] split = authorName.split("/");

        final Intent intent = new Intent(this, activity_introduction_analysis.class);
        intent.putExtra("check", 1);

        int textIntro = findAuthorNameForIntroduction(split[0]);
        int textAnalysis = getAnalysis(split[1]);
        //Toast.makeText(this, textIntro, Toast.LENGTH_SHORT).show();

        intent.putExtra("headerIntro", split[0]);
        intent.putExtra("textIntro", textIntro);
        intent.putExtra("headerAnalysis", split[1]);
        intent.putExtra("textAnalysis", textAnalysis);

        //startActivity(intent);

        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }else {
            if(!isNetworkAvailable){
                Toast.makeText(this, "Моля, включете си интернета :)", Toast.LENGTH_SHORT).show();
            }
            isAdLoaded = false;
            intent.putExtra("isAdLoaded", isAdLoaded);
            startActivity(intent);
        }

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                isAdLoaded = true;
                intent.putExtra("isAdLoaded", isAdLoaded);
                startActivity(intent);
                if(BuildConfig.DEBUG) {
                    interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }else {
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
              //  interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });

    }

    public void chooseWork(View view){
        authorWork = view.getTag().toString();
        String[] split = authorWork.split("/");

       final Intent intent = new Intent(this, activity_introduction_analysis.class);
       intent.putExtra("check", 2);
       //intent.putExtra("authorNameIntro", split[0]);
        int textIntro = findAuthorNameForIntroduction(split[0]);
        int textAnalysis = getAnalysis(split[1]);
       // Toast.makeText(this, split[1], Toast.LENGTH_SHORT).show();

       intent.putExtra("headerIntro", split[0]);
       intent.putExtra("textIntro", textIntro);
       intent.putExtra("headerAnalysis", split[1]);
       intent.putExtra("textAnalysis", textAnalysis);
       intent.putExtra("tab", 1);

        //startActivity(intent);

        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }else {
            if(!isNetworkAvailable){
                Toast.makeText(this, "Моля, включете си интернета :)", Toast.LENGTH_SHORT).show();
            }
            isAdLoaded = false;
            intent.putExtra("isAdLoaded", isAdLoaded);
            startActivity(intent);
        }

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                isAdLoaded = true;
                intent.putExtra("isAdLoaded", isAdLoaded);
                startActivity(intent);
                if(BuildConfig.DEBUG) {
                    interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }else {
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
                //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });

    }

    private int findAuthorNameForIntroduction(String authorName){
        int introduction = 0;


        switch (authorName) {
            case "Христо Ботев":
                introduction = R.string.introduction_hristo_botev;
                break;
            case "Иван Вазов":
                introduction = R.string.introduction_ivan_vazov;
                break;
            case "Алеко\n Константинов":
                introduction = R.string.introduction_aleko_konstantinov;
                break;
            case "Пенчо Славейков":
                introduction = R.string.introduction_pencho_slaveikov;
                break;
            case "Пейо Яворов":
                introduction = R.string.introduction_peyo_qvorov;
                break;
            case "Елин Пелин":
                introduction = R.string.introduction_elin_pelin;
                break;
            case "Димчо Дебелянов":
                introduction = R.string.introduction_dimcho_debelqnov;
                break;
            case "Христо Смирненски":
                introduction = R.string.introduction_hristo_smirnenski;
                break;
            case "Гео Милев":
                introduction = R.string.introduction_geo_milev;
                break;
            case "Атанас Далчев":
                introduction = R.string.introduction_atanas_dalchev;
                break;
            case "Елисавета Багряна":
                introduction = R.string.introduction_elisaveta_bagrqna;
                break;
            case "Йордан Йовков":
                introduction = R.string.introduction_jordan_jovkov;
                break;
            case "Никола Вапцаров":
                introduction = R.string.introduction_nikola_vapcarov;
                break;
            case "Димитър Димов":
                introduction = R.string.introduction_dimityr_dimov;
                break;
            case "Димитър Талев":
                introduction = R.string.introduction_dimityr_talev;
                break;


        }

        return introduction;
    }

    public int getAnalysis(String analysis){
        int textAnalysis = 0;

        switch (analysis){
            //3 more to go
            //chichovci, kocho, septemvri
            case "\"Майце си\"":
                textAnalysis = R.string.analysis_maice_si;
                break;
            case "\"Към брата си\"":
                textAnalysis = R.string.analysis_kym_brata_si;
                break;
            case "\"Елегия\"":
                textAnalysis = R.string.analysis_elegiq;
                break;
            case "\"Борба\"":
                textAnalysis = R.string.analysis_borba;
                break;
            case "\"До моето първо либе\"":
                textAnalysis = R.string.analysis_do_moeto_pyrvo_libe;
                break;
            case "\"На прощаване\"":
                textAnalysis = R.string.analysis_na_proshtavane;
                break;
            case "\"Хаджи Димитър\"":
                textAnalysis = R.string.analysis_hadji_dimityr;
                break;
            case "\"Моята Молитва\"":
                textAnalysis = R.string.analysis_moqta_molitva;
                break;
            case "\"Обесването на Васил Левски\"":
                textAnalysis = R.string.analysis_obesvaneto_na_vasil_levski;
                break;
            case "\"Левски\"":
                textAnalysis = R.string.analysis_levski;
                break;
            case "\"Кочо\"":
                textAnalysis = R.string.analysis_kocho;
                break;
            case "\"Паисий\"":
                textAnalysis = R.string.analysis_paisii;
                break;
            case "\"Опълченците на шипка\"":
                textAnalysis = R.string.analysis_shipka;
                break;
            case "\"Отечество любезно, как хубаво си ти\"":
                textAnalysis = R.string.analysis_otechestvo_lubezno;
                break;
            case "\"Българският език\"":
                textAnalysis = R.string.analysis_bylgarskiqt_ezik;
                break;
            case "\"Линее нашто поколенье\"":
                textAnalysis = R.string.analysis_linee_nashto_pokolenie;
                break;
            case "\"При Рилския манастир\"":
                textAnalysis = R.string.analysis_pri_rilskiq_manastir;
                break;
            case "\"Елате ни вижте\"":
                textAnalysis = R.string.analysis_elate_ni_vijte;
                break;
            case "\"Дядо Йоцо гледа\"":
                textAnalysis = R.string.analysis_dqdo_joco_gleda;
                break;
            case "\"Чичовци\"":
                textAnalysis = R.string.analysis_chichovci;
                break;
            case "\"Под игото\"":
                textAnalysis = R.string.analysis_pod_igoto;
                break;
            case "\"Разни хора, разни идеали\"":
                textAnalysis = R.string.analysis_razni_hora_razni_ideali;
                break;
            case "\"Бай Ганьо\"":
                textAnalysis = R.string.analysis_bay_ganyo;
                break;
            case "\"Cis moll\"":
                textAnalysis = R.string.analysis_cis_moll;
                break;
            case "\"Ни лъх не дъхва над полени\"":
                textAnalysis = R.string.analysis_ni_luh_ne_dyhva_nad_poleni;
                break;
            case "\"Спи езерото\"":
                textAnalysis = R.string.analysis_spi_ezeroto;
                break;
            case "\"Самотен гроб в самотен кът\"":
                textAnalysis = R.string.analysis_samoten_grob_v_samoten_kyt;
                break;
            case "\"Ралица\"":
                textAnalysis = R.string.analysis_ralica;
                break;
            case "\"Градушка\"":
                textAnalysis = R.string.analysis_gradushka;
                break;
            case "\"Заточеници\"":
                textAnalysis = R.string.analysis_zatochenici;
                break;
            case "\"Ще бъдеш в бяло\"":
                textAnalysis = R.string.analysis_shte_bydesh_v_bqlo;
                break;
            case "\"Две хубави очи\"":
                textAnalysis = R.string.analysis_dve_hubavi_ochi;
                break;
            case "\"Стон\"":
                textAnalysis = R.string.analysis_ston;
                break;
            case "\"Две души\"":
                textAnalysis = R.string.analysis_dve_dushi;
                break;
            case "\"Сенки\"":
                textAnalysis = R.string.analysis_senki;
                break;
            case "\"Песента на човека\"":
                textAnalysis = R.string.analysis_pesenta_na_choveka;
                break;
            case "\"Маска\"":
                textAnalysis = R.string.analysis_maska;
                break;
            case "\"Ветрената мелница\"":
                textAnalysis = R.string.analysis_vetrenata_melnica;
                break;
            case "\"На оня свят\"":
                textAnalysis = R.string.analysis_na_onq_svqt;
                break;
            case "\"Задушница\"":
                textAnalysis = R.string.analysis_zadushnica;
                break;
            case "\"Косачи\"":
                textAnalysis = R.string.analysis_kosachi;
                break;
            case "\"Андрешко\"":
                textAnalysis = R.string.analysis_andreshko;
                break;
            case "\"Мечтатели\"":
                textAnalysis =  R.string.analysis_mechtateli;
                break;
            case "\"Занемелите камбани\"":
                textAnalysis = R.string.analysis_zanemelite_kambani;
                break;
            case "\"Чорба от греховете на отец Никодим\"":
                textAnalysis = R.string.analysis_chorba_ot_grehovete_na_otec_nikodim;
                break;
            case "\"Гераците\"":
                textAnalysis = R.string.analysis_geracite;
                break;
            case "\"Черна песен\"":
                textAnalysis = R.string.analysis_cherna_pesen;
                break;
            case "\"Пловдив\"":
                textAnalysis = R.string.analysis_plovdiv;
                break;
            case "\"Да се завърнеш в бащината къща\"":
                textAnalysis = R.string.analysis_da_se_zavyrnesh_v_bashtinata_kyshta;
                break;
            case "\"Помниш ли, помниш ли\"":
                textAnalysis = R.string.analysis_pomnish_li_pomnish_li;
                break;
            case "\"Спи градът\"":
                textAnalysis = R.string.analysis_spi_gradyt;
                break;
            case "\"Миг\"":
                textAnalysis = R.string.analysis_mig;
                break;
            case "\"Един убит\"":
                textAnalysis = R.string.analysis_edin_ubit;
                break;
            case "\"Сиротна песен\"":
                textAnalysis = R.string.analysis_sirotna_pesen;
                break;
            case "\"Тиха победа\"":
                textAnalysis = R.string.analysis_tiha_pobeda;
                break;
            case "\"Да бъде ден!\"":
                textAnalysis = R.string.analysis_da_byde_den;
                break;
            case "\"Ний\"":
                textAnalysis = R.string.analysis_nii;
                break;
            case "\"Йохан\"":
                textAnalysis = R.string.analysis_yohan;
                break;
            case "\"Юноша\"":
                textAnalysis = R.string.analysis_unusha;
                break;
            case "\"Стария музикант\"":
                textAnalysis = R.string.analysis_stariq_muzikant;
                break;
            case "\"Цветарка\"":
                textAnalysis = R.string.analysis_cvetarka;
                break;
            case "\"Зимни вечери\"":
                textAnalysis = R.string.analysis_zimni_vecheri;
                break;
            case "\"Септември\"":
                textAnalysis = R.string.analysis_septemvri;
                break;
            case "\"Прозорец\"":
                textAnalysis = R.string.analysis_prozorec;
                break;
            case "\"Болница\"":
                textAnalysis = R.string.analysis_bolnica;
                break;
            case "\"Стаята\"":
                textAnalysis = R.string.analysis_staqta;
                break;
            case "\"Къщата\"":
                textAnalysis = R.string.analysis_kyshtata;
                break;
            case "\"Повест\"":
                textAnalysis = R.string.analysis_povest;
                break;
            case "\"Книгите\"":
                textAnalysis = R.string.analysis_knigite;
                break;
            case "\"Камък\"":
                textAnalysis = R.string.analysis_kamyk;
                break;
            case "\"Дяволско\"":
                textAnalysis = R.string.analysis_dqvolsko;
                break;
            case "\"Кукувица\"":
                textAnalysis = R.string.analysis_kukuvica;
                break;
            case "\"Стихии\"":
                textAnalysis = R.string.analysis_stihii;
                break;
            case "\"Потомка\"":
                textAnalysis = R.string.analysis_potomka;
                break;
            case "\"Вечната\"":
                textAnalysis = R.string.analysis_vechnata;
                break;
            case "\"Песента на колелетата\"":
                textAnalysis = R.string.analysis_pesenta_na_koleletata;
                break;
            case "\"Последна радост\"":
                textAnalysis = R.string.analysis_posledna_radost;
                break;
            case "\"Шибил\"":
                textAnalysis = R.string.analysis_shibil;
                break;
            case "\"През чумавото\"":
                textAnalysis = R.string.analysis_prez_chumavoto;
                break;
            case "\"Индже\"":
                textAnalysis = R.string.analysis_indje;
                break;
            case "\"Албена\"":
                textAnalysis = R.string.analysis_albena;
                break;
            case "\"Другоселец\"":
                textAnalysis = R.string.analysis_drugoselec;
                break;
            case "\"Серафим\"":
                textAnalysis = R.string.analysis_serafim;
                break;
            case "\"Вяра\"":
                textAnalysis = R.string.analysis_vqra;
                break;
            case "\"Писмо\"":
                textAnalysis = R.string.analysis_pismo;
                break;
            case "\"Песен за човека\"":
                textAnalysis = R.string.analysis_pesenta_za_choveka;
                break;
            case "\"Сън\"":
                textAnalysis = R.string.analysis_syn;
                break;
            case "\"История\"":
                textAnalysis = R.string.analysis_istoriq;
                break;
            case "\"Завод\"":
                textAnalysis = R.string.analysis_zavod;
                break;
            case "\"Кино\"":
                textAnalysis = R.string.analysis_kino;
                break;
            case "\"Прощално\"":
                textAnalysis = R.string.analysis_proshtalno;
                break;
            case "\"Борбата е безмилостно жестока\"":
                textAnalysis = R.string.analysis_borbata_e_bezmilostna_jestoka;
                break;
            case "\"Тютюн\"":
                textAnalysis = R.string.analysis_tutun;
                break;
            case "\"Железният светилник\"":
                textAnalysis = R.string.analysis_jelezniq_svetilnik;
                break;
        }


        return textAnalysis;
    }

    public void foundTest(String name){


        Intent intent = new Intent(this, ActivityQuiz.class);

        switch (name.toLowerCase()){
            case "сборен тест":
                intent.putExtra("test", "Сборен тест");
                intent.putExtra("check", 16);
                finish();
                startActivity(intent);
                break;
            case "христо ботев":
            case "ботев":
            case "botev":
            case "hristo botev":
                intent.putExtra("test", "Христо Ботев");
                intent.putExtra("check", 1);
                finish();
                startActivity(intent);
                //Toast.makeText(this, "Hristo Botew", Toast.LENGTH_SHORT).show();
                break;
            case "иван вазов":
            case "вазов":
            case "ivan vazov":
            case "vazov":
                intent.putExtra("test", "Иван Вазов");
                intent.putExtra("check", 2);
                finish();
                startActivity(intent);
                //Toast.makeText(this, "Ivan Vazov", Toast.LENGTH_SHORT).show();
                break;
            case "алеко\n константинов":
            case "алеко константинов":
            case "aleko konstantinov":
            case "алеко":
            case "aleko":
                intent.putExtra("test", "Алеко Константинов");
                intent.putExtra("check", 3);
                finish();
                startActivity(intent);
                //Toast.makeText(this, "Aleko", Toast.LENGTH_SHORT).show();
                break;
            case "пенчо славейков":
            case "славейков":
            case "pencho slaveikov":
            case "slaveikov":
            case "pencho":
            case "пенчо":
                intent.putExtra("test", "Пенчо Славейков");
                intent.putExtra("check", 4);
                finish();
                startActivity(intent);
                break;
            case "пейо яворов":
            case "peyo qvorov":
            case "pejo qvoror":
            case "qvorov":
            case "яворов":
                intent.putExtra("test", "Пейо Яворов");
                intent.putExtra("check", 5);
                finish();
                startActivity(intent);
                break;
            case "елин пелин":
            case "elin pelin":
                intent.putExtra("test", "Елин Пелин");
                intent.putExtra("check", 6);
                finish();
                startActivity(intent);
                //Toast.makeText(this, "Elin Pelin", Toast.LENGTH_SHORT).show();
                break;
            case "димчо дебелянов":
            case "дебелянов":
            case "dimcho debelqnov":
            case "debelqnov":
            case "dimcho":
            case "димчо":
                intent.putExtra("test", "Димчо Дебелянов");
                intent.putExtra("check", 7);
                finish();
                startActivity(intent);
                break;
            case "христо смирненски":
            case "hristo smirnenski":
            case "смирненски":
            case "smirnenski":
                intent.putExtra("test", "Христо Смирненски");
                intent.putExtra("check", 8);
                finish();
                startActivity(intent);
                //Toast.makeText(this, "Hristo Smirneski", Toast.LENGTH_SHORT).show();
                break;
            case "гео милев":
            case "geo milev":
            case "geo":
            case "гео":
            case "milev":
            case "милев":
                intent.putExtra("test", "Гео Милев");
                intent.putExtra("check", 9);
                finish();
                startActivity(intent);
                break;
            case "атанас далчев":
            case "atanas dalchev":
            case "dalchev":
            case "далчев":
                intent.putExtra("test", "Атанас Далчев");
                intent.putExtra("check", 10);
                finish();
                startActivity(intent);
                break;
            case "елисавета багряна":
            case "багряна":
            case "elisaveta bagrqna":
            case "bagrqna":
                intent.putExtra("test", "Елисавета Багряна");
                intent.putExtra("check", 11);
                finish();
                startActivity(intent);
                break;
            case "йордан йовков":
            case "йовков":
            case "jordan jovkov":
            case "yordan yovkov":
            case "jovkov":
            case "yovkov":
                intent.putExtra("test", "Йордан Йовков");
                intent.putExtra("check", 12);
                finish();
                startActivity(intent);
                break;
            case "никола вапцаров":
            case "nikola vapcarov":
            case "vapcarov":
            case "вапцаров":
                intent.putExtra("test", "Никола Вапцаров");
                intent.putExtra("check", 13);
                finish();
                startActivity(intent);
                break;
            case "димитър димов":
            case "dimityr dimov":
            case "dimov":
            case "димов":
                intent.putExtra("test", "Димитър Димов");
                intent.putExtra("check", 14);
                finish();
                startActivity(intent);
                break;
            case "димитър талев":
            case "dimityr talev":
            case "talev":
            case "талев":
                intent.putExtra("test", "Димитър Талев");
                intent.putExtra("check", 15);
                finish();
                startActivity(intent);
                break;

            default:
                Toast.makeText(this, "Невалидно име", Toast.LENGTH_SHORT).show();
        }

        //return name;
    }

    public void goToHomeMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        //finish();
    }


}