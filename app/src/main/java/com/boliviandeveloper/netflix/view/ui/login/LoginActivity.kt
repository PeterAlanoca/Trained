package com.boliviandeveloper.netflix.view.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.boliviandeveloper.netflix.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailEditText.doOnTextChanged { _, _, _, _ ->
            validateLoginButton()
        }
        binding.passwordEditText.doOnTextChanged { _, _, _, _ ->
            validateLoginButton()
        }
        binding.loginButton.setOnClickListener {
            println("LOGIN")
        }
        binding.backImageView.setOnClickListener {
            finish()
        }
    }

    private fun validateLoginButton() {
        binding.emailEditText.text?.let { email ->
            binding.passwordEditText.text?.let { password ->
                binding.loginButton.isEnabled = (email.trim().isNotEmpty() && password.trim().isNotEmpty())
            }
        }
    }
}