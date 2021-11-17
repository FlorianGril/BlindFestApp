package com.example.blindfest;

public class Playlist {
    public String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean publica;

    public int nb_tracks;
    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public String link;
    public String picture;
    public String picture_small;
    public String picture_medium;
    public String picture_big;
    public String picture_xl;
    public String checksum;
    public String tracklist;
    public String creation_date;
    public String md5_image;
    public String picture_type;
    public User user;
    public String type;

    public Tracks tracks;
    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
