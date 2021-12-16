package com.example.blindfest

class MusicModel(title: String?) {
    private var title: String
    init {
        this.title = title!!
    }
    fun getTitle(): String? {
        return title
    }
}