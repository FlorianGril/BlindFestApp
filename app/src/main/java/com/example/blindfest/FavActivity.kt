package com.example.blindfest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavActivity : AppCompatActivity(){

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
    }

    fun accueil(view: View){
        val intentAcc = Intent(this, MainActivity::class.java)
        startActivity(intentAcc)
    }
}