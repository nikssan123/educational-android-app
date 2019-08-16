package com.FornaxElit.MaturaBel.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.FornaxElit.MaturaBel.AnalysisFragment;
import com.FornaxElit.MaturaBel.IntroductionFragment;
import com.FornaxElit.MaturaBel.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private String headerIntro,  headerAnalysis; //, textAnalysis, textIntro;
    private int textIntro, textAnalysis;



    public SectionsPagerAdapter(Context context, FragmentManager fm, String headerIntro, int textIntro, String headerAnalysis, int textAnalysis) {
        super(fm);
        mContext = context;
        this.headerIntro = headerIntro;
        this.textIntro = textIntro;
        this.headerAnalysis = headerAnalysis;
        this.textAnalysis = textAnalysis;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = IntroductionFragment.newInstance(headerIntro,textIntro);
                break;
            case 1:
                fragment = AnalysisFragment.newInstance(headerAnalysis, textAnalysis);
                break;
        }
        return fragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}