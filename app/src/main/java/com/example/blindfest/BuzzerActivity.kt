package com.example.blindfest

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.wear.activity.ConfirmationActivity.EXTRA_MESSAGE
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class BuzzerActivity : AppCompatActivity() {
    private var seconds = 30
    private var seconds2 = 5
    private var running = true
    var nbequipe = 2
    var nbmanche = 1
    var manche = 1
    var playlist = ""
    var musique = "Coolio - Gangsta's Paradise (ft. LV)"
    var finTimer = false
    lateinit var tracks : List<Track>

    private var music: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buzzer)
        getSupportActionBar()?.hide()
        //nbequipe = intent.getIntExtra("nb_equipe", 2)
        val sharedPreferences = getSharedPreferences("Teams", Context.MODE_PRIVATE)
        nbequipe = sharedPreferences.getInt("nb_equipe", 2)
        nbmanche = intent.getIntExtra("nb_manche", 1)
        manche = intent.getIntExtra("manche", 1)
        playlist = intent.getStringExtra("playlist").toString()
        //Toast.makeText(this, playlist, Toast.LENGTH_LONG).show()
        var timerBuz = findViewById(R.id.timer2) as TextView
        var equipeBuz = findViewById(R.id.equipebuz) as TextView
        timerBuz.setVisibility(View.GONE)
        equipeBuz.setVisibility(View.GONE)
        nbBuzzers()


        val idPlay = searchMusic(playlist)
        //val idPlay = "1996494362"
        Log.d("PLAYLIST CODE", "" + idPlay)

        val musiques = searchTracks(idPlay)

        //val musiques = arrayOf("https://cdns-preview-7.dzcdn.net/stream/c-795a4ca42a97994b436f12110652fab3-3.mp3", "aaa")
        Log.d("Test Music", "" + musiques[manche - 1])

        music.setDataSource(musiques[manche - 1])
        //music = MediaPlayer.create(applicationContext, R.raw.gangsta)
        music.prepare()
        music.start()
        runTimer()
    }

    override fun onBackPressed() {

    }

    fun reponse(){
        val intentFin = Intent(this, ReponseActivity::class.java).apply {
            putExtra("musique", musique)
            putExtra("playlist", playlist)
            putExtra("manche", manche)
            //putExtra("nb_equipe", nbequipe)
            putExtra("nb_manche", nbmanche)
        }
        startActivity(intentFin)
    }
    fun onClickFin(view: View) {
        music.stop()
        music.reset()
        reponse()
        finTimer = true
    }

    fun onClickStop(view: View?) {
        var buz1 = findViewById(R.id.buzjaune) as Button
        var buz2 = findViewById(R.id.buzbleu) as Button
        var buz3 = findViewById(R.id.buzrouge) as Button
        var buz4 = findViewById(R.id.buzvert) as Button
        var fin = findViewById(R.id.timerFin) as TextView
        buz1.setVisibility(View.GONE)
        buz2.setVisibility(View.GONE)
        buz3.setVisibility(View.GONE)
        buz4.setVisibility(View.GONE)
        fin.setVisibility(View.GONE)
        running = false
        music.pause()
        runTimer2()
        var button = view as Button
        var timerBuz = findViewById(R.id.timer2) as TextView
        timerBuz.setVisibility(View.VISIBLE)
        var equipeBuz = findViewById(R.id.equipebuz) as TextView
        equipeBuz.setVisibility(View.VISIBLE)

        var buttonDrawable = equipeBuz.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        if (button.getId()== R.id.buzjaune) {
            DrawableCompat.setTint(buttonDrawable, Color.rgb(255, 235, 59))
        }
        if (button.getId()== R.id.buzbleu) {
            DrawableCompat.setTint(buttonDrawable, Color.rgb(0, 217, 255))
        }
        if (button.getId()== R.id.buzrouge) {
            DrawableCompat.setTint(buttonDrawable, Color.rgb(255, 0, 0))
        }
        if (button.getId()== R.id.buzvert) {
            DrawableCompat.setTint(buttonDrawable, Color.rgb(133, 244, 4))
        }
        equipeBuz.background = buttonDrawable
    }

    private fun nbBuzzers(){
        var buz1 = findViewById(R.id.buzjaune) as Button
        var buz4 = findViewById(R.id.buzvert) as Button
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
                if (finTimer) {
                    handler.removeCallbacks(this)
                }
                if (seconds == 19) {
                    fin.setVisibility(View.VISIBLE)
                }
                if (seconds == -1) {
                    music.stop()
                    music.reset()
                    reponse()
                    handler.removeCallbacks(this)
                }
            }

        })
        }

    private fun runTimer2() {
        var timerBuz = findViewById(R.id.timer2) as TextView
        var equipeBuz = findViewById(R.id.equipebuz) as TextView
        var fin = findViewById(R.id.timerFin) as TextView
        var buz1 = findViewById(R.id.buzjaune) as Button
        var buz2 = findViewById(R.id.buzbleu) as Button
        var buz3 = findViewById(R.id.buzrouge) as Button
        var buz4 = findViewById(R.id.buzvert) as Button
        val handlerBuz = Handler()
        handlerBuz.post(object : Runnable {
            override fun run() {
                val secs2 = seconds2 % 60
                val time2 = secs2.toString()
                timerBuz.text = time2
                seconds2--
                handlerBuz.postDelayed(this, 1000)
                if (seconds2 == -1) {
                    handlerBuz.removeCallbacks(this)
                    timerBuz.setVisibility(View.GONE)
                    equipeBuz.setVisibility(View.GONE)
                    buz1.setVisibility(View.VISIBLE)
                    buz2.setVisibility(View.VISIBLE)
                    buz3.setVisibility(View.VISIBLE)
                    buz4.setVisibility(View.VISIBLE)
                    seconds2 = 5
                    music.start()
                    running = true
                    fin.setVisibility(View.VISIBLE)
                    nbBuzzers()
                }
            }

        })
    }

    private fun searchMusic(playlist: String) : String {
        val client = OkHttpClient()
        val url = "https://api.deezer.com/search/playlist/?q=$playlist/"
        val request = Request.Builder().url(url).build()
        var playlistId : String = "marc"

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val rep = response.body()!!.string()

                    val json = JSONObject(rep)

                    Log.d("AA", "JSON ::${json.toString()}")

                    val gson = Gson()
                    var plays = gson.fromJson(json.toString(), DataPlaylist::class.java)

                    var playlists: List<Playlist>
                    playlists = plays.getPlaylists()

                    var numPlay = 9999;

                    if (playlists.size > 0) {
                        Log.d("KIKI", "" + playlists[0].getTitle())
                        for (i in 0..playlists.size - 1) {
                            if (playlists[i].getNb_tracks() > nbmanche && numPlay == 9999) {
                                numPlay = i
                            }
                        }
                    }

                    Log.d("NUMERO PLAY", "" + numPlay)

                    var playlistChoisi = playlists[numPlay]

                    playlistId = playlistChoisi.getId()
                    Log.d("111-ID CHOISI-111", "" + playlistId)

                }

                override fun onFailure(call: Call, e: IOException) {
                    println("API execute failed")

                }
            })
            Log.d("ID CHOISI", "" + playlistId)
        Thread.sleep(1_000)
        return playlistId
    }

    private fun searchTracks(playlist: String): Array<String> {
        val client = OkHttpClient()
        val url = "https://api.deezer.com/playlist/$playlist/"

        Log.d("AA", "URL ::$url")
        val request = Request.Builder().url(url).build()
        var previews: Array<String> = Array(nbmanche) {"a"}

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val rep = response.body()!!.string()

                    val json = JSONObject(rep)

                    Log.d("AA", "JSON ::${json.toString()}")

                    val gson = Gson()
                    var play = gson.fromJson(json.toString(), Playlist::class.java)

                    var tracks: List<Track>
                    var pre = play.getTracks()
                    Log.d("pre", "" + pre.getData())
                    tracks = pre.getData()

                    if (tracks.size > 0) {
                        Log.d("KIKI", "" + tracks[0].getTitle())
                        for (i in 0..nbmanche - 1) {
                            previews[i] = tracks[i].getPreview()
                        }
                    }

                    Log.d("Musique 1 preview", "" + previews[0])

                }

                override fun onFailure(call: Call, e: IOException) {
                    println("API execute failed")
                }
            })
        Log.d("preview 1 :", "" + previews[0])
        Thread.sleep(1_000)
        return previews
    }
}
