package com.example.blindfest

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BuzzerActivity : AppCompatActivity() {
    private var seconds = 30
    private var running = false
    private var fin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buzzer)
        runTimer()
    }


    fun onClickStart(view: View?) {
        running = true
    }

    fun onClickStop(view: View?) {
        running = false
        var fin = findViewById(R.id.timerFin) as TextView
        fin.setVisibility(View.VISIBLE)
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
                    handler.removeCallbacks(this)
                }
            }

        })
        }
    }
