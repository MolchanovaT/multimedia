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

import android.media.MediaPlayer
import android.os.Bundle
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

        val viewModel: AudioViewModel by viewModels()

        val adapter = AudioAdapter(object : OnInteractionListener {
            override fun onPlay(audio: Audio) {
                viewModel.playById(audio.id)
            }

            override fun onPause(audio: Audio) {
                viewModel.pauseById(audio.id)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { list ->
            adapter.submitList(list)
        }
    }
}

