package com.vartmp7.stalker.GsonBeans;

public class TrackSignal {
    private boolean entered=false;
    private boolean authenticated=false;
    private long uid_number;
    private String username;
    private String surname;
    private String date_time;

    public TrackSignal() {
    }

    public TrackSignal(boolean en,boolean au, long uid, String user, String sur, String date) {
        entered = en;
        authenticated = au;
        uid_number = uid;
        username = user;
        surname = sur;
        date_time = date;
    }


    public long getUid_number() {
        return uid_number;
    }

    public void setUid_number(long uid_number) {
        this.uid_number = uid_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
