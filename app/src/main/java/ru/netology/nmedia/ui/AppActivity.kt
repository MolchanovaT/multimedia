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

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.AudioAdapter
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.databinding.ActivityAppBinding
import ru.netology.nmedia.viewmodel.AudioViewModel

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var mediaPlayer: MediaPlayer

        val viewModel: AudioViewModel by viewModels()

        val adapter = AudioAdapter(object : OnInteractionListener {
            override fun onPlay(audio: Audio) {

                mediaPlayer = MediaPlayer()

                if (audio.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.release()

                    viewModel.pauseById(audio.id)

                    Toast.makeText(
                        this@AppActivity,
                        "Audio has been paused",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                } else {

                    mediaPlayer.setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )

                    try {
                        mediaPlayer.setDataSource(audio.songUrl)
                        mediaPlayer.prepareAsync()
                        mediaPlayer.setOnPreparedListener { mp ->
                            mp.start()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Toast.makeText(this@AppActivity, "Audio started playing..", Toast.LENGTH_SHORT)
                        .show()
                }

                viewModel.playById(audio.id)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { list ->
            adapter.submitList(list)
        }
    }
}

