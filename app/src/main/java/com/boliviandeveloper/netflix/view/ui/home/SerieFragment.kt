package com.boliviandeveloper.netflix.view.ui.home

import android.graphics.Outline
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewOutlineProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.FragmentSerieBinding
import com.boliviandeveloper.netflix.helper.extesion.back
import com.boliviandeveloper.netflix.helper.extesion.hideMenuBottom
import com.boliviandeveloper.netflix.helper.extesion.showMenuBottom
import com.boliviandeveloper.netflix.model.entity.Serie
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.view.adapter.SeriePagerAdapter
import com.boliviandeveloper.netflix.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

class SerieFragment: Fragment(R.layout.fragment_serie) {

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var binding: FragmentSerieBinding
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSerieBinding.bind(view)
        player = ExoPlayer.Builder(requireContext()).build()

        binding.closeView.setOnClickListener {
            back()
        }
        viewModel.serie?.let { serie ->
            getSerie(serie.id)
        }
    }

    private fun getSerie(id: Int) {
        viewModel.getSerie(id)
            .observe(viewLifecycleOwner) { resource ->
                when(resource.status) {
                    Resource.Status.SUCCESS -> {
                        resource.data?.let { data ->
                            setUpUI(data.serie)
                        }
                    }
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.ERROR -> {

                    }
                }
            }
    }

    private fun setUpUI(serie: Serie) {
        viewModel.serie = serie
        val mediaItem = MediaItem.fromUri(serie.trailerUrl)
        binding.playerView.player = player
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()
        player.volume = 0f
        binding.volumeView.setOnClickListener {
            if (player.volume == 1f) {
                binding.volumeImageView.setImageResource(R.drawable.ic_volume_off_48dp)
                player.volume = 0f
            } else {
                binding.volumeImageView.setImageResource(R.drawable.ic_volume_up_48dp)
                player.volume = 1f
            }
        }

        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    binding.playerView.visibility = View.VISIBLE
                    binding.loadingPlayerView.visibility = View.GONE
                    binding.playerView.useController = true
                }
            }
        })

        binding.contentPlayerView.outlineProvider = object : ViewOutlineProvider() {
            val cornerRadius = 70f
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.let { o ->
                    view?.let { v ->
                        o.setRoundRect(0, 0, v.width, (v.height + cornerRadius).toInt(), cornerRadius)
                    }
                }
            }
        }
        binding.contentPlayerView.clipToOutline = true

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                val progress = player.currentPosition.toFloat().div(player.duration.toFloat()).times(100).toInt()
                binding.linearProgress.progress = progress
                handler.postDelayed(this, 1000)
            }
        }, 1000)

        Glide
            .with(this)
            .load(serie.trailerThumbnailUrl)
            .centerCrop()
            .into(binding.thumbnailImageView)

        binding.nameTextView.text = serie.name
        binding.coincidenceTextView.text = serie.coincidence
        binding.yearTextView.text = serie.year
        binding.ratingTextView.text = serie.rating
        binding.seasonsCountTextView.text = serie.seasonsCount
        binding.resolutionTextView.text = serie.resolution
        binding.synopsisTextView.text = serie.synopsis
        binding.castTextView.text = serie.cast

        val titles = listOf("Episodios", "Más títulos similares")
        val fragments = listOf(EpisodesFragment.newInstance(), SimilarFragment.newInstance())

        binding.viewPager.adapter = SeriePagerAdapter(this, fragments)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

    }

    override fun onStart() {
        super.onStart()
        hideMenuBottom()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
        showMenuBottom()
    }

    companion object {
        fun newInstance() = SerieFragment()
    }

}