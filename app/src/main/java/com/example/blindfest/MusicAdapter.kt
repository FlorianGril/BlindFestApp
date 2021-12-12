package com.example.blindfest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class MusicAdapter(private var musicList: List<MusicModel>) :
        RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.titleFav)
        var year: TextView = view.findViewById(R.id.yearFav)
        var artist: TextView = view.findViewById(R.id.artistFav)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val music = musicList[position]
        holder.title.text = music.getTitle()
        holder.artist.text = music.getGenre()
        holder.year.text = music.getYear()
    }
    override fun getItemCount(): Int {
        return musicList.size
    }
}