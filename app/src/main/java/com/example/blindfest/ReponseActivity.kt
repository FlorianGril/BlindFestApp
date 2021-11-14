package com.example.blindfest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE
import android.content.Intent
import android.widget.TextView

class ReponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reponse)
        var reponse = intent.getStringExtra(EXTRA_MESSAGE)
        var rep = findViewById(R.id.reponse) as TextView
        rep.text = reponse
    }
}