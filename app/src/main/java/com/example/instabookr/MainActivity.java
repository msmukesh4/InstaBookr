package com.example.instabookr;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Offer;
import com.example.instabookr.models.Service;
import com.squareup.picasso.Picasso;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "Main Activity";
    TextView email;
    ServiceAdapter serviceAdapter;
    ImageView iv_cart_icon,iv_salon,iv_spa,iv_gym,iv_sports;
    RelativeLayout rl_salon,rl_spa,rl_gym,rl_sports,rl_view_pager;
    LinearLayout ll_offer_layout;
    ListView gymServicesList, spaServicesList , sportsServicesList, salonServicesList;
    List<Offer> offer_list;
    private AutoScrollViewPager viewPager;
    MyPagerAdapter myPagerAdapter;
    private boolean isAllListHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Common.initServices();

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
        gymServicesList = new ListView(this);
        spaServicesList = new ListView(this);
        sportsServicesList = new ListView(this);
        salonServicesList = new ListView(this);

        initNodes();

        iv_salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set all root as gone and make salon as visible
                hideSubMenus();
                isAllListHidden = false;
                rl_salon.setVisibility(View.VISIBLE);
            }
        });

        iv_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
                isAllListHidden = false;
                rl_gym.setVisibility(View.VISIBLE);
            }
        });

        iv_spa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
                isAllListHidden = false;
                rl_spa.setVisibility(View.VISIBLE);
            }
        });

        iv_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSubMenus();
                isAllListHidden = false;
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

        populateListViews();

        rl_gym.addView(gymServicesList);
        rl_sports.addView(sportsServicesList);
        rl_spa.addView(spaServicesList);
        rl_salon.addView(salonServicesList);
    }

    private void populateListViews() {

        final ServiceAdapter gymAdapter = new ServiceAdapter(this,Common.gymServices);
        gymServicesList.setAdapter(gymAdapter);
        gymServicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Service serviceSelected = Common.gymServices.get(position);
                Toast.makeText(getApplicationContext(),"service selected "+serviceSelected.id,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: "+serviceSelected.toString());

            }
        });

        final ServiceAdapter spaAdapter = new ServiceAdapter(this,Common.spaServices);
        spaServicesList.setAdapter(spaAdapter);
        spaServicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Service serviceSelected = Common.spaServices.get(position);
                Toast.makeText(getApplicationContext(),"service selected "+serviceSelected.id,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: "+serviceSelected.toString());
            }
        });

        final ServiceAdapter sportsAdapter = new ServiceAdapter(this,Common.sportsServices);
        sportsServicesList.setAdapter(spaAdapter);
        sportsServicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Service serviceSelected = Common.sportsServices.get(position);
                Toast.makeText(getApplicationContext(),"service selected "+serviceSelected.id,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: "+serviceSelected.toString());
            }
        });

        final ServiceAdapter salonServiceAdapter = new ServiceAdapter(this,Common.salonServices);
        salonServicesList.setAdapter(salonServiceAdapter);
        salonServicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Service serviceSelected = Common.salonServices.get(position);
                Toast.makeText(getApplicationContext(),"service selected "+serviceSelected.id,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: "+position);
            }
        });
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
//            tv1.setText(offers.get(i++).getOffer_text());
            offer1.setLayoutParams(offer_layout_params);
            tv1.setGravity(Gravity.CENTER | Gravity.BOTTOM);

            offer1.addView(tv1);

            TextView tv2 = new TextView(MainActivity.this);
//            tv2.setText(offers.get(i).getOffer_text());
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
            if (isAllListHidden)
                super.onBackPressed();
            else {
                hideSubMenus();
                rl_view_pager.setVisibility(View.VISIBLE);
                isAllListHidden = true;
            }
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
            isAllListHidden = true;
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

    private class ServiceAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Service> lService;
        private LayoutInflater inflater = null;

        ServiceAdapter(Context context ,ArrayList<Service> lService){
            try{
                this.context = context;
                this.lService = lService;
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }catch (Exception e){

            }
        }

        public int getCount() {
            return lService.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            final ViewHolder holder;
            try {
                if (convertView == null) {
                    vi = inflater.inflate(R.layout.service_list_item, null);
                    holder = new ViewHolder();
                    vi.setTag(holder);
                } else {
                    holder = (ViewHolder) vi.getTag();
                }

                if (lService.get(position).getIcon() != null) {
                    holder.icon.setVisibility(View.VISIBLE);
                    Picasso.with(context).load(lService.get(position).getIcon()).placeholder(R.mipmap.ic_launcher).into(holder.icon);
                }

                holder.service_name.setText(lService.get(position).getService_name());
//
//                holder.display_name.setText(lProducts.get(position).name);
//                holder.display_number.setText(lProducts.get(position).number);


            } catch (Exception e) {


            }
            return vi;
        }

        public class ViewHolder {
            public ImageView icon;
            public TextView service_name;

            ViewHolder() {
                icon = (ImageView) findViewById(R.id.iv);
                service_name = (TextView) findViewById(R.id.name);
            }

        }

    }


}

