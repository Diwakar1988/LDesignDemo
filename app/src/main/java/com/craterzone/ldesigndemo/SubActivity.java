package com.craterzone.ldesigndemo;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.craterzone.ldesigndemo.tabs.SlidingTabLayout;

import java.util.ArrayList;


public class SubActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private SlidingTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] titles = {"Tab 1", "Tab 2", "Tab 3"};
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), titles);
        for (int i = 0; i < titles.length; i++) {
            pagerAdapter.addPage(TabPage.newInstance("Page " + (i + 1)));
        }
        pager.setAdapter(pagerAdapter);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setCustomTabView(R.layout.layout_tab_view, R.id.tvTab);
        tabLayout.setViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class PagerAdapter extends FragmentPagerAdapter {
        private ArrayList<TabPage> pages = new ArrayList<TabPage>();
        private String[] pageTitles;

        public PagerAdapter(FragmentManager fm, String[] pageTitles) {
            super(fm);
            this.pageTitles = pageTitles;
        }

        public void addPage(TabPage page) {
            pages.add(page);
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pageTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = null;
            switch (position) {
                case 0:
                    drawable = getDrawable(R.drawable.ic_pending);
                    break;
                case 1:
                    drawable = getDrawable(R.drawable.ic_synching);
                    break;
                case 2:
                    drawable = getDrawable(R.drawable.ic_synched);
                    break;
            }
            drawable.setBounds(0, 0, 36, 36);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }

    public static class TabPage extends Fragment {

        private String text;

        public static TabPage newInstance(String text) {

            TabPage page = new TabPage();
            page.text = text;
            return page;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.layout_tab_page, null);
            ((TextView) root.findViewById(R.id.tv_tab_page)).setText(text);
            return root;
        }
    }
}
