package com.boliviandeveloper.netflix.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.FragmentHomeBinding
import com.boliviandeveloper.netflix.helper.extesion.push
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.view.adapter.SectionsAdapter
import com.boliviandeveloper.netflix.viewmodel.HomeViewModel
import com.bumptech.glide.Glide

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            getSections()
        }
        return binding?.root
    }

    private fun getSections() {
        viewModel.getAll()
            .observe(viewLifecycleOwner) { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        resource.data?.let { data ->
                            binding?.let { binding ->
                                Glide
                                    .with(this)
                                    .load(data.cover.coverUrl)
                                    .centerCrop()
                                    .placeholder(R.drawable.shape_placeholder_image)
                                    .into(binding.coverImageView)

                                Glide
                                    .with(this)
                                    .load(data.cover.logoUrl)
                                    .centerInside()
                                    .placeholder(R.drawable.shape_placeholder_image)
                                    .into(binding.logoImageView)

                                binding.genderTextView.text = data.cover.gender

                                val sectionsAdapter = SectionsAdapter(data.sections)
                                sectionsAdapter.onSelected { serie ->
                                    viewModel.serie = serie
                                    push(SerieFragment.newInstance())
                                }
                                binding.sectionsRecyclerView.apply {
                                    adapter = sectionsAdapter
                                    setHasFixedSize(true)
                                    setItemViewCacheSize(40)
                                }

                                binding.playButton.setOnClickListener {
                                    viewModel.serie = data.cover
                                    push(SerieFragment.newInstance())
                                }
                            }
                        }
                    }
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.ERROR -> {

                    }
                }
            }
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}