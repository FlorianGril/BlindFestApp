package com.example.blindfest

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE

class BuzzerActivity : AppCompatActivity() {
    private var seconds = 30
    private var seconds2 = 5
    private var running = true
    var nbequipe = 2
    var musique = "Coolio - Gangsta's Paradise (ft. LV)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buzzer)
        nbequipe = intent.getIntExtra("int_value", 2)
        var timerBuz = findViewById(R.id.timer2) as TextView
        timerBuz.setVisibility(View.GONE)
        nbBuzzers()
        runTimer()
    }


    fun onClickStart(view: View?) {
        running = true
    }

    fun reponse(){
        val intentFin = Intent(this, ReponseActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, musique)
        }
        startActivity(intentFin)
    }
    fun onClickFin(view: View) {
        reponse()
    }

    fun onClickStop(view: View?) {
        running = false
        runTimer2()

        var timerBuz = findViewById(R.id.timer2) as TextView

        timerBuz.setVisibility(View.VISIBLE)
    }

    private fun nbBuzzers(){
        var buz1 = findViewById(R.id.buz1) as Button
        var buz4 = findViewById(R.id.buz4) as Button
        if (nbequipe==2){
            buz1.setVisibility(View.GONE)
            buz4.setVisibility(View.GONE)
        }
        if (nbequipe==3){
            buz1.setVisibility(View.GONE)
        }
    }
    private fun runTimer() {
        var timer = findViewById(R.id.timer) as TextView
        var fin = findViewById(R.id.timerFin) as TextView
        fin.setVisibility(View.GONE)
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val secs = seconds % 60
                val time = secs.toString()
                timer.text = time
                if (running) {
                    seconds--
                }
                handler.postDelayed(this, 1000)
                if(seconds==9){
                    fin.setVisibility(View.VISIBLE)
                }
                if(seconds==-1){
                    reponse()
                    handler.removeCallbacks(this)
                }
            }

        })
        }

    private fun runTimer2() {
        var timerBuz = findViewById(R.id.timer2) as TextView
        var fin = findViewById(R.id.timerFin) as TextView
        val handlerBuz = Handler()
        handlerBuz.post(object : Runnable {
            override fun run() {
                val secs2 = seconds2 % 60
                val time2 = secs2.toString()
                timerBuz.text = time2
                seconds2--
                handlerBuz.postDelayed(this, 1000)
                if(seconds2==-1){
                    handlerBuz.removeCallbacks(this)
                    timerBuz.setVisibility(View.GONE)
                    seconds2=5
                    running=true
                    fin.setVisibility(View.VISIBLE)
                }
            }

        })
    }
    }
