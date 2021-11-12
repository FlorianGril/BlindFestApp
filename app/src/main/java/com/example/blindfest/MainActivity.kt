package com.example.blindfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun jouer(view: View){
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
                        Toast.makeText(this,"MARC", Toast.LENGTH_LONG).show()
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
        }
    }
}