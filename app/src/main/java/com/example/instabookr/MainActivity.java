package com.example.instabookr;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Offer;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView email;
    ImageView iv_cart_icon,iv_salon,iv_spa,iv_gym,iv_sports;
    RelativeLayout rl_salon,rl_spa,rl_gym,rl_sports,rl_view_pager;
    LinearLayout ll_offer_layout;
    List<Offer> offer_list;
    private AutoScrollViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Log.d("Lee", Common.email);
//        Log.d("Lee",Common.uuid);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);

        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);

        viewPager.setOnPageChangeListener(myOnPageChangeListener);
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        // Main menu
        iv_salon = (ImageView) findViewById(R.id.iv_salon);
        iv_spa = (ImageView) findViewById(R.id.iv_spa);
        iv_gym = (ImageView) findViewById(R.id.iv_gym);
        iv_sports = (ImageView) findViewById(R.id.iv_sports);
        rl_salon = (RelativeLayout) findViewById(R.id.salon_treeview);
        rl_spa = (RelativeLayout) findViewById(R.id.spa_treeview);
        rl_gym = (RelativeLayout) findViewById(R.id.gym_treeview);
        rl_sports = (RelativeLayout) findViewById(R.id.sports_treeview);
        rl_view_pager = (RelativeLayout) findViewById(R.id.rl_view_pager);
//        ll_offer_layout = (LinearLayout) findViewById(R.id.ll_offers);

        iv_salon.setImageResource(R.drawable.cart_icon);
        iv_gym.setImageResource(R.drawable.cart_icon);
        iv_spa.setImageResource(R.drawable.cart_icon);
        iv_sports.setImageResource(R.drawable.cart_icon);

//        // Dummy Static Population of Offers
//        offer_list = new ArrayList<Offer>();
//        Offer offer = new Offer();
//        offer.setImg_res(R.drawable.new2);
//        offer.setOffer_text("50% off");
//        offer_list.add(offer);
//
//        Offer offer2 = new Offer();
//        offer2.setImg_res(R.drawable.new3);
//        offer2.setOffer_text("56% off");
//        offer_list.add(offer2);
//
//        Offer offer3 = new Offer();
//        offer3.setImg_res(R.drawable.new1);
//        offer3.setOffer_text("40% off");
//        offer_list.add(offer3);
//
//        Offer offer4 = new Offer();
//        offer4.setImg_res(R.drawable.new4);
//        offer4.setOffer_text("90% off");
//        offer_list.add(offer4);

//        build_offers_layout(ll_offer_layout, offer_list);

        initNodes();

        iv_salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set all root as gone and make salon as visible
                hideSubMenus();
//                initNodes();
                rl_salon.setVisibility(View.VISIBLE);
            }
        });

        iv_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
//                initNodes();
                rl_gym.setVisibility(View.VISIBLE);
            }
        });

        iv_spa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
//                initNodes();
                rl_spa.setVisibility(View.VISIBLE);
            }
        });

        iv_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
