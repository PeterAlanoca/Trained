package com.boliviandeveloper.netflix.view.ui.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boliviandeveloper.netflix.databinding.ActivitySplashBinding
import com.boliviandeveloper.netflix.view.ui.welcome.WelcomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieAnimationView = binding.lottieAnimationView

        lottieAnimationView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) { }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(applicationContext, WelcomeActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) { }

            override fun onAnimationRepeat(animation: Animator?) { }
        })
    }


}