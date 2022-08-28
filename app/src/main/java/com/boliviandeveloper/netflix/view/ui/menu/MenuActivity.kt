package com.boliviandeveloper.netflix.view.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.ActivityMenuBinding
import com.boliviandeveloper.netflix.helper.extesion.push
import com.boliviandeveloper.netflix.view.ui.download.DownloadFragment
import com.boliviandeveloper.netflix.view.ui.home.HomeFragment
import com.boliviandeveloper.netflix.view.ui.laugh.LaughFragment
import com.boliviandeveloper.netflix.view.ui.new.NewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { item ->

            var fragment: Fragment? = null

            when (item.itemId) {
                R.id.actionHome -> fragment = HomeFragment.newInstance()
                R.id.actionNew -> fragment = NewFragment.newInstance()
                R.id.actionLaugh -> fragment = LaughFragment.newInstance()
                R.id.actionDownload -> fragment = DownloadFragment.newInstance()
            }
            fragment?.let { f ->
                push(f)
            }
            return@setOnItemSelectedListener true
        }

        bottomNavigationView.selectedItemId = R.id.actionHome
    }
}