package com.example.android.popularmoviesstage_1;

public class Movies {

    private String TILTE;
    private  String POSTER;
    private String DATE;
    private  String  RATE;
    private String  OVERVIEW;


    public Movies(){}


    public String getTILTE() {
        return TILTE;
    }

    public void setTILTE(String TILTE) {
        this.TILTE = TILTE;
    }

    public String getPOSTER() {
        return POSTER;
    }

    public void setPOSTER(String POSTER) {
        this.POSTER = POSTER;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getOVERVIEW() {
        return OVERVIEW;
    }

    public void setOVERVIEW(String OVERVIEW) {
        this.OVERVIEW = OVERVIEW;
    }

    public Movies(String TILTE , String POSTER, String DATE, String  RATE, String  OVERVIEW)
    {
        this.TILTE = TILTE;
        this.POSTER = POSTER;
        this.DATE = DATE;
        this.RATE = RATE;
        this.OVERVIEW = OVERVIEW;
    }
}


