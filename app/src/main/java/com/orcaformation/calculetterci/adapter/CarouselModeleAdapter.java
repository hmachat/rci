package com.orcaformation.calculetterci.adapter;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.activity.ModeleActivity;
import com.orcaformation.calculetterci.content.DBAdapter;
import com.orcaformation.calculetterci.fragment.FragmentModele;
import com.orcaformation.calculetterci.layout.LinearLayoutModele;
import com.orcaformation.calculetterci.utils.Utils;

/**
 * Created by Aicha on 17/11/2017.
 */

public class CarouselModeleAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private int PAGES;
    private int LOOPS = 1000;
    public static int FIRST_PAGE;

    private LinearLayoutModele cur = null;
    private LinearLayoutModele next = null;
    private ModeleActivity context;
    private FragmentManager fm;
    private float scale;

    public CarouselModeleAdapter(ModeleActivity context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;

        DBAdapter mDbhelper = new DBAdapter(context).open();
        Cursor cr = mDbhelper.fetchModeleByMarqueId(Utils.getFromSharedPrefs(context, "INFO_VEH", "MARQUE_ID"));
        if(cr.moveToFirst()) {
            this.PAGES = cr.getCount();
            FIRST_PAGE = PAGES * LOOPS / 2;
        }
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        if (position == FIRST_PAGE)
            scale = BIG_SCALE;
        else
            scale = SMALL_SCALE;

        position = position % PAGES;
        return FragmentModele.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        return PAGES * LOOPS;
    }

    @Override
    public void transformPage(View page, float position) {
        LinearLayoutModele myLinearLayout = (LinearLayoutModele) page.findViewById(R.id.root);
        float scale = BIG_SCALE;
        if (position > 0) {
            scale = scale - position * DIFF_SCALE;
        } else {
            scale = scale + position * DIFF_SCALE;
        }
        if (scale < 0) scale = 0;
        myLinearLayout.setScaleBoth(scale);
    }

}
