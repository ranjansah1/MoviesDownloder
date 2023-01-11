package com.god.moviesdownloder_god;

public class FilmSimple {
    private  String FIlmimg,FIlmDate,FilmDubbed,FilmName,FilmID;


    public FilmSimple(String FIlmimg, String FIlmDate, String filmDubbed, String filmName,String filmID) {
        this.FIlmimg = FIlmimg;
        this.FIlmDate = FIlmDate;
        FilmDubbed = filmDubbed;
        FilmName = filmName;
        FilmID= filmID;
    }

    public FilmSimple(){

    }

    public String getFIlmimg() {
        return FIlmimg;
    }

    public void setFIlmimg(String FIlmimg) {
        this.FIlmimg = FIlmimg;
    }

    public String getFIlmDate() {
        return FIlmDate;
    }

    public void setFIlmDate(String FIlmDate) {
        this.FIlmDate = FIlmDate;
    }

    public String getFilmDubbed() {
        return FilmDubbed;
    }

    public void setFilmDubbed(String filmDubbed) {
        FilmDubbed = filmDubbed;
    }

    public String getFilmName() {
        return FilmName;
    }

    public void setFilmName(String filmName) {
        FilmName = filmName;
    }

    public String getFilmID() {
        return FilmID;
    }

    public void setFilmID(String filmID) {
        FilmID = filmID;
    }
}
