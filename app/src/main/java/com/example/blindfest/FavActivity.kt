package com.example.blindfest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavActivity : AppCompatActivity(){
    private val musicList = ArrayList<MusicModel>()
    private lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        getSupportActionBar()?.hide()
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.regles->{
                    var intent: Intent = Intent(this, ReglesActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }
                R.id.jeu->{
                    var intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }

            }
            true
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        musicAdapter = MusicAdapter(musicList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = musicAdapter
        prepareMusicData()
    }


    private fun prepareMusicData() {
        var music = MusicModel("Coolio - Gangstas Paradise")
        musicList.add(music)
        val favoris = getSharedPreferences("Favoris", Context.MODE_PRIVATE)
        var nb_favori = favoris.getInt("nb_fav", 2)
        for (i in 1..nb_favori){
            var musique = favoris.getString("musique" + i, "non")
            if (musique != "non")
            {
                music = MusicModel(musique)
                musicList.add(music)
            }
        }

        musicAdapter.notifyDataSetChanged()
    }
}
