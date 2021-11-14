package com.example.blindfest

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var nbequipe = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun choixequipe(view: View) {
        var but2=findViewById(R.id.equipe2) as Button
        var but3=findViewById(R.id.equipe3) as Button
        var but4=findViewById(R.id.equipe4) as Button
        but2.setTextColor(Color.WHITE)
        but3.setTextColor(Color.WHITE)
        but4.setTextColor(Color.WHITE)
        var button = view as Button
        var sah = button.getText().toString()
        nbequipe = sah.toInt()
        button.setTextColor(Color.RED)
    }


    fun jouer(view: View) {
        val intent = Intent(this, BuzzerActivity::class.java).apply {
            putExtra("int_value", nbequipe)
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