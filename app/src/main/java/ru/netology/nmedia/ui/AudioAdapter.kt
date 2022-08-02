package ru.netology.nmedia.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R

class AudioAdapter(val requireContext: Context, private val audioList: ArrayList<AudioModel>) :
    RecyclerView.Adapter<AudioAdapter.ViewHolder>() {
    lateinit var mediaPlayer: MediaPlayer

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val btnplay = itemView.findViewById<TextView>(R.id.btnPlay)
        val icon = itemView.findViewById<ImageView>(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(requireContext).inflate(R.layout.card_audio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audio = audioList[position]
        val no = position + 1
        holder.title.setText("Song " + no)
        holder.btnplay.setOnClickListener {
            val audioUrl = audio.songUrl
            if (audioUrl != null) {
                if (holder.btnplay.text.equals("Play")) {
                    playAudio(audioUrl)
                    holder.btnplay.text = "Pause"
                    holder.icon.setBackgroundResource(R.drawable.pause)
                } else {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.release()
                    Toast.makeText(requireContext, "Audio has been paused", Toast.LENGTH_SHORT)
                        .show()
                    holder.btnplay.text = "Play"
                    holder.icon.setBackgroundResource(R.drawable.play)
                }
            }
        }
    }

    override fun getItemCount() = audioList.size

    private fun playAudio(audioUrl: String) {

        mediaPlayer = MediaPlayer()

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(requireContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }
}