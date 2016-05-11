package com.thienphuoc.tpkaraoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import data.Constants;
import data.DatabaseHandler;
import model.MySong;

public class SongDetailActivity extends AppCompatActivity {

    private TextView tvNumberCode ;
    private TextView tvNameSong;
    private TextView tvLyricSong;
    private TextView tvAuthor,tvFavorite;

    private ImageButton ibFavorite;
    private ImageButton ibClose;

    private DatabaseHandler databaseHandler;
    private MySong mySong;
    private int tableSong =-1; //Init data
    private String stableSong ="";//Init data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        tvNumberCode = (TextView) findViewById(R.id.tvACDetailID);
        tvNameSong = (TextView) findViewById(R.id.tvACDetailNameSong);
        tvLyricSong = (TextView) findViewById(R.id.tvACDetailLyric);
        tvAuthor = (TextView) findViewById(R.id.tvACDetailAuthor);

        ibFavorite = (ImageButton) findViewById(R.id.ibACDetailFavorite);
        ibClose = (ImageButton) findViewById(R.id.ibACDetailClose);

        mySong = (MySong) getIntent().getSerializableExtra("mysong");
        tableSong = getIntent().getIntExtra("tableSong",-1);

        Toast.makeText(SongDetailActivity.this, Integer.toString(tableSong), Toast.LENGTH_SHORT).show();
        databaseHandler = new DatabaseHandler(this);

        if(tableSong==0)
            stableSong=Constants.DB_TABLE_ARIRANG;
        else if(tableSong ==1)
            stableSong=Constants.DB_TABLE_MUSICCORE;
        else if(tableSong ==2)
            stableSong=Constants.DB_TABLE_CALIFORNIA;
        else if(tableSong ==3)
            stableSong=Constants.DB_TABLE_VIETKTV;

        /*
        * Search song in database ....
        * */
        mySong = databaseHandler.getAllSongInXXList(mySong.getNumberCode(),stableSong);
        databaseHandler.close();

        /*
        * set data for some textview ^^...
        * */
        tvNumberCode.setText(mySong.getNumberCode());
        tvNameSong.setText(mySong.getSongName());
        tvLyricSong.setText(mySong.getSongLyric());
        tvAuthor.setText("Tác Giả: " + mySong.getSongAuthor());

        /*
        * Close click
        * */
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        /*
        * Check if song in list favorite ? ok -> set icon : ic_favorite_selected
        *
        * */
        if( Integer.valueOf(mySong.getSongFavorite()) == 1 )
            ibFavorite.setImageResource(R.drawable.ic_favorite_selected);
        else ibFavorite.setImageResource(R.drawable.ic_favorite_unselected);

        /*
        * Button favorite click !
        * */
        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////

                Toast.makeText(SongDetailActivity.this,""+ mySong.getNumberCode() ,Toast.LENGTH_SHORT).show();

                if(Integer.valueOf(mySong.getSongFavorite()) == 0 ){
                    ibFavorite.setImageResource(R.drawable.ic_favorite_selected);
                    mySong.setSongFavorite("1");
                    databaseHandler = new DatabaseHandler(SongDetailActivity.this);
                    databaseHandler.updateSonginXXList(mySong, stableSong);
                    databaseHandler.close();
                } else {
                    ibFavorite.setImageResource(R.drawable.ic_favorite_unselected);
                    mySong.setSongFavorite("0");
                    databaseHandler = new DatabaseHandler(SongDetailActivity.this);
                    databaseHandler.updateSonginXXList(mySong, stableSong);
                    databaseHandler.close();
                }
            }
        });
    }

    public void close(){
           finish();
    }
}
