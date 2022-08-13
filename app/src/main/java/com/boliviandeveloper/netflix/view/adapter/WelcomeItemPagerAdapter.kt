package com.boliviandeveloper.netflix.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boliviandeveloper.netflix.databinding.ItemPagerWelcomeBinding
import com.boliviandeveloper.netflix.model.WelcomeItem

class WelcomeItemPagerAdapter(private var items: List<WelcomeItem> = emptyList()) : RecyclerView.Adapter<WelcomeItemPagerAdapter.WelcomeItemPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomeItemPagerHolder {
        val binding = ItemPagerWelcomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WelcomeItemPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: WelcomeItemPagerHolder, position: Int) {
        holder.set(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class WelcomeItemPagerHolder(private val binding: ItemPagerWelcomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun set(item: WelcomeItem) {
            binding.titleTextView.text = item.title
            binding.descriptionTextView.text = item.description
            item.background?.let { background ->
                binding.backgroundImageView.setImageResource(background)
            } ?:run {
                binding.backgroundImageView.visibility = View.INVISIBLE
            }
            item.image?.let { image ->
                binding.imageView.setImageResource(image)
            } ?:run {
                binding.imageView.visibility = View.INVISIBLE
            }
        }

    }

}