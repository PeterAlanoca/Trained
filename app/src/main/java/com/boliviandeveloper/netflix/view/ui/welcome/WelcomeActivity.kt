package com.boliviandeveloper.netflix.view.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.databinding.ActivityWelcomeBinding
import com.boliviandeveloper.netflix.model.entity.WelcomeItem
import com.boliviandeveloper.netflix.view.adapter.WelcomeItemPagerAdapter
import com.boliviandeveloper.netflix.view.ui.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            WelcomeItem(1, null, "Peliculas y series\nilimitadas y mucho más.", "Disfruta donde quieras. Cancela\ncuando quieras. Toca el enlace de\nabajo para suscribirte.", R.drawable.ic_welcome_background),
            WelcomeItem(2, R.drawable.ic_welcome_devices, "Tú decides cómo\nlo ves", "En tu telefono, tablet, computadora y\nTV sin costo extra.",  null),
            WelcomeItem(3, R.drawable.ic_welcome_download, "Descarga y listo", "Disfruta offline tus peliculas y series\nfavoritas en cualquier lugar.",  0),
            WelcomeItem(4, R.drawable.ic_welcome_not_contract, "Sin contratos\nmolestos", "Únete hoy, cancela cuando quieras.",  0)
        )

        binding.viewPager.adapter = WelcomeItemPagerAdapter(items)
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.loginTextView.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

}