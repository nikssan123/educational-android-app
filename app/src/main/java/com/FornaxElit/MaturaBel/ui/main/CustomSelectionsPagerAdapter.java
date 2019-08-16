package com.FornaxElit.MaturaBel.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.FornaxElit.MaturaBel.GrammerFragment;
import com.FornaxElit.MaturaBel.PunctFragment;
import com.FornaxElit.MaturaBel.R;
import com.FornaxElit.MaturaBel.SpellFragment;

public class CustomSelectionsPagerAdapter extends FragmentPagerAdapter {


    private static final int[] TAB_TITLES = new int[]{R.string.custom_tab_text_2, R.string.custom_tab_text_1, R.string.custom_tab_text_3};
    private Context context;

   // private String textGrammer, textPunct, textSpell;

    public CustomSelectionsPagerAdapter(Context context, FragmentManager fm){//String textGrammer, String textPunct, String textSpell) {
        super(fm);
        this.context = context;
        this.context = context;
        //this.textGrammer = textGrammer;
        //this.textPunct = textPunct;
        //this.textSpell = textSpell;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = SpellFragment.newInstance("Правопис");//, textSpell);
                break;
            case 1:
                fragment = GrammerFragment.newInstance("Граматика");//, textGrammer);
                break;
            case 2:
                fragment = PunctFragment.newInstance("Пунктуация");//, textPunct);
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
