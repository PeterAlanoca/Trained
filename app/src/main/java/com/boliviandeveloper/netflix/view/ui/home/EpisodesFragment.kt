package com.boliviandeveloper.netflix.view.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.FragmentEpisodesBinding
import com.boliviandeveloper.netflix.view.adapter.EpisodesAdapter
import com.boliviandeveloper.netflix.viewmodel.HomeViewModel

class EpisodesFragment: Fragment(R.layout.fragment_episodes) {

    private val viewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentEpisodesBinding? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = FragmentEpisodesBinding.inflate(inflater, container, false)
            binding?.let { binding ->
                viewModel.serie?.let { serie ->
                    val season = serie.seasons.first()
                    val episodes = season.episodes
                    val episodesAdapter = EpisodesAdapter(episodes)
                    episodesAdapter.onSelected { episode ->
                        val intent = Intent(requireContext(), PlayerActivity::class.java)
                        intent.putExtra(PlayerActivity.EPISODE_KEY, episode)
                        startActivity(intent)
                    }
                    binding.nameTextView.text = season.name
                    binding.episodesRecyclerView.apply {
                        adapter = episodesAdapter
                        setHasFixedSize(true)
                        setItemViewCacheSize(40)
                    }
                }
            }
        }
        return binding?.root
    }

    companion object {
        fun newInstance() = EpisodesFragment()
    }

}