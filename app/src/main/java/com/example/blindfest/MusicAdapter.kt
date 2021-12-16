package com.example.blindfest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Browser
import android.provider.Browser.EXTRA_CREATE_NEW_TAB
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import androidx.core.content.ContextCompat.startActivity

import java.net.URI.create

internal class MusicAdapter(private var musicList: List<MusicModel>) :
        RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.titleFav)

        var yt: Button = view.findViewById(R.id.yt)
        /*fun bind(property: MusicModel, index: Int) {
            val suppr = view.findViewById<Button>(R.id.button)
            suppr.setOnClickListener{deleteItem(index)}
        }*/
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
        //holder.bind(musicList[position], position)
        //var titre =  holder.title.text.toString()
        /*holder.yt.setOnClickListener {
            fun onClick(v: View){
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + titre))
                BuzzerActivity.startActivity(intent)
            }
            openYoutube(holder.title.text as String)
        }*/
    }
    override fun getItemCount(): Int {
        return musicList.size
    }

    /*fun openYoutube(title: String){
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+title))
        BuzzerActivity.this.startActivity(intent)
    }

    fun deleteItem(index: Int){
        val favoris = getSharedPreferences("Favoris", Context.MODE_PRIVATE)
        var nb_favori = favoris.getInt("nb_fav", 2)
        notifyDataSetChanged()
    }*/
}