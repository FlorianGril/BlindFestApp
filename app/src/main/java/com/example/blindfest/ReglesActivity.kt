package com.example.blindfest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReglesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regles)
        getSupportActionBar()?.hide()
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.jeu -> {
                    var intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }
                R.id.favoris -> {
                    var intent: Intent = Intent(this, FavActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }

            }
            true
        }
    }
}