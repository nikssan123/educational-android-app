package com.FornaxElit.MaturaBel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class AppRater {


    private Context mContext;

    public AppRater(Context context){
        this.mContext = context;
    }

    public void rateApp(){
        long countOpenedApp = 0;
        int counterDialogShowed = 0;
        boolean showAgain = true;

        final SharedPreferences sharedPreferences = mContext.getSharedPreferences("com.FornaxElit.snackbar_rating_test", MODE_PRIVATE);


        try{
            countOpenedApp = sharedPreferences.getLong("counter", 0);
            counterDialogShowed = sharedPreferences.getInt("counterDialog", 0);
            showAgain = sharedPreferences.getBoolean("showAgain", true);
        }catch (Exception e){
            e.printStackTrace();
        }

        sharedPreferences.edit().putLong("counter", countOpenedApp + 1).apply();

        //Toast.makeText(mContext, Boolean.toString(showAgain) + "," + countOpenedApp + "," + counterDialogShowed, Toast.LENGTH_SHORT).show();


        if (showAgain) {
                if (counterDialogShowed <= 3) {
                    if (countOpenedApp == 2 || countOpenedApp == 5) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                                .setTitle("Оценете приложението! :)")
                                .setMessage("Ако това приложение Ви е харесало, моля оценете го тук :)")
                                .setPositiveButton("Оценете!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        try {
                                            sharedPreferences.edit().putBoolean("showAgain", false).apply();
                                            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.FornaxElit.MaturaBel")));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNeutralButton("По-късно", null)
                                .setNegativeButton("Не, благодаря!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        sharedPreferences.edit().putBoolean("showAgain", false).apply();
                                    }
                                });

                        AlertDialog alertDialog = builder.show();
                        //change button_positive to negative or neutral and change their colors as well
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(3, 126, 3));
                        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.rgb(187, 73, 25));
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(187, 73, 25));


                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(12);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(12);
                        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextSize(12);


                        alertDialog.show();
                        counterDialogShowed++;
                        sharedPreferences.edit().putInt("counterDialog", counterDialogShowed).apply();
                    }
                }
        }

    }

}
