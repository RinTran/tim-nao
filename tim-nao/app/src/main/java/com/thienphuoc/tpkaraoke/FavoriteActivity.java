package com.thienphuoc.tpkaraoke;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import data.Constants;
import data.CustomListviewAdapter;
import data.CustomViewPager;
import data.DataHolder;
import data.DatabaseHandler;
import fragment.FragmentDisplaySongFavorite;

public class FavoriteActivity extends AppCompatActivity {
    private Toolbar toolbarFav;
    private TabLayout tabLayoutFav;
    private CustomViewPager viewPagerFav;

    private ListView listView;
    private CustomListviewAdapter customListviewAdapterFav;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

//        databaseHandler = new DatabaseHandler(this);
//        databaseHandler.copyDB();

        databaseHandler = DataHolder.getInstance().getDatabaseHandler();

        /*
        * Init toolbar !
        * */
        toolbarFav = (Toolbar) findViewById(R.id.toolBarFav);
        setSupportActionBar(toolbarFav);

        toolbarFav.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_exit:
                        finish();
                        return true;
                    default :
                        return false;
                }
            }
        });

        viewPagerFav = (CustomViewPager) findViewById(R.id.viewPagerFav);
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPagerFav.setAdapter(pageAdapter);
        viewPagerFav.setPagingEnabled(true);

        tabLayoutFav = (TabLayout) findViewById(R.id.tabLayoutFav);
        tabLayoutFav.setTabsFromPagerAdapter(pageAdapter);
        tabLayoutFav.setupWithViewPager(viewPagerFav);
        setIcon();

        final Bundle bundle = getIntent().getExtras();
        final String type = bundle.getString("Type");

        if (bundle!=null){
            if (type.equals("favorite")){
                getSupportActionBar().setTitle(" Yêu Thích");
                getSupportActionBar().setLogo(R.drawable.ic_favorite_activity);

            }
            if (type.equals("young")){
                getSupportActionBar().setTitle(" Nhạc Trẻ");
                getSupportActionBar().setLogo(R.drawable.ic_music);

            }
            if (type.equals("romantic")){
                getSupportActionBar().setTitle(" Nhạc Trữ Tình");
                getSupportActionBar().setLogo(R.drawable.ic_music);

            }
            if (type.equals("revolution")){
                getSupportActionBar().setTitle(" Nhạc Cách Mạng");
                getSupportActionBar().setLogo(R.drawable.ic_music);

            }
            if (type.equals("trinh")){
                getSupportActionBar().setTitle(" Nhạc Trịnh");
                getSupportActionBar().setLogo(R.drawable.ic_music);

            }
            if (type.equals("children")){
                getSupportActionBar().setTitle(" Nhạc Thiếu Nhi");
                getSupportActionBar().setLogo(R.drawable.ic_music);

            }
        }

        tabLayoutFav.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayoutFav.getTabAt(0).setIcon(R.drawable.ic_check);
                        tabLayoutFav.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        listView = (ListView) findViewById(R.id.lvFavorite);

                        //add type
                        if (bundle!=null){
                            if (type.equals("favorite")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInALLNumber(Constants.DB_TABLE_ARIRANG),0);
                            }
                            if (type.equals("young")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeYoung(Constants.DB_TABLE_ARIRANG),0);
                            }
                            if (type.equals("romantic")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRomantic(Constants.DB_TABLE_ARIRANG),0);
                            }
                            if (type.equals("revolution")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRevolution(Constants.DB_TABLE_ARIRANG),0);
                            }
                            if (type.equals("trinh")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeTrinh(Constants.DB_TABLE_ARIRANG),0);
                            }
                            if (type.equals("children")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeChildren(Constants.DB_TABLE_ARIRANG),0);
                            }
                        }
                        listView.setAdapter(customListviewAdapterFav);
                        customListviewAdapterFav.notifyDataSetChanged();
                        break;
                    case 1:
                        tabLayoutFav.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(1).setIcon(R.drawable.ic_check);
                        tabLayoutFav.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        listView = (ListView) findViewById(R.id.lvFavorite);
                        //add type
                        if (bundle!=null){
                            if (type.equals("favorite")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInALLNumber(Constants.DB_TABLE_MUSICCORE),0);
                            }
                            if (type.equals("young")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeYoung(Constants.DB_TABLE_MUSICCORE),0);
                            }
                            if (type.equals("romantic")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRomantic(Constants.DB_TABLE_MUSICCORE),0);
                            }
                            if (type.equals("revolution")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRevolution(Constants.DB_TABLE_MUSICCORE),0);
                            }
                            if (type.equals("trinh")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeTrinh(Constants.DB_TABLE_MUSICCORE),0);
                            }
                            if (type.equals("children")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeChildren(Constants.DB_TABLE_MUSICCORE),0);
                            }
                        }
                        listView.setAdapter(customListviewAdapterFav);
                        break;
                    case 2:
                        tabLayoutFav.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(2).setIcon(R.drawable.ic_check);
                        tabLayoutFav.getTabAt(3).setIcon(R.drawable.ic_uncheck);
                        listView = (ListView) findViewById(R.id.lvFavorite);
                        //add type
                        if (bundle!=null){
                            if (type.equals("favorite")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInALLNumber(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                            if (type.equals("young")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeYoung(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                            if (type.equals("romantic")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRomantic(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                            if (type.equals("revolution")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRevolution(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                            if (type.equals("trinh")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeTrinh(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                            if (type.equals("children")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeChildren(Constants.DB_TABLE_CALIFORNIA),0);
                            }
                        }
                        listView.setAdapter(customListviewAdapterFav);
                        break;
                    case 3:
                        tabLayoutFav.getTabAt(0).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(1).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(2).setIcon(R.drawable.ic_uncheck);
                        tabLayoutFav.getTabAt(3).setIcon(R.drawable.ic_check);
                        listView = (ListView) findViewById(R.id.lvFavorite);
                        //add type
                        if (bundle!=null){
                            if (type.equals("favorite")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInALLNumber(Constants.DB_TABLE_VIETKTV),0);
                            }
                            if (type.equals("young")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeYoung(Constants.DB_TABLE_VIETKTV),0);
                            }
                            if (type.equals("romantic")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRomantic(Constants.DB_TABLE_VIETKTV),0);
                            }
                            if (type.equals("revolution")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeRevolution(Constants.DB_TABLE_VIETKTV),0);
                            }
                            if (type.equals("trinh")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeTrinh(Constants.DB_TABLE_VIETKTV),0);
                            }
                            if (type.equals("children")){
                                customListviewAdapterFav = new CustomListviewAdapter(FavoriteActivity.this,
                                        R.layout.song_row, databaseHandler.searchInAllTypeChildren(Constants.DB_TABLE_VIETKTV),0);
                            }
                        }
                        listView.setAdapter(customListviewAdapterFav);
                        break;
                    default :
                        Toast.makeText(FavoriteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /*
    * Set icon for 4 tab.
     */
    private void setIcon(){
        tabLayoutFav.getTabAt(0).setIcon(R.drawable.ic_check);
        tabLayoutFav.getTabAt(1).setIcon(R.drawable.ic_uncheck);
        tabLayoutFav.getTabAt(2).setIcon(R.drawable.ic_uncheck);
        tabLayoutFav.getTabAt(3).setIcon(R.drawable.ic_uncheck);
    }

    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentDisplaySongFavorite.newInstance();
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
