package ru.netology.nmedia.ui

/*import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    private val mediaObserver = MediaLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(mediaObserver)

        findViewById<Button>(R.id.play).setOnClickListener {
            mediaObserver.apply {
                player?.setDataSource(
                    "https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/1.mp3"
                )
            }.play()
        }
    }
}*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R

class AppActivity : AppCompatActivity() {
    lateinit var audioList: RecyclerView
    lateinit var adapter: AudioAdapter
    var list = ArrayList<AudioModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        audioList = findViewById(R.id.list)
        audioList.layoutManager = LinearLayoutManager(this)
        loadTracks()
    }

    fun loadTracks() {
        list.clear()
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/1.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/2.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/3.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/4.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/5.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/6.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/7.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/8.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/9.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/10.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/11.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/12.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/13.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/14.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/15.mp3"))
        list.add(AudioModel("https://raw.githubusercontent.com/netology-code/andad-homeworks/master/09_multimedia/data/16.mp3"))

        adapter = AudioAdapter(this, list)
        audioList.adapter = adapter
    }
}

