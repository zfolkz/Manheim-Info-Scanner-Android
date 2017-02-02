package com.manheiminfoscanner.mudule;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.manheiminfoscanner.R;
import com.manheiminfoscanner.adapter.ViewPagerAdapter;

public class WishListActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment[] fragmentList = {
            WishListCarDetailFragment.newInstance("",""),WishListPhotoFragment.newInstance("","")
    };
    private String[] toolbarTitle = {
            "Car Detail","Photo"
    };
    private int[] tabs_icon = {
            R.drawable.cardetail,
            R.drawable.photo
    };

    private int[] tabs_icon_active = {
            R.drawable.cardetail_ac,
            R.drawable.photo_ac
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        setUpToolbar();

        setUpViewpager();
        setUpTap();
    }

    private void setupTabIcons(int active) {




        for (int i = 0; i< tabs_icon.length; i++){
            if (active==i){
                tabLayout.getTabAt(i).setIcon(tabs_icon_active[i]).setText(toolbarTitle[i]);
            }else{
                tabLayout.getTabAt(i).setIcon(tabs_icon[i]).setText(toolbarTitle[i]);
            }

        }
    }
    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.drawable.logo);
        //toolbar.setTitle(toolbarTitle[0]);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("WishList");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void setUpTap() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(viewPager.getCurrentItem());
        //tabLayout.setupWithViewPager(viewPager);

    }
    private void setUpViewpager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i=0;i<fragmentList.length;i++){
            adapter.addFragment(fragmentList[i], toolbarTitle[i]);
        }

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setupTabIcons(position);
                //getSupportActionBar().setTitle(toolbarTitle[position]);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
