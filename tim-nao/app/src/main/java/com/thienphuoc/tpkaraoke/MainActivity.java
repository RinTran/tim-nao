package com.thienphuoc.tpkaraoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import data.CustomListviewAdapter;
import data.CustomViewPager;
import data.DataHolder;
import data.DatabaseHandler;
import fragment.FragmentDisplaySongs;
import fragment.NavigationDrawerFragment;
import model.MySong;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationDrawerFragment.NavigationDrawerCallbacks{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CustomViewPager viewPager;

    private SearchView searchView;
    /**/
   // private CircleProgress circleProgress;

    /*Database*/

    private DatabaseHandler databaseHandler;
    private ArrayList<MySong> ArirangSongList =new ArrayList<>();
    private ArrayList<MySong> MusicCoreSongList =new ArrayList<>();
    private ArrayList<MySong> CaliforniaSongList =new ArrayList<>();
    private ArrayList<MySong> VietKTVSongList =new ArrayList<>();

    private ListView listView;
    private CustomListviewAdapter customListviewAdapter;

    boolean isArirangSearch=true,isMussicCoreSearch=false,isCaliforniaSearch=false,isVietKTVSearch=false;

    /*
    * Navigation Drawer ( Slider)
    * */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    //Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * Navigation Drawer (Slider)
        *
        */
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setMenuVisibility(true);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        /*Copy database file from access folder to database folder*/
        databaseHandler = DataHolder.getInstance().getDatabaseHandler();

        /*
        * Toolbar : Title + SearchView
        * */
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        
        //getSupportActionBar().setLogo(R.drawable.ic_action);
 //       SpannableString s = new SpannableString("My Title");
//        s.setSpan(new TypefaceSpan(this, "MTO Dom.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        getSupportActionBar().setTitle(s);

        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        MyPageAdapter pagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPagingEnabled(false);

        /*Tabs: Arirang,Musiccore,Viet KTV,California*/
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setIcon();
        /*
        * Set event when tab selected : Arirang,Musiccore,Viet KTV,Californial
        *
        * */
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               // super.onTabSelected(tab);
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_check);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        searchView.setQueryHint("Tìm trong Arirang");
                        listView = (ListView) findViewById(R.id.lvDisplaySongs);
                        customListviewAdapter = new CustomListviewAdapter(MainActivity.this,R.layout.song_row,DataHolder.getInstance().getArirangSongList(),0);
                        listView.setAdapter(customListviewAdapter);
                       // customListviewAdapter.notifyDataSetChanged();
                        isArirangSearch=true;isMussicCoreSearch=false;isCaliforniaSearch=false;isVietKTVSearch=false;
                        break;
                    case 1:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_check);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        searchView.setQueryHint("Tìm trong Musiccore");
                        listView = (ListView) findViewById(R.id.lvDisplaySongs);
                        customListviewAdapter = new CustomListviewAdapter(MainActivity.this,R.layout.song_row,DataHolder.getInstance().getMusicCoreSongList(),1);
                        listView.setAdapter(customListviewAdapter);
                        isArirangSearch=false;isMussicCoreSearch=true;isCaliforniaSearch=false;isVietKTVSearch=false;
                        break;
                    case 2:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_check);
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        searchView.setQueryHint("Tìm trong California");
                        listView = (ListView) findViewById(R.id.lvDisplaySongs);
                        customListviewAdapter = new CustomListviewAdapter(MainActivity.this,R.layout.song_row,DataHolder.getInstance().getCaliforniaSongList(),2);
                        listView.setAdapter(customListviewAdapter);
                        isArirangSearch=false;isMussicCoreSearch=false;isCaliforniaSearch=true;isVietKTVSearch=false;
                        break;
                    case 3:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_check);
                        setAdapterLisview(DataHolder.getInstance().getVietKTVSongList(), "Tìm trong Việt KTV",3);
                        isArirangSearch=false;isMussicCoreSearch=false;isCaliforniaSearch=false;isVietKTVSearch=true;
                        break;
                    default :
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    /*
    * customListviewAdapter -> ArirangSongList,VietKTVSongList.....
    * */
    private void setAdapterLisview(ArrayList<MySong> lst,String query,int tableSong){
        searchView.setQueryHint(query);
        listView = (ListView) findViewById(R.id.lvDisplaySongs);
        customListviewAdapter = new CustomListviewAdapter(MainActivity.this,R.layout.song_row,lst,tableSong);
        listView.setAdapter(customListviewAdapter);
    }

    /*
    * Set icon for 4 tab.
     */
    private void setIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_check);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_uncheck);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_uncheck);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_uncheck);
    }

    /*
    * Search view...
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }
    /*
    * event when user enter data from keyboard!
    * */
    @Override
    public boolean onQueryTextChange(String keyword) {

        if(isArirangSearch){

            this.ArirangSongList.clear();
            this.ArirangSongList = databaseHandler.searchInArirangSongList(keyword);
            customListviewAdapter = new CustomListviewAdapter(this,R.layout.song_row,this.ArirangSongList,0);
            listView = (ListView) findViewById(R.id.lvDisplaySongs);
            this.listView.setAdapter(customListviewAdapter);
            customListviewAdapter.notifyDataSetChanged();

        }else if(isMussicCoreSearch){
            this.MusicCoreSongList.clear();
            this.MusicCoreSongList = databaseHandler.searchInMusicCoreSongList(keyword);
            customListviewAdapter = new CustomListviewAdapter(this,R.layout.song_row,this.MusicCoreSongList,1);
            listView = (ListView) findViewById(R.id.lvDisplaySongs);
            this.listView.setAdapter(customListviewAdapter);
            customListviewAdapter.notifyDataSetChanged();

        }else if(isCaliforniaSearch){
            this.CaliforniaSongList.clear();
            this.CaliforniaSongList = databaseHandler.searchInCaliforniaSongList(keyword);
            customListviewAdapter = new CustomListviewAdapter(this,R.layout.song_row,this.CaliforniaSongList,2);
            listView = (ListView) findViewById(R.id.lvDisplaySongs);
            this.listView.setAdapter(customListviewAdapter);
            customListviewAdapter.notifyDataSetChanged();

        }else if(isVietKTVSearch){
            this.VietKTVSongList.clear();
            this.VietKTVSongList = databaseHandler.searchInVietKTVSongList(keyword);
            customListviewAdapter = new CustomListviewAdapter(this,R.layout.song_row,this.VietKTVSongList,3);
            listView = (ListView) findViewById(R.id.lvDisplaySongs);
            this.listView.setAdapter(customListviewAdapter);
            customListviewAdapter.notifyDataSetChanged();
        }
        return false;
    }

    /*
    * Navigation Drawer (Slider)
    * */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Intent intent;
        switch (position) {
            case 0:
                break;
            case 1:
                //Feature favorites.
                Intent intent0 = new Intent(this, FavoriteActivity.class);
                intent0.putExtra("Type","favorite");
                startActivity(intent0);
                DataHolder.getInstance().setNumSongType(0);
                break;
            case 2:
                //Feature backup data.
                intent = new Intent(this, BackupFavoritesActivity.class);
                startActivity(intent);
                break;
            case 3:
                //Feature recovery data.
                intent = new Intent(this, RecoveryFavoritesActivity.class);
                startActivity(intent);
                break;
            case 4:
                Intent intent1 = new Intent(this, FavoriteActivity.class);
                intent1.putExtra("Type","young");
                startActivity(intent1);
                DataHolder.getInstance().setNumSongType(1);

                Toast.makeText(MainActivity.this, "Fifth", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent intent_2 = new Intent(this, FavoriteActivity.class);
                intent_2.putExtra("Type","romantic");
                startActivity(intent_2);
                //Toast.makeText(MainActivity.this, "Six", Toast.LENGTH_SHORT).show();
                DataHolder.getInstance().setNumSongType(2);
                break;
            case 6:
                Intent intent3 = new Intent(this, FavoriteActivity.class);
                intent3.putExtra("Type","revolution");
                startActivity(intent3);
                DataHolder.getInstance().setNumSongType(3);
                //Toast.makeText(MainActivity.this, "Six", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Intent intent4 = new Intent(this, FavoriteActivity.class);
                intent4.putExtra("Type","trinh");
                startActivity(intent4);
                DataHolder.getInstance().setNumSongType(4);
                //Toast.makeText(MainActivity.this, "Six", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Intent intent5 = new Intent(this, FavoriteActivity.class);
                intent5.putExtra("Type","children");
                startActivity(intent5);
                DataHolder.getInstance().setNumSongType(5);
                //Toast.makeText(MainActivity.this, "Six", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentDisplaySongs.newInstance();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "Arirang";
                case 1: return "Music Core";
                case 2: return "California";
                case 3: return  "Việt KTV" ;
                default: return  "Error";
            }
        }
    }
}
