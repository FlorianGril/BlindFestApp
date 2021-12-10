package com.example.blindfest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.wear.activity.ConfirmationActivity
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var nbequipe = 2
    var nbmanche = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        var manche1=findViewById(R.id.manche1) as Button
        manche1.setVisibility(View.GONE)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.regles->{
                    var intent: Intent = Intent(this, ReglesActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }
                R.id.favoris->{
                    var intent: Intent = Intent(this, FavActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.no_transition, R.anim.no_transition)
                }

            }
            true
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "ouiiii", Toast.LENGTH_LONG).show()

        when (item.itemId) {

            R.id.regles-> {
                //var intent: Intent = Intent(this, QRCodeEncoder::class.java)
                //startActivity(intent)
                return true
            }
            R.id.favoris -> {
                Toast.makeText(this, "fav", Toast.LENGTH_LONG).show()
                var intent: Intent = Intent(this, FavActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return true
    }


    fun choixequipe(view: View) {
        var but2=findViewById(R.id.equipe2) as Button
        var but3=findViewById(R.id.equipe3) as Button
        var but4=findViewById(R.id.equipe4) as Button
        but2.setTextColor(Color.WHITE)
        but3.setTextColor(Color.WHITE)
        but4.setTextColor(Color.WHITE)
        var button = view as Button
        var choixequipe = button.getText().toString()
        nbequipe = choixequipe.toInt()
        button.setTextColor(Color.RED)
    }

    fun choixmanche(view: View) {
        var manche5=findViewById(R.id.manche5) as Button
        var manche10=findViewById(R.id.manche10) as Button
        var manche30=findViewById(R.id.manche30) as Button
        manche5.setTextColor(Color.WHITE)
        manche10.setTextColor(Color.WHITE)
        manche30.setTextColor(Color.WHITE)
        var buttonManche = view as Button
        var choixmanche = buttonManche.getText().toString()
        nbmanche = choixmanche.toInt()
        buttonManche.setTextColor(Color.RED)
    }

    fun choixOs(view:View){
        var manche1=findViewById(R.id.manche1) as Button
        var manche5=findViewById(R.id.manche5) as Button
        var manche10=findViewById(R.id.manche10) as Button
        var manche30=findViewById(R.id.manche30) as Button
        manche1.setVisibility(View.VISIBLE)
        manche5.setVisibility(View.GONE)
        manche10.setVisibility(View.GONE)
        manche30.setVisibility(View.GONE)
        var os =findViewById(R.id.os) as Button
        var party=findViewById(R.id.party) as Button
        os.setTextColor(Color.WHITE)
        party.setTextColor(Color.WHITE)
        var buttonMode = view as Button
        buttonMode.setTextColor(Color.RED)
        nbmanche=1
    }

    fun choixParty(view: View){
        var manche1=findViewById(R.id.manche1) as Button
        var manche5=findViewById(R.id.manche5) as Button
        var manche10=findViewById(R.id.manche10) as Button
        var manche30=findViewById(R.id.manche30) as Button
        manche1.setVisibility(View.GONE)
        manche5.setVisibility(View.VISIBLE)
        manche10.setVisibility(View.VISIBLE)
        manche30.setVisibility(View.VISIBLE)
        var os =findViewById(R.id.os) as Button
        var party=findViewById(R.id.party) as Button
        os.setTextColor(Color.WHITE)
        party.setTextColor(Color.WHITE)
        var buttonMode = view as Button
        buttonMode.setTextColor(Color.RED)
    }

    fun jouer(view: View) {
        val sharedPreferences = getSharedPreferences("Teams", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("nb_equipe", nbequipe)
        editor.putInt("scorerouge", 0)
        editor.putInt("scorebleu", 0)
        editor.putInt("scorejaune", 0)
        editor.putInt("scorevert", 0)
        editor.apply()
        var playlistspinner= findViewById<View>(R.id.playlist) as Spinner
        var playlist: String = playlistspinner.getSelectedItem().toString()
        val intent = Intent(this, BuzzerActivity::class.java).apply {
            //putExtra("nb_equipe", nbequipe)
            putExtra("nb_manche", nbmanche)
            putExtra(EXTRA_MESSAGE, playlist)
        }
        startActivity(intent)
    }


    /*fun jouer(view: View){
        val radio: RadioButton = findViewById(R.id.radioButton2)

        Toast.makeText(applicationContext,"On click : ${radio.text}", Toast.LENGTH_LONG).show()

        Toast.makeText(this@MainActivity,"MARC", Toast.LENGTH_LONG).show()

        //setContentView(R.layout.activity_regle);
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioButton2 ->
                    if (checked) {
                        Toast.makeText(this, "MARC", Toast.LENGTH_LONG).show()
                    }
                R.id.radioButton3 ->
                    if (checked) {
                        // Ninjas rule
                    }
                R.id.radioButton4 ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }*/

}


