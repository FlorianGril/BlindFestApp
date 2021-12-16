package com.example.blindfest

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.wear.activity.ConfirmationActivity
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE
import java.util.ArrayList

class ResultatActivity : AppCompatActivity() {
    var nbmanche = 1
    var manche = 1
    var nbequipe = 2
    var playlist=""
    var listrandom = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)
        getSupportActionBar()?.hide()
        nbmanche = intent.getIntExtra("nb_manche", 1)
        manche = intent.getIntExtra("manche", 1)
        playlist = intent.getStringExtra("playlist").toString()
        listrandom= intent.getIntegerArrayListExtra("listrand") as ArrayList<Int>
        if (nbmanche == manche){
            var bouton = findViewById(R.id.accueil) as Button
            bouton.text = "Accueil"
        }

        classement()
        nbTeam()
    }
    override fun onBackPressed() {

    }


    fun classement(){
        val sharedPreferences = getSharedPreferences("Teams", Context.MODE_PRIVATE)
        nbequipe = sharedPreferences.getInt("nb_equipe", 2)
        var scorerouge = sharedPreferences.getInt("scorerouge", 2)
        var scorebleu = sharedPreferences.getInt("scorebleu", 2)
        var scorevert = sharedPreferences.getInt("scorevert", 1)
        var scorejaune = sharedPreferences.getInt("scorejaune", 0)
        var rouge = Team("Rouge", scorerouge)
        var bleu = Team("Bleue", scorebleu)
        var jaune = Team("Jaune", scorejaune)
        var vert = Team("Verte", scorevert)
        var classement = arrayOf(jaune, vert, rouge, bleu)
        classement.sort()
        var premier = findViewById(R.id.premier) as TextView
        var deuxieme = findViewById(R.id.deuxieme) as TextView
        var troisieme = findViewById(R.id.troisieme) as TextView
        var quatrieme =  findViewById(R.id.quatrieme) as TextView
        premier.text = classement[3].toString()
        deuxieme.text = classement[2].toString()
        troisieme.text = classement[1].toString()
        quatrieme.text = classement[0].toString()
    }

    private fun nbTeam(){
        var troisieme = findViewById(R.id.troisieme) as TextView
        var quatrieme =  findViewById(R.id.quatrieme) as TextView
        var trois = findViewById(R.id.trois) as TextView
        var quatre = findViewById(R.id.quatre) as TextView
        if (nbequipe==2){
            troisieme.setVisibility(View.GONE)
            quatrieme.setVisibility(View.GONE)
            trois.setVisibility(View.GONE)
            quatre.setVisibility(View.GONE)
        }
        if (nbequipe==3){
            quatrieme.setVisibility(View.GONE)
            quatre.setVisibility(View.GONE)
        }
    }

    fun accueil(view: View){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Voulez-vous vraiment retourner au menu principal?")

                .setCancelable(false)

                .setPositiveButton("Oui", DialogInterface.OnClickListener {
                    dialog, id -> val intentAcc = Intent(this, MainActivity::class.java)
                    startActivity(intentAcc)
                })

                .setNegativeButton("Non", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                })

        val alert = dialogBuilder.create()

        alert.show()
    }

    fun suite(view: View){
        if (nbmanche == manche){
            val intentAcc = Intent(this, MainActivity::class.java)
            startActivity(intentAcc)
        }
        else {
            manche += 1
            val intent = Intent(this, BuzzerActivity::class.java).apply {
                putExtra("nb_manche", nbmanche)
                putExtra("manche", manche)
                putExtra("playlist", playlist)
                putIntegerArrayListExtra("listrand", listrandom)
                //putExtra("nb_equipe", nbequipe)
            }
            startActivity(intent)
        }

    }
}

class Team(val nom: String, var points: Int) : Comparable<Team> {
    override fun compareTo(other: Team): Int = this.points.compareTo(other.points)
    override fun toString(): String = "L'équipe $nom avec $points points"
}