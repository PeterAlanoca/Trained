package com.boliviandeveloper.netflix.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.ItemSeriesBinding
import com.boliviandeveloper.netflix.model.entity.Serie
import com.bumptech.glide.Glide

class SeriesAdapter(series: List<Serie>): RecyclerView.Adapter<SeriesAdapter.SeriesHolder>() {

    private lateinit var context: Context
    private var series: MutableList<Serie>
    private var action: ((Serie) -> Unit)? = null

    init {
        this.series = series.toMutableList()
        if (this.series.size > 1) {
            this.series.add(series.last())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesHolder {
        context = parent.context
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        holder.set(series[position])
    }

    override fun getItemCount(): Int {
        return series.size
    }

    inner class SeriesHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(serie: Serie) {

            if (adapterPosition == 0) {
                binding.leftView.visibility = View.VISIBLE
            }
            if (adapterPosition == series.size - 1) {
                binding.rightView.visibility = View.VISIBLE
            }
            binding.contentImageView.layoutParams.width = (0.29 * Resources.getSystem().displayMetrics.widthPixels).toInt()
            binding.contentImageView.layoutParams.height = (binding.contentImageView.layoutParams.width + 0.4 * binding.contentImageView.layoutParams.width).toInt()
            Glide
                .with(context)
                .load(serie.coverUrl)
                .centerCrop()
                .placeholder(R.drawable.shape_placeholder_image)
                .into(binding.coverImageView)

            itemView.setOnClickListener {
                action?.let { action ->
                    action(serie)
                }
            }
        }
    }

    fun onSelected(action: (Serie) -> Unit) {
        this.action = action
    }
}
