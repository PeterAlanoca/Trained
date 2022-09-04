package com.boliviandeveloper.netflix.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boliviandeveloper.netflix.databinding.ItemEpisodesBinding
import com.boliviandeveloper.netflix.model.entity.Episode
import com.bumptech.glide.Glide

class EpisodesAdapter(private val episodes: List<Episode> = emptyList()) : RecyclerView.Adapter<EpisodesAdapter.EpisodesHolder>() {

    private lateinit var context: Context
    private var action: ((Episode) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesHolder {
        context = parent.context
        val binding = ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodesHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesHolder, position: Int) {
        holder.set(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class EpisodesHolder(private val binding: ItemEpisodesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(episode: Episode) {
            binding.nameTextView.text = episode.name
            binding.durationTextView.text = episode.duration
            binding.descriptionTextView.text = episode.description
            Glide
                .with(context)
                .load(episode.thumbnailUrl)
                .centerCrop()
                .into(binding.thumbnailImageView)

            itemView.setOnClickListener {
                action?.let { action ->
                    action(episode)
                }
            }
        }
    }

    fun onSelected(action: (Episode) -> Unit) {
        this.action = action
    }
}


