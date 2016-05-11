package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import model.MySong;

/**
 * Created by Thien Phuoc on 2/4/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    private Context context;
    private String DirectOfDB="";
    private ArrayList<MySong> ArirangSongList = new ArrayList<>();
    private ArrayList<MySong> MusiccoreSongList = new ArrayList<>();
    private ArrayList<MySong> CaliforniaSongList = new ArrayList<>();
    private ArrayList<MySong> VietKTVSongList = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context=context;
        this.DirectOfDB = context.getDatabasePath(Constants.DB_NAME).getPath();
    }

    /**
     * Create new db
     *
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE ...
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    *
    * Function: copy file database from access folder to database folder.
    *
    * */
    public void copyDB(){
        if(!doseDBExist()){
            this.getReadableDatabase();
            try {
                InputStream is = context.getAssets().open(Constants.DB_NAME);
                OutputStream os = new FileOutputStream(context.getDatabasePath(Constants.DB_NAME).getPath());
                byte[] buffer = new byte[1024];//1024byte=1kb
                int length = 0;
                while ((length = is.read(buffer)) > 0 ){
                    os.write(buffer,0,length);
                }
                is.close();
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Check if db is exist?
    * */

    public boolean doseDBExist(){
        File db = this.context.getDatabasePath(Constants.DB_NAME);
        return db.exists();
    }

    /*
    * Get table song Arirang.
    *
    * */

    public ArrayList<MySong> getArirangSongList(){

        this.ArirangSongList.clear();

        String [] columns = {

                Constants.DB_COLUMN_ID_SONG,
                Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,
                Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,
                Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_VOL,
                Constants.DB_COLUMN_LANGUAGE,
                Constants.DB_COLUMN_GENRE,
                Constants.DB_COLUMN_FAVAVORITE,
                Constants.DB_COLUMN_AUTHOR};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.DB_TABLE_ARIRANG,columns,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setId(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_ID_SONG)));
                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongName2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG2)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongLyric2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG2)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));
                mySong.setSongGenre(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_GENRE)));
                mySong.setSongLanguage(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LANGUAGE)));
                mySong.setSongFavorite(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_FAVAVORITE)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    /*
    * Get table song Musiccore
    *
    * */

    public ArrayList<MySong> getMusiccoreSongList(){

        this.MusiccoreSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_VOL,Constants.DB_COLUMN_AUTHOR};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.DB_TABLE_MUSICCORE,columns,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.MusiccoreSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        db.close();
        return this.MusiccoreSongList;
    }

    /*
    *
    *Get table song California
    * */

    public ArrayList<MySong> getCaliforniaSongList(){

        this.CaliforniaSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_VOL,Constants.DB_COLUMN_AUTHOR};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.DB_TABLE_CALIFORNIA,columns,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.CaliforniaSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.CaliforniaSongList;
    }


    /*
    *
    * Get table song VietKTV
    *
    * */

    public ArrayList<MySong> getVietKTVSongList(){

        this.VietKTVSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_VOL,Constants.DB_COLUMN_AUTHOR};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.DB_TABLE_VIETKTV,columns,null,null,null,null,null);

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.VietKTVSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.VietKTVSongList;
    }

    //**************Search theo the loai***********/
    public ArrayList<MySong> searchInAllTypeYoung(String TBSONG){
        this.ArirangSongList = new ArrayList<>();
        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_GENRE + " LIKE" + "'%" + "1"
                        + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    public ArrayList<MySong> searchInAllTypeRomantic(String TBSONG){
        this.ArirangSongList = new ArrayList<>();
        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_GENRE + " LIKE" + "'%" + "2"
                        + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    public ArrayList<MySong> searchInAllTypeRevolution(String TBSONG){
        this.ArirangSongList = new ArrayList<>();
        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_GENRE + " LIKE" + "'%" + "3"
                        + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    public ArrayList<MySong> searchInAllTypeTrinh(String TBSONG){
        this.ArirangSongList = new ArrayList<>();
        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_GENRE + " LIKE" + "'%" + "4"
                        + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    public ArrayList<MySong> searchInAllTypeChildren(String TBSONG){
        this.ArirangSongList = new ArrayList<>();
        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_GENRE + " LIKE" + "'%" + "5"
                        + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    /********Search theo ma so*****************/
    public ArrayList<MySong> searchInALLNumber(String TBSONG){
        this.ArirangSongList = new ArrayList<>();

        String [] columns = {
                Constants.DB_COLUMN_ID_SONG,
                Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,
                Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,
                Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_VOL,
                Constants.DB_COLUMN_LANGUAGE,
                Constants.DB_COLUMN_GENRE,
                Constants.DB_COLUMN_FAVAVORITE,
                Constants.DB_COLUMN_AUTHOR};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TBSONG,
                columns, Constants.DB_COLUMN_FAVAVORITE + " LIKE" + "'%" + "1"
                       + "%'", null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {

                MySong mySong = new MySong();

                mySong.setId(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_ID_SONG)));
                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongName2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG2)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongLyric2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG2)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));
                mySong.setSongGenre(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_GENRE)));
                mySong.setSongLanguage(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LANGUAGE)));
                mySong.setSongFavorite(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_FAVAVORITE)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }

    /*
    * Search in Arirang List
    * */

    public ArrayList<MySong> searchInArirangSongList(String keyword){

        this.ArirangSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

//        String  squery ="SELECT "
//                      + columns[0]+ " , " + columns[1] + " , "+columns[2]+
//                " , " + columns[3]+ " , " + columns[4] + " , "+columns[5]+ " , " + columns[6] +
//                " FROM " +Constants.DB_TABLE_ARIRANG + " WHERE " +  Constants.DB_COLUMN_NAME_SONG
//                + " LIKE '%"+keyword.toLowerCase()+"%'" + " OR ";
        //Log.d("AAAAAAAAAAAAAAAAAAAAAAA",keyword.toLowerCase());
        //Cursor cursor = db.rawQuery(squery,null);//query(Constants.DB_TABLE_ARIRANG,columns,null,null,null,null,null);

        Cursor cursor = db.query(true, Constants.DB_TABLE_ARIRANG,
                    columns, Constants.DB_COLUMN_NAME_SONG + " LIKE" + "'%" + keyword.toUpperCase()
                            + "%' OR " + Constants.DB_COLUMN_NAME_SONG2 + " LIKE" + "'%" + keyword.toLowerCase()
                            + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG + " LIKE" + "'%" + keyword.toLowerCase()
                            + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG2 + " LIKE" + "'%" + keyword.toLowerCase() + "%'",
                    null, null, null, null, String.valueOf(20));
        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.ArirangSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.ArirangSongList;
    }


    public MySong getAllSongInXXList(String keyword,String tableSong){

        MySong mySong = new MySong();

        String [] columns = {
                Constants.DB_COLUMN_ID_SONG,
                Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,
                Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,
                Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_VOL,
                Constants.DB_COLUMN_LANGUAGE,
                Constants.DB_COLUMN_GENRE,
                Constants.DB_COLUMN_FAVAVORITE,
                Constants.DB_COLUMN_AUTHOR};


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, tableSong,
                columns, Constants.DB_COLUMN_NUMBER_CODE_SONG + " LIKE" + "'%" + keyword.toUpperCase() + "%'",
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                mySong.setId(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_ID_SONG)));
                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongName2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG2)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongLyric2(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG2)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));
                mySong.setSongGenre(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_GENRE)));
                mySong.setSongLanguage(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LANGUAGE)));
                mySong.setSongFavorite(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_FAVAVORITE)));
            }
            while (cursor.moveToNext());
        }

        return mySong ;
    }


    public ArrayList<MySong> searchInMusicCoreSongList(String keyword){

        this.MusiccoreSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor  cursor = db.query(true, Constants.DB_TABLE_MUSICCORE,
                    columns, Constants.DB_COLUMN_NAME_SONG + " LIKE" + "'%" + keyword.toUpperCase()
                            + "%' OR " + Constants.DB_COLUMN_NAME_SONG2 + " LIKE" + "'%" + keyword.toLowerCase()
                            + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG + " LIKE" + "'%" + keyword.toLowerCase()
                            + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG2 + " LIKE" + "'%" + keyword.toLowerCase() + "%'",
                    null, null, null, null, String.valueOf(20));

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();
                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.MusiccoreSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.MusiccoreSongList;
    }

    public ArrayList<MySong> searchInCaliforniaSongList(String keyword){

        this.CaliforniaSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor  cursor = db.query(true, Constants.DB_TABLE_CALIFORNIA,
                columns, Constants.DB_COLUMN_NAME_SONG + " LIKE" + "'%" + keyword.toUpperCase()
                        + "%' OR " + Constants.DB_COLUMN_NAME_SONG2 + " LIKE" + "'%" + keyword.toLowerCase()
                        + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG + " LIKE" + "'%" + keyword.toLowerCase()
                        + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG2 + " LIKE" + "'%" + keyword.toLowerCase() + "%'",
                null, null, null, null, String.valueOf(20));

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.CaliforniaSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.CaliforniaSongList;
    }

    public ArrayList<MySong> searchInVietKTVSongList(String keyword){

        this.VietKTVSongList.clear();

        String [] columns = {Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor  cursor = db.query(true, Constants.DB_TABLE_VIETKTV,
                columns, Constants.DB_COLUMN_NAME_SONG + " LIKE" + "'%" + keyword.toUpperCase()
                        + "%' OR " + Constants.DB_COLUMN_NAME_SONG2 + " LIKE" + "'%" + keyword.toLowerCase()
                        + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG + " LIKE" + "'%" + keyword.toLowerCase()
                        + "%' OR " + Constants.DB_COLUMN_LYRIC_SONG2 + " LIKE" + "'%" + keyword.toLowerCase() + "%'",
                null, null, null, null, String.valueOf(20));

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                this.VietKTVSongList.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return this.VietKTVSongList;
    }

    /*
    * Update song in all list...
    */

    public  void updateSonginXXList(MySong newSong,String tableSong){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(Constants.DB_COLUMN_FAVAVORITE,newSong.getSongFavorite());
        values.put(Constants.DB_COLUMN_AUTHOR, newSong.getSongAuthor());
        values.put(Constants.DB_COLUMN_NUMBER_CODE_SONG, newSong.getNumberCode());
        values.put(Constants.DB_COLUMN_NAME_SONG,newSong.getSongName());
        values.put(Constants.DB_COLUMN_NAME_SONG2,newSong.getSongName2());
        values.put(Constants.DB_COLUMN_LYRIC_SONG2,newSong.getSongLyric2());
        values.put(Constants.DB_COLUMN_LYRIC_SONG,newSong.getSongLyric());
        values.put(Constants.DB_COLUMN_VOL,newSong.getSongVol());
        values.put(Constants.DB_COLUMN_LANGUAGE,newSong.getSongLanguage());
        values.put(Constants.DB_COLUMN_GENRE,newSong.getSongGenre());

        db.update(tableSong, values, Constants.DB_COLUMN_ID_SONG + " = " + newSong.getId(), null);

        db.close();

    }

    public ArrayList<MySong> getListFavorite(String table){

        ArrayList<MySong> lst = new ArrayList<>();
        String [] columns = {
                Constants.DB_COLUMN_NUMBER_CODE_SONG,
                Constants.DB_COLUMN_NAME_SONG,
                Constants.DB_COLUMN_NAME_SONG2,
                Constants.DB_COLUMN_LYRIC_SONG,
                Constants.DB_COLUMN_LYRIC_SONG2,
                Constants.DB_COLUMN_AUTHOR,
                Constants.DB_COLUMN_VOL};

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor  cursor = db.query(true,table,
                columns,"FAV LIKE'%1%'",null, null, null, null, null);

        if(cursor.moveToFirst()){

            do {

                MySong mySong = new MySong();

                mySong.setNumberCode(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NUMBER_CODE_SONG)));
                mySong.setSongName(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_NAME_SONG)));
                mySong.setSongLyric(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_LYRIC_SONG)));
                mySong.setSongVol(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_VOL)));
                mySong.setSongAuthor(cursor.getString(cursor.getColumnIndex(Constants.DB_COLUMN_AUTHOR)));

                lst.add(mySong);
            }
            while (cursor.moveToNext());
        }

        return lst;
    }


}
