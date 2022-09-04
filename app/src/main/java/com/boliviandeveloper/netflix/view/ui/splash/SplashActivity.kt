package com.boliviandeveloper.netflix.view.ui.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.boliviandeveloper.netflix.databinding.ActivitySplashBinding
import com.boliviandeveloper.netflix.model.entity.User
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.view.ui.menu.MenuActivity
import com.boliviandeveloper.netflix.view.ui.welcome.WelcomeActivity
import com.boliviandeveloper.netflix.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    private lateinit var binding: ActivitySplashBinding

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUser()

        val lottieAnimationView = binding.lottieAnimationView

        lottieAnimationView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) { }

            override fun onAnimationEnd(animation: Animator?) {
                if (user != null) {
                    startActivity(Intent(applicationContext, MenuActivity::class.java))
                } else {
                    startActivity(Intent(applicationContext, WelcomeActivity::class.java))
                }
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) { }

            override fun onAnimationRepeat(animation: Animator?) { }
        })
    }

    private fun getUser() {
        viewModel.getUser()
            .observe(this) { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        resource.data?.let { user ->
                            this.user = user
                        }
                    }
                    Resource.Status.ERROR -> {

                    }
                    Resource.Status.LOADING -> {

                    }
                }

            }
    }

}