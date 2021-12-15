package com.example.blindfest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class ReponseActivity : AppCompatActivity() {
    var nbmanche = 1
    var manche = 1
    var nbequipe = 2
    var ptbleu = 0
    var ptjaune = 0
    var ptrouge = 0
    var ptvert = 0
    var reponse = ""
    var playlist =""
    var listrandom = ArrayList<Int>()

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reponse)
        getSupportActionBar()?.hide()
        reponse = intent.getStringExtra("musique").toString()
        val sharedPreferences = getSharedPreferences("Teams", Context.MODE_PRIVATE)
        nbequipe = sharedPreferences.getInt("nb_equipe", 2)
        //nbequipe = intent.getIntExtra("nb_equipe", 2)
        nbmanche = intent.getIntExtra("nb_manche", 1)
        manche = intent.getIntExtra("manche", 1)
        playlist = intent.getStringExtra("playlist").toString()
        listrandom= intent.getIntegerArrayListExtra("listrand") as ArrayList<Int>

        var rep = findViewById(R.id.reponse) as TextView
        rep.text = reponse
        var affmanche = findViewById(R.id.affmanche) as TextView
        affmanche.text = "Manche $manche/$nbmanche"
        nbBuzzersPoints()
    }

    

    private fun nbBuzzersPoints(){
        var buzjm = findViewById(R.id.buzjaunem) as Button
        var buzjp = findViewById(R.id.buzjaunep) as Button
        var buzjs = findViewById(R.id.scorejaune) as Button
        var buzvm = findViewById(R.id.buzvertm) as Button
        var buzvp = findViewById(R.id.buzvertp) as Button
        var buzvs = findViewById(R.id.scorevert) as Button
        if (nbequipe==2){
            buzjm.setVisibility(View.GONE)
            buzjp.setVisibility(View.GONE)
            buzjs.setVisibility(View.GONE)
            buzvm.setVisibility(View.GONE)
            buzvp.setVisibility(View.GONE)
            buzvs.setVisibility(View.GONE)
        }
        if (nbequipe==3){
            buzjm.setVisibility(View.GONE)
            buzjp.setVisibility(View.GONE)
            buzjs.setVisibility(View.GONE)
        }
    }

    fun continuer(view: View){

        val sharedPreferences = getSharedPreferences("Teams", Context.MODE_PRIVATE)
        var scorebleu = sharedPreferences.getInt("scorebleu", 0) + ptbleu
        var scorerouge = sharedPreferences.getInt("scorerouge", 0) + ptrouge
        var scorejaune = sharedPreferences.getInt("scorejaune", 0) + ptjaune
        var scortvert = sharedPreferences.getInt("scorevert", 0) + ptvert
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("scorerouge", scorerouge)
        editor.putInt("scorebleu", scorebleu)
        editor.putInt("scorejaune", scorejaune)
        editor.putInt("scorevert", scortvert)
        editor.apply()
        val intent = Intent(this, ResultatActivity::class.java).apply {
            putExtra("nb_manche", nbmanche)
            putExtra("manche", manche)
            putExtra("playlist", playlist)
            putIntegerArrayListExtra("listrand", listrandom)
        }
        startActivity(intent)

    }
    fun addfav(view: View){
        val favoris = getSharedPreferences("Favoris", Context.MODE_PRIVATE)
        var nb_fav = favoris.getInt("nb_fav", 0)
        nb_fav +=1
        val editor: SharedPreferences.Editor = favoris.edit()
        editor.putInt("nb_fav", nb_fav)
        editor.putString("musique" + nb_fav, reponse)
        editor.apply()
        Toast.makeText(this, "Ajouté aux favoris", Toast.LENGTH_LONG).show()
    }

    fun accueil(view: View){
        val intentAcc = Intent(this, MainActivity::class.java)
        startActivity(intentAcc)
    }

    fun moins(view: View){
        if (view.getId()== R.id.buzjaunem)
        {
            ptjaune -= 1
            var scorejaune = findViewById(R.id.scorejaune) as TextView
            scorejaune.setText(ptjaune.toString())
        }
        if (view.getId()== R.id.buzrougem)
        {
            ptrouge -= 1
            var scorerouge = findViewById(R.id.scorerouge) as TextView
            scorerouge.setText(ptrouge.toString())
        }
        if (view.getId()== R.id.buzbleum)
        {
            ptbleu -= 1
            var scorebleu = findViewById(R.id.scorebleu) as TextView
            scorebleu.setText(ptbleu.toString())
        }
        if (view.getId()== R.id.buzvertm)
        {
            ptvert -= 1
            var scorevert = findViewById(R.id.scorevert) as TextView
            scorevert.setText(ptvert.toString())
        }
    }

    fun plus(view: View){
        if (view.getId()== R.id.buzjaunep)
        {
            ptjaune += 1
            var scorejaune = findViewById(R.id.scorejaune) as TextView
            scorejaune.setText(ptjaune.toString())
        }
        if (view.getId()== R.id.buzrougep)
        {
            ptrouge += 1
            var scorerouge = findViewById(R.id.scorerouge) as TextView
            scorerouge.setText(ptrouge.toString())
        }
        if (view.getId()== R.id.buzbleup)
        {
            ptbleu += 1
            var scorebleu = findViewById(R.id.scorebleu) as TextView
            scorebleu.setText(ptbleu.toString())
        }
        if (view.getId()== R.id.buzvertp)
        {
            ptvert += 1
            var scorevert = findViewById(R.id.scorevert) as TextView
            scorevert.setText(ptvert.toString())
        }
    }

    override fun onBackPressed() {

    }
}