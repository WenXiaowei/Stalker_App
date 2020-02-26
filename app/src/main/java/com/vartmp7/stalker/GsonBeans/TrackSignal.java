package com.vartmp7.stalker.GsonBeans;

public class TrackSignal {
    public boolean isEntered() {
        return entered;
    }

    public TrackSignal setEntered(boolean entered) {
        this.entered = entered;
        return this;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public TrackSignal setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        return this;
    }

    public long getUid_number() {
        return uid_number;
    }

    public TrackSignal setUid_number(long uid_number) {
        this.uid_number = uid_number;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TrackSignal setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public TrackSignal setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getDate_time() {
        return date_time;
    }

    public TrackSignal setDate_time(String date_time) {
        this.date_time = date_time;
        return this;
    }
    private boolean entered=false;
    private boolean authenticated=false;
    private long uid_number;
    private String username;
    private String surname;
    private String date_time;
    private long idPlace;

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


    public long getIdPlace() {
        return idPlace;
    }

    public TrackSignal setIdPlace(long idPlace) {
        this.idPlace = idPlace;
        return this;
    }
}
