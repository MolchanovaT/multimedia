package ru.netology.nmedia.ui

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

        var mediaPlayer: MediaPlayer? = null
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        val viewModel: AudioViewModel by viewModels()

        val adapter = AudioAdapter(object : OnInteractionListener {
            override fun onPlay(audio: Audio) {

                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                }

                if (audio.isPlaying) {

                    viewModel.pauseById(audio.id)

                    Toast.makeText(
                        this@AppActivity,
                        "Audio has been paused",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                } else {
                    try {
                        mediaPlayer = MediaPlayer()

                        mediaPlayer?.setOnCompletionListener {
                            val playingNext: Audio = viewModel.getNext()

                            mediaPlayer?.release()
                            mediaPlayer = null
                            mediaPlayer = MediaPlayer()
                            mediaPlayer?.setDataSource(playingNext.songUrl)
                            mediaPlayer?.prepareAsync()
                            mediaPlayer?.setOnPreparedListener { mp ->
                                mp.start()
                            }

                            viewModel.playById(playingNext.id)
                        }

                        mediaPlayer?.setDataSource(audio.songUrl)
                        mediaPlayer?.prepareAsync()
                        mediaPlayer?.setOnPreparedListener { mp ->
                            mp.start()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    viewModel.playById(audio.id)

                    Toast.makeText(this@AppActivity, "Audio started playing..", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { list ->
            adapter.submitList(list)
        }
    }
}

