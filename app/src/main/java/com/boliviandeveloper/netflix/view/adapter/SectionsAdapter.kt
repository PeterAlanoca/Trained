package com.boliviandeveloper.netflix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boliviandeveloper.netflix.databinding.ItemSectionsBinding
import com.boliviandeveloper.netflix.model.entity.Section
import com.boliviandeveloper.netflix.model.entity.Serie

class SectionsAdapter(private val sections: List<Section> = emptyList()) : RecyclerView.Adapter<SectionsAdapter.SectionsHolder>() {

    private var action: ((Serie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsHolder {
        val binding = ItemSectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionsHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionsHolder, position: Int) {
        holder.set(sections[position])
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    inner class SectionsHolder(private val binding: ItemSectionsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(section: Section) {
            binding.nameTextView.text = section.name
            val seriesAdapter = SeriesAdapter(section.series)
            seriesAdapter.onSelected { serie ->
                action?.let { action ->
                    action(serie)
                }
            }
            binding.seriesRecyclerView.apply {
                adapter = seriesAdapter
                setHasFixedSize(true)
                setItemViewCacheSize(40)
            }
        }
    }

    fun onSelected(action: (Serie) -> Unit) {
        this.action = action
    }

}