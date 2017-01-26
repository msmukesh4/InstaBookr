package com.example.instabookr;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabookr.models.Offer;
import com.example.instabookr.models.Service;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "Main Activity";
    private ImageView iv_cart_icon,ivGym,ivSports,ivSalon,ivSpa;
    private ViewPager servicesViewPager;
    private TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private RelativeLayout homePageInitailLayout,layoutWithFragments;
    private AutoScrollViewPager autoScrollViewPager;
    private GridView offersGrid;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<FragmentModel> fragmentArraylist;
    private boolean isServicesVisible = false;
    private boolean isBackPressed = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentArraylist = new ArrayList<>();
        FragmentModel fm = new FragmentModel();

        // for test only
        Service s1 = new Service("name",12);
        Service s2 = new Service("nnnn",2);
        Service s3 = new Service("mmmm",112);
        Service s4 = new Service("kkkk",122);
        ArrayList<Service> sl = new ArrayList<>();
        sl.add(s1);
        sl.add(s2);
        sl.add(s3);
        sl.add(s4);
        fm.service_list = sl;

        fragmentArraylist.add(fm);
        fragmentArraylist.add(fm);
        fragmentArraylist.add(fm);
        fragmentArraylist.add(fm);
        // test code ends

        ivGym = (ImageView) findViewById(R.id.iv_gym);
        ivSpa = (ImageView) findViewById(R.id.iv_spa);
        ivSports = (ImageView) findViewById(R.id.iv_sports);
        ivSalon = (ImageView) findViewById(R.id.iv_salon);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),getApplicationContext(),fragmentArraylist);
        servicesViewPager = (ViewPager) findViewById(R.id.view_pager_container);
        servicesViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the ViewPager with the sections adapter.
        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(servicesViewPager);

        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.mipmap.ic_launcher);

        View view2 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.mipmap.ic_launcher);

        View view3 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.mipmap.ic_launcher);

        View view4 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.mipmap.ic_launcher);

        tabLayout.getTabAt(0).setCustomView(view1);
        tabLayout.getTabAt(1).setCustomView(view2);
        tabLayout.getTabAt(2).setCustomView(view3);
        tabLayout.getTabAt(3).setCustomView(view4);

        homePageInitailLayout = (RelativeLayout) findViewById(R.id.rl_main_extra);
        layoutWithFragments = (RelativeLayout) findViewById(R.id.main_with_fragments);
        autoScrollViewPager = (AutoScrollViewPager) findViewById(R.id.auto_scroll_view_pager);
        offersGrid = (GridView) findViewById(R.id.offers_grid);


        // config auto scroll view pager
        myPagerAdapter = new MyPagerAdapter();
        autoScrollViewPager.setAdapter(myPagerAdapter);

        autoScrollViewPager.setOnPageChangeListener(myOnPageChangeListener);
        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.startAutoScroll();

        // Populate Offers
        initDemoOffers();
        offersGrid.setAdapter(new OfferAdapter(this));


        //  Show the services list
        ivSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();
            }
        });

        ivGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();
            }
        });

        ivSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();
            }
        });

        ivSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();
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

        setGridViewHeightBasedOnChildren(offersGrid,2);
    }

    private void toggleVisibility(){
        if (isServicesVisible){
            homePageInitailLayout.setVisibility(View.VISIBLE);
            layoutWithFragments.setVisibility(View.INVISIBLE);
            isServicesVisible = false;
        }else {
            homePageInitailLayout.setVisibility(View.INVISIBLE);
            layoutWithFragments.setVisibility(View.VISIBLE);
            isServicesVisible = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isServicesVisible){
            toggleVisibility();
        }else {
            if (isBackPressed)
                finish();
            else {
                Toast.makeText(getApplicationContext(),"Press Again To Exit",Toast.LENGTH_LONG).show();
                isBackPressed = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isBackPressed = false;
                    }
                }, 5000);
            }
        }
    }

    private void initDemoOffers() {
        Common.offerList = new ArrayList<>();
        Common.offerList.add(new Offer("head1","desc1"));
        Common.offerList.add(new Offer("head2","desc2"));
        Common.offerList.add(new Offer("head3","desc3"));
        Common.offerList.add(new Offer("head4","desc4"));
    }

    ViewPager.OnPageChangeListener myOnPageChangeListener = new ViewPager.OnPageChangeListener(){

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
                }
    };


    // for auto scroll view pager
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

            LinearLayout layout = new LinearLayout(HomePage.this);
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
                    Toast.makeText(HomePage.this,
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ServiceListFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        ListView listView;
        FragmentModel fragmentModel;

        public ServiceListFragment() {
        }



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ServiceListFragment newInstance(FragmentModel fragmentModel) {
            ServiceListFragment fragment = new ServiceListFragment();
            fragment.fragmentModel = fragmentModel;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(R.id.list_view);
            listView.setAdapter(new ServiceAdapter(fragmentModel,getContext()));

//            listView.setAdapter(new ArrayAdapter<Service>(getActivity(),android.R.layout.simple_list_item_1,fragmentModel.getService_list()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getContext(),fragmentModel.getService_list().get(i).getService_name(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(),ServiceListActivity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    public static class ServiceAdapter extends BaseAdapter{

        ArrayList<Service> servicesList;
        Context context;
        LayoutInflater inflater;

        ServiceAdapter(FragmentModel fragments, Context context){
            servicesList = fragments.getService_list();
            this.context = context;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return servicesList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            myServiceListHolder holder;
            if (convertView == null){
                convertView = inflater.inflate(R.layout.service_list_item,null,false);
                holder = new myServiceListHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (myServiceListHolder) convertView.getTag();
            }

            holder.name.setText(servicesList.get(position).getService_name());


            return convertView;
        }

        private class myServiceListHolder {
            ImageView icon;
            TextView name;
            public myServiceListHolder(View view) {
                icon = (ImageView) view.findViewById(R.id.iv);
                name = (TextView) view.findViewById(R.id.name);
            }

        }

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Context context;
        private ArrayList<FragmentModel> fragments;

        public SectionsPagerAdapter(FragmentManager fm, Context context, ArrayList<FragmentModel> fragments ) {
            super(fm);
            this.context = context;
            this.fragments = fragments;

        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a ServiceListFragment (defined as a static inner class below).
            return ServiceListFragment.newInstance(fragments.get(position));
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
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
                startActivity(new Intent(HomePage.this,MyCartActivity.class));
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
            Toast.makeText(HomePage.this,"Going to cart...",Toast.LENGTH_SHORT).show();
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

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(HomePage.this, UserProfileActivity.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(HomePage.this, OffersMainActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class OfferAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        public OfferAdapter(Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return Common.offerList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null){
                view = new View(context);
                view = inflater.inflate(R.layout.activity_offers_main , null);
                OfferHolder offerHolder = new OfferHolder(view);
                offerHolder.header.setText(Common.offerList.get(position).offersHeader);
                offerHolder.desc.setText(Common.offerList.get(position).offerDesc);

            }else {
                view = convertView;
            }
            return view;
        }

        class OfferHolder{
            ImageView ivBackground;
            TextView header,desc;

            OfferHolder(View view){
                ivBackground = (ImageView) view.findViewById(R.id.background);
                header = (TextView) view.findViewById(R.id.offer_header);
                desc = (TextView) view.findViewById(R.id.offer_desc);
            }
        }
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
        gridView.setFocusable(false);

    }

    class FragmentModel{
        private ArrayList<Service> service_list;

        public ArrayList<Service> getService_list() {
            return service_list;
        }

        public void setService_list(ArrayList<Service> service_list) {
            this.service_list = service_list;
        }
    }
}

