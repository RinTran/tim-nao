package data;

import java.util.ArrayList;

import model.MySong;

/**
 * Created by thienphuoc on 5/6/2016.
 */
public class DataHolder {

    public int getNumSongType() {
        return NumSongType;
    }

    public void setNumSongType(int numSongType) {
        NumSongType = numSongType;
    }

    private int NumSongType;

    private DatabaseHandler databaseHandler;

    private ArrayList<MySong> ArirangSongList;
    private ArrayList<MySong> MusicCoreSongList;
    private ArrayList<MySong> CaliforniaSongList;
    private ArrayList<MySong> VietKTVSongList;

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}


    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public ArrayList<MySong> getArirangSongList() {
        return ArirangSongList;
    }

    public void setArirangSongList(ArrayList<MySong> arirangSongList) {
        ArirangSongList = arirangSongList;
    }

    public ArrayList<MySong> getMusicCoreSongList() {
        return MusicCoreSongList;
    }

    public void setMusicCoreSongList(ArrayList<MySong> musicCoreSongList) {
        MusicCoreSongList = musicCoreSongList;
    }

    public ArrayList<MySong> getCaliforniaSongList() {
        return CaliforniaSongList;
    }

    public void setCaliforniaSongList(ArrayList<MySong> californiaSongList) {
        CaliforniaSongList = californiaSongList;
    }

    public ArrayList<MySong> getVietKTVSongList() {
        return VietKTVSongList;
    }

    public void setVietKTVSongList(ArrayList<MySong> vietKTVSongList) {
        VietKTVSongList = vietKTVSongList;
    }
}
