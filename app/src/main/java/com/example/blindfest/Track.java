package com.example.blindfest;

public class Track {
    public int id;
    public boolean readable;
    public String title;
    public String title_short;
    public String title_version;
    public String link;
    public int duration;
    public int rank;
    public boolean explicit_lyrics;
    public int explicit_content_lyrics;
    public int explicit_content_cover;
    public String preview;
    public String md5_image;
    public int time_add;
    public Artist artist;
    public Album album;
    public String type;

    public String getTitle() {
        return title;
    }

    public Artist getArtist() { return artist; }

    public String getPreview() {
        return preview;
    }
}
