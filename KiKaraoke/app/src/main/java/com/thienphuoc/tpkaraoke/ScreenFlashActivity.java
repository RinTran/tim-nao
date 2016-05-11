package com.thienphuoc.tpkaraoke;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import data.DataHolder;
import data.DatabaseHandler;
import model.MySong;

public class ScreenFlashActivity extends AppCompatActivity  {

    private ArrayList<MySong> ArirangSongList;
    private ArrayList<MySong> MusicCoreSongList;
    private ArrayList<MySong> CaliforniaSongList;
    private ArrayList<MySong> VietKTVSongList;

    /*Database*/
    private DatabaseHandler databaseHandler;
    private DatabaseHandler databaseHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_flash);
        getWindow().getDecorView().getBackground().setDither(true);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        ArirangSongList = new ArrayList<>();
        MusicCoreSongList = new ArrayList<>();
        CaliforniaSongList = new ArrayList<>();
        VietKTVSongList = new ArrayList<>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadDataFromDatabase().execute();
            }
        });
    }

    private class LoadDataFromDatabase extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {

            /*Copy database file from access folder to database folder*/
            databaseHandler = new DatabaseHandler(getApplicationContext());
            databaseHandler.copyDB();

             /*Copy database file from access folder to database folder*/
            databaseHandler2 = new DatabaseHandler(getApplicationContext());
            databaseHandler2.copyDB();

            /*Load data from database....*/
            ArirangSongList = databaseHandler2.getArirangSongList();
            MusicCoreSongList = databaseHandler2.getMusiccoreSongList();
            CaliforniaSongList = databaseHandler2.getCaliforniaSongList();
            VietKTVSongList = databaseHandler2.getVietKTVSongList();

            return "HaHa";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            DataHolder.getInstance().setDatabaseHandler(databaseHandler);
            DataHolder.getInstance().setArirangSongList(ArirangSongList);
            DataHolder.getInstance().setCaliforniaSongList(CaliforniaSongList);
            DataHolder.getInstance().setMusicCoreSongList(MusicCoreSongList);
            DataHolder.getInstance().setVietKTVSongList(VietKTVSongList);
            loadingComplete();
        }
    }

    void loadingComplete(){
        Intent startMainScreen =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(startMainScreen);
        finish();
    }
}
