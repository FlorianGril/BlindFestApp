package com.example.blindfest

class MusicModel(title: String?, genre: String?, year: String?) {
    private var title: String
    private var artist: String
    private var year: String
    init {
        this.title = title!!
        this.artist = genre!!
        this.year = year!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getYear(): String? {
        return year
    }
    fun setYear(year: String?) {
        this.year = year!!
    }
    fun getGenre(): String? {
        return artist
    }
    fun setGenre(genre: String?) {
        this.artist = genre!!
    }
}