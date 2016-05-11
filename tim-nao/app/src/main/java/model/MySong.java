package model;

import java.io.Serializable;

/**
 * Created by Thien Phuoc on 2/4/2016.
 */
public class MySong implements Serializable {

    String id ;
    String numberCode;
    String songName;
    String songName2;
    String songLyric;
    String songLyric2;
    String songAuthor;
    String songLanguage;
    String songVol;
    String songFavorite;
    String songGenre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongName2() {
        return songName2;
    }

    public void setSongName2(String songName2) {
        this.songName2 = songName2;
    }

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric;
    }

    public String getSongLyric2() {
        return songLyric2;
    }

    public void setSongLyric2(String songLyric2) {
        this.songLyric2 = songLyric2;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongLanguage() {
        return songLanguage;
    }

    public void setSongLanguage(String songLanguage) {
        this.songLanguage = songLanguage;
    }

    public String getSongVol() {
        return songVol;
    }

    public void setSongVol(String songVol) {
        this.songVol = songVol;
    }

    public String getSongFavorite() {
        return songFavorite;
    }

    public void setSongFavorite(String songFavorite) {
        this.songFavorite = songFavorite;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }
}
