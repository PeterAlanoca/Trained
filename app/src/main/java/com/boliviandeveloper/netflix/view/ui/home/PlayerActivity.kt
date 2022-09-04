package com.boliviandeveloper.netflix.view.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.boliviandeveloper.netflix.databinding.ActivityPlayerBinding
import com.boliviandeveloper.netflix.model.entity.Episode
import com.bumptech.glide.Glide

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var player: ExoPlayer

    private var episode: Episode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(applicationContext).build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        val bundle = intent.extras
        if (bundle != null) {
            episode = bundle.getSerializable(EPISODE_KEY) as Episode
        }

        episode?.let { episode ->

            val mediaItem = MediaItem.fromUri(episode.videoUrl)
            binding.playerView.player = player
            player.setMediaItem(mediaItem)
            player.playWhenReady = true
            player.prepare()

            player.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (isPlaying) {
                        binding.playerView.visibility = View.VISIBLE
                        binding.loadingPlayerView.visibility = View.GONE
                        binding.playerView.useController = true
                    }
                }
            })

            Glide
                .with(this)
                .load(episode.thumbnailUrl)
                .centerCrop()
                .into(binding.thumbnailImageView)

        }
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }

    companion object {
        const val EPISODE_KEY = "EPISODE_KEY"
    }
}