//                initNodes();
                rl_sports.setVisibility(View.VISIBLE);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void hideSubMenus() {
        rl_salon.setVisibility(View.GONE);
        rl_spa.setVisibility(View.GONE);
        rl_gym.setVisibility(View.GONE);
        rl_sports.setVisibility(View.GONE);
        rl_view_pager.setVisibility(View.GONE);
    }

    private void initNodes(){

        Log.d("Lee","Initializing nodes");

        // salon nodes
        TreeNode salon_root = TreeNode.root();
        TreeNode myProfile = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Hair Care")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode bruce = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Face")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode clark = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Nail")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode barry = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Body")).setViewHolder(new ProfileHolder(MainActivity.this));
        String[] salon_hair_service = getResources().getStringArray(R.array.salon_hair_services);
        addProfileData(myProfile, salon_hair_service);
        String[] salon_face_service = getResources().getStringArray(R.array.salon_face_services);
        addProfileData(clark,salon_face_service);
        addProfileData(bruce,salon_hair_service);
        addProfileData(barry,salon_hair_service);
        salon_root.addChildren(myProfile, bruce, barry, clark);

        AndroidTreeView tView_salon = new AndroidTreeView(MainActivity.this, salon_root);
        tView_salon.setDefaultAnimation(true);
        tView_salon.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        rl_salon.addView(tView_salon.getView());

        // spa_nodes
        TreeNode spa_root = TreeNode.root();
        TreeNode spa1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "My Profile2")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode spa2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Bruce Wayne2")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode spa3 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Clark Kent2")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode spa4 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Barry Allen2")).setViewHolder(new ProfileHolder(MainActivity.this));
        addProfileData(spa1,salon_hair_service);
        addProfileData(spa2,salon_hair_service);
        addProfileData(spa3,salon_hair_service);
        addProfileData(spa4,salon_hair_service);
        spa_root.addChildren(spa1, spa2, spa3, spa4);
        AndroidTreeView tView_spa = new AndroidTreeView(MainActivity.this, spa_root);
        tView_spa.setDefaultAnimation(true);
        tView_spa.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        rl_spa.addView(tView_spa.getView());

        // gym_nodes
        TreeNode gym_root = TreeNode.root();
        TreeNode gym1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "My Profile3")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode gym2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Bruce Wayne3")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode gym3 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Clark Kent3")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode gym4 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Barry Allen3")).setViewHolder(new ProfileHolder(MainActivity.this));
        addProfileData(gym1,salon_hair_service);
        addProfileData(gym2,salon_hair_service);
        addProfileData(gym3,salon_hair_service);
        addProfileData(gym4,salon_hair_service);
        gym_root.addChildren(gym1, gym2, gym3, gym4);
        AndroidTreeView tView_gym = new AndroidTreeView(MainActivity.this, gym_root);
        tView_gym.setDefaultAnimation(true);
        tView_gym.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        rl_gym.addView(tView_gym.getView());

        // gym_nodes
        TreeNode sports_root = TreeNode.root();
        TreeNode sports1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "My Profile4")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode sports2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Bruce Wayne4")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode sports3 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Clark Kent4")).setViewHolder(new ProfileHolder(MainActivity.this));
        TreeNode sports4 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.arrow_down, "Barry Allen4")).setViewHolder(new ProfileHolder(MainActivity.this));
        addProfileData(sports1,salon_hair_service);
        addProfileData(sports2,salon_hair_service);
        addProfileData(sports3,salon_hair_service);
        addProfileData(sports4,salon_hair_service);
        sports_root.addChildren(sports1, sports2, sports3, sports4);
        AndroidTreeView tView_sports = new AndroidTreeView(MainActivity.this, sports_root);
        tView_sports.setDefaultAnimation(true);
        tView_sports.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        rl_sports.addView(tView_sports.getView());

    }

    private void build_offers_layout(LinearLayout ll_parent_layout, List<Offer> offers){
        for (int i = 0; i<= offers.size()/2; i++ ){

            LinearLayout ll_child = new LinearLayout(MainActivity.this);
            LinearLayout offer1 = new LinearLayout(MainActivity.this);
            LinearLayout offer2 = new LinearLayout(MainActivity.this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 60);
            ll_child.setOrientation(LinearLayout.HORIZONTAL);
            ll_child.setLayoutParams(layoutParams);

            LinearLayout.LayoutParams offer_layout_params = new LinearLayout.LayoutParams(
                    0, 60,1.0f);
            offer_layout_params.gravity = Gravity.CENTER;


            // Generate random color
            Random rnd = new Random();
            int color1 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            int color2 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            offer1.setBackgroundColor(color1);
            offer1.setAlpha((float) 0.6);
            offer1.setPadding(15, 15, 15, 15);

            offer2.setBackgroundColor(color2);
            offer2.setAlpha((float) 0.6);

            offer2.setPadding(15, 15, 15, 15);

            TextView tv1 = new TextView(MainActivity.this);
            tv1.setText(offers.get(i++).getOffer_text());
            offer1.setLayoutParams(offer_layout_params);
            tv1.setGravity(Gravity.CENTER | Gravity.BOTTOM);

            offer1.addView(tv1);

            TextView tv2 = new TextView(MainActivity.this);
            tv2.setText(offers.get(i).getOffer_text());
            offer2.setLayoutParams(offer_layout_params);
            tv2.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            offer2.addView(tv2);

            ll_child.addView(offer1);
            ll_child.addView(offer2);

            ll_parent_layout.addView(ll_child);

        }
    }


    private void addProfileData(TreeNode profile, String[] service) {

        ArrayList<TreeNode> node_array = new ArrayList<>();
        for (int i = 0;i< service.length; i++){
            TreeNode treeNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.cart_icon, service[i])).setViewHolder(new HeaderHolder(MainActivity.this));
            node_array.add(treeNode);
        }
        profile.addChildren(node_array);

//        TreeNode socialNetworks = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.cart_icon, "Social")).setViewHolder(new HeaderHolder(MainActivity.this));
//        TreeNode places = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.cart_icon, "Places")).setViewHolder(new HeaderHolder(MainActivity.this));
//        profile.addChildren(socialNetworks, places);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Log.v("Lee","creation menu items");

        MenuItem menuItem = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(menuItem,R.layout.cart_text_layout);

        Log.v("Lee", "referencing menu items");
        RelativeLayout rl_cart_count = (RelativeLayout) MenuItemCompat.getActionView(menuItem);

        View image = menu.findItem(R.id.cart).getActionView();
        iv_cart_icon = (ImageView) image.findViewById(R.id.cart_icon);

        iv_cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take you to cart activity
                Toast.makeText(getApplicationContext(),"Showing cart items...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MyCartActivity.class));
            }
        });

        TextView cart_text = (TextView) rl_cart_count.findViewById(R.id.cart_text);
        if (Common.cart_items != 0){
            cart_text.setVisibility(View.VISIBLE);
            cart_text.setText(Integer.toString(Common.cart_items));
            System.out.println("notification count :"+Common.cart_items);
        }

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
        if (id == R.id.cart){
            Toast.makeText(MainActivity.this,"Going to cart...",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            hideSubMenus();
            rl_view_pager.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this,UserProfileActivity.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this,OffersMainActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    private class MyPagerAdapter extends PagerAdapter {

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

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

//            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setImageResource(res[position]);
//            ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setBackground(getDrawable(res[position]));
            layout.setLayoutParams(layoutParams);
//            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,
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

