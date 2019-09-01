package com.FornaxElit.MaturaBel;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.PriceChangeConfirmationListener;
import com.android.billingclient.api.PriceChangeFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.RewardLoadParams;
import com.android.billingclient.api.RewardResponseListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;
    private BillingClient billingClient;
    private SkuDetails ad_removal = null;
    AdView adView;
    boolean remove_ads;
    boolean isAdLoaded = true;
    static boolean isFirstOpen = true;
    InterstitialAd interstitialAd;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        constraintLayout = findViewById(R.id.constraintLayoutMain);

        FirebaseMessaging.getInstance().subscribeToTopic("Week");


        MobileAds.initialize(this, "ca-app-pub-5283989799923871~6950893953");

        sharedPreferences = getSharedPreferences("removed_ads", MODE_PRIVATE);
        remove_ads = sharedPreferences.getBoolean("remove_ads_boolean", false);

        adView = findViewById(R.id.bannerAdViewMain);
        loadAd();

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5283989799923871/9631140991");
        if(BuildConfig.DEBUG) {
            interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        }else {
            interstitialAd.loadAd(new AdRequest.Builder().build());
        }
        //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());


        boolean isNetworkAvailable = isNetworkAvailable();

        if(!isNetworkAvailable){
            showSnackbar(constraintLayout);
        }

        AppRater appRater = new AppRater(this);
        if(isFirstOpen) {
            appRater.rateApp();
        }

        /*
        Execute those lines of code to create the database
        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        questionList = quizDbHelper.getAllQuestions();
        */

        //add bellow line to ask for rating and put info in sharedPreferences to know whether the given user has already rated the app ->
        //display it no more than two times
        //action bellow is executed on positive answer from alertDialog
       // this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAd();

        boolean isNetworkAvailable = isNetworkAvailable();
        if(!isNetworkAvailable){
            showSnackbar(constraintLayout);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // finish();
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           // Toast.makeText(this, "Няма настройки, сори мотори", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EmailActiviti.class);
            isFirstOpen = false;
            startActivity(intent);
            return true;
        }else if(id == R.id.privacy_policy){
            Intent intent = new Intent(this, LegalNotice.class);
            isFirstOpen = false;
            startActivity(intent);
            return true;
        }else if(id == R.id.remove_ads){

            setBillingClient();

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.small_owl_pic_transperent)
                    .setTitle("Премахнете рекламите!")
                    .setMessage("Искате ли да премахнете рекламите за: 4,49BGN?")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(ad_removal)
                                    .build();
                            billingClient.launchBillingFlow(MainActivity.this, flowParams);
                        }
                    })
                    .setNegativeButton("Не", null)
                    .show();
        }


        return super.onOptionsItemSelected(item);
    }

    //to better understand what the intent.putExtras are for you need to follow them in their activities

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_testsLit) {
           // Toast.makeText(this, "Ко стаа авер", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Activity_Lit_Bg.class);
            intent.putExtra("activityCheck", 2);
            intent.putExtra("check", 1);
            isFirstOpen = false;
            startActivity(intent);

        } else if (id == R.id.nav_lit) {

            Intent intent = new Intent(getApplicationContext(), Activity_Lit_Bg.class);
            intent.putExtra("activityCheck", 2);
            isFirstOpen = false;
            startActivity(intent);
        } else if (id == R.id.nav_bg) {
            isFirstOpen = false;
            final Intent intent = new Intent(getApplicationContext(), activity_introduction_analysis.class);
            intent.putExtra("check", 4);
            if(interstitialAd.isLoaded()){
                interstitialAd.show();
            }else{
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
                   // interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }
            });

        } else if (id == R.id.nav_testsBg) {
            Intent intent = new Intent(getApplicationContext(), ActivityQuiz.class);
            intent.putExtra("test", "Български език");
            intent.putExtra("check", 0);
            isFirstOpen = false;
            startActivity(intent);

        } else if (id == R.id.nav_analysis) {
            Intent intent = new Intent(getApplicationContext(), Activity_Lit_Bg.class);
            intent.putExtra("activityCheck", 5);
            isFirstOpen = false;
            startActivity(intent);
        } else if(id == R.id.nav_report){
            Intent intent = new Intent(this, EmailActiviti.class);
            isFirstOpen = false;
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_privacy_policy){
            Intent intent = new Intent(this, LegalNotice.class);
            isFirstOpen = false;
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_remove_ads){
            setBillingClient();

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.small_owl_pic_transperent)
                    .setTitle("Премахнете рекламите!")
                    .setMessage("Искате ли да премахнете рекламите за: 4,49BGN?")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(ad_removal)
                                    .build();
                            billingClient.launchBillingFlow(MainActivity.this, flowParams);
                        }
                    })
                    .setNegativeButton("Не", null)
                    .show();
        }
         /*else if (id == R.id.nav_introductions){
            Intent intent = new Intent(getApplicationContext(), Activity_Lit_Bg.class);
            intent.putExtra("activityCheck", 6);
            startActivity(intent);*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //could've made this made static but forgot and now this method can be seen in almost every activity
    public void loadAd(){
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).
        AdRequest adRequest;
        if(BuildConfig.DEBUG){
            adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        }else{
          adRequest = new AdRequest.Builder().build();
        }
        adView.loadAd(adRequest);

    }

    //method to check wheter there is available network connection to the device
    public boolean isNetworkAvailable() {
       // if(Build.VERSION.SDK_INT >= 19) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
            try {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            }catch (NullPointerException e){
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        /*}else{
            return true;
        }*/
    }

    //method which is displayed through out the app and displays a snackBar if(aboveMethod returns false)
    public static void showSnackbar(final ConstraintLayout constraintLayout1){
        Snackbar snackbar = Snackbar.make(constraintLayout1, "Моля, включете си интернета :)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Разбрах", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(constraintLayout1, "Благодаря за разбирането!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                })
                .setActionTextColor(Color.rgb(9, 167, 9));
        snackbar.show();
    }

    //the two methods bellow delete the cache
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            try {
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public void testLit(View view){
        Intent intent = new Intent(MainActivity.this, Activity_Lit_Bg.class);
        intent.putExtra("check", 1);
        isFirstOpen = false;
        startActivity(intent);

    }

    public void tests(View view){
        Intent intent = new Intent(this, ActivityQuiz.class);
        intent.putExtra("test", "Български език");
        intent.putExtra("check", 0);
        isFirstOpen = false;
        startActivity(intent);

    }


    public void goToMaterials(View view){
        Intent intent = new Intent(getApplicationContext(), Activity_Lit_Bg.class);
        intent.putExtra("activityCheck", 2);
        isFirstOpen = false;
        startActivity(intent);
    }

    public void normsClick(View view){
        final Intent intent = new Intent(getApplicationContext(), activity_introduction_analysis.class);
        intent.putExtra("check", 5);
        isFirstOpen = false;
        //put string extra to the intent with the text etc.
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
                if(BuildConfig.DEBUG) {
                    interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }else {
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
                //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });

    }

    private void setBillingClient(){
        billingClient = BillingClient.newBuilder(MainActivity.this)
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
                        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null){
                            for(Purchase purchase : purchases){
                                Toast.makeText(MainActivity.this, "Congrats!", Toast.LENGTH_SHORT).show();
                                handlePurchases(purchase);

                            }
                        }else if( billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED){
                            Toast.makeText(MainActivity.this, "Неуспешна покупка! fd", Toast.LENGTH_SHORT).show();
                        }else if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED){
                            Toast.makeText(MainActivity.this, "Продуктът вече е закупен!", Toast.LENGTH_SHORT).show();
                            sharedPreferences.edit().putBoolean("remove_ads_boolean", true).apply();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Неуспешна покупка! 1", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .enablePendingPurchases()
                .build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    List<String> skuList = new ArrayList<>();
                    skuList.add("android.test.purchased");
                    //skuList.add("remove_ads");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                            Toast.makeText(MainActivity.this, "Does it work", Toast.LENGTH_SHORT).show();
                            if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null){
                                for(SkuDetails skuDetails : skuDetailsList){
                                    String sku = skuDetails.getSku();
                                    if("android.test.purchased".equals(sku)){
                                        Log.i("Info", "working");
                                    }
                                    //ad_removal = skuDetails;
                                    // Log.i("Find", "working?");
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void handlePurchases(Purchase purchase){
        AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(BillingResult billingResult) {

                Log.i("Something", "Something");
            }
        };

        if(purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED){

            remove_ads = true;
            sharedPreferences.edit().putBoolean("remove_ads_boolean", remove_ads).apply();
        }

        if(!purchase.isAcknowledged()){
            AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.getPurchaseToken())
                    .build();
            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
        }

    }


}
