package com.example.blindfest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE
import android.content.Intent
import android.view.View
import android.widget.TextView

class ReponseActivity : AppCompatActivity() {
    var point1 = 5
    var point2 = 3
    var nbmanche = 1
    var manche = 1
    var nbequipe = 2
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reponse)
        var reponse = intent.getStringExtra(EXTRA_MESSAGE)
        nbequipe = intent.getIntExtra("nb_equipe", 2)
        nbmanche = intent.getIntExtra("nb_manche", 1)
        manche = intent.getIntExtra("manche", 1)
        var rep = findViewById(R.id.reponse) as TextView
        rep.text = reponse
    }

    fun continuer(view: View){
        if (nbmanche == manche){
            val intent = Intent(this, ResultatActivity::class.java).apply {
                putExtra("point1", point1)
                putExtra("point2", point2)
            }
            startActivity(intent)
        }
        else {
            manche += 1
            val intent = Intent(this, BuzzerActivity::class.java).apply {
                putExtra("nb_manche", nbmanche)
                putExtra("manche", manche)
                putExtra("nb_equipe", nbequipe)
            }
            startActivity(intent)
        }

    }
    fun accueil(view: View){
        val intentAcc = Intent(this, MainActivity::class.java)
        startActivity(intentAcc)
    }
}