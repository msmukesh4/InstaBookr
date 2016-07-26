package com.example.instabookr;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


/**
 * Created by mukesh on 25/7/16.
 */
public class OffersMainActivity extends AppCompatActivity {
    ImageView iv_cart_icon,iv_salon,iv_spa,iv_gym,iv_sports;
    RelativeLayout rl_salon,rl_spa,rl_gym,rl_sports;
    private AutoScrollViewPager viewPager;
//    private ViewPager viewPager;
//    private TextView textMsg;
    MyPagerAdapter myPagerAdapter;
    private List<Integer> imageIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_main);

//        viewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);
//
//        myPagerAdapter = new MyPagerAdapter();
//        viewPager.setAdapter(myPagerAdapter);
//
//        viewPager.setOnPageChangeListener(myOnPageChangeListener);
//        viewPager.setInterval(2000);
//        viewPager.startAutoScroll();
//
//        // Main menu
//        iv_salon = (ImageView) findViewById(R.id.iv_salon);
//        iv_spa = (ImageView) findViewById(R.id.iv_spa);
//        iv_gym = (ImageView) findViewById(R.id.iv_gym);
//        iv_sports = (ImageView) findViewById(R.id.iv_sports);
//        rl_salon = (RelativeLayout) findViewById(R.id.salon_treeview);
//        rl_spa = (RelativeLayout) findViewById(R.id.spa_treeview);
//        rl_gym = (RelativeLayout) findViewById(R.id.gym_treeview);
//        rl_sports = (RelativeLayout) findViewById(R.id.sports_treeview);
//
//        iv_salon.setImageResource(R.drawable.cart_icon);
//        iv_gym.setImageResource(R.drawable.cart_icon);
//        iv_spa.setImageResource(R.drawable.cart_icon);
//        iv_sports.setImageResource(R.drawable.cart_icon);
//
//        iv_salon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // set all root as gone and make salon as visible
//
////                initNodes();
//                rl_salon.setVisibility(View.VISIBLE);
//            }
//        });
//
//        iv_gym.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                initNodes();
//                rl_gym.setVisibility(View.VISIBLE);
//            }
//        });
//
//        iv_spa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                initNodes();
//                rl_spa.setVisibility(View.VISIBLE);
//            }
//        });
//
//        iv_sports.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                initNodes();
//                rl_sports.setVisibility(View.VISIBLE);
//            }
//        });

    }

    @Override
    public void onBackPressed() {
       finish();
    }

    ViewPager.OnPageChangeListener myOnPageChangeListener =
            new ViewPager.OnPageChangeListener(){

                @Override
                public void onPageScrollStateChanged(int state) {
                    //Called when the scroll state changes.
                }

                @Override
                public void onPageScrolled(int position,
                                           float positionOffset, int positionOffsetPixels) {
                    //This method will be invoked when the current page is scrolled,
                    //either as part of a programmatically initiated smooth scroll
                    //or a user initiated touch scroll.
                }

                @Override
                public void onPageSelected(int position) {
                    //This method will be invoked when a new page becomes selected.
//                    textMsg.append("onPageSelected:" + position + "\n");
                }};

    private class MyPagerAdapter extends PagerAdapter{

        int NumberOfPages = 3;

        int[] res = { R.drawable.nature1,
                        R.drawable.nature2,
                        R.drawable.nature3};
        int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030};

        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(OffersMainActivity.this);
            imageView.setImageResource(res[position]);
            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(OffersMainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layout.setBackgroundColor(backgroundcolor[position]);
            layout.setLayoutParams(layoutParams);
            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(OffersMainActivity.this,
                            "Page " + page + " clicked", Toast.LENGTH_LONG)
                            .show();
                }
            });

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);

        }




    }
}
