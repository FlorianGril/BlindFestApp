package com.example.blindfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.wear.activity.ConfirmationActivity

class ResultatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

    }

    fun accueil(view: View){
        val intentAcc = Intent(this, MainActivity::class.java)
        startActivity(intentAcc)
    }
}