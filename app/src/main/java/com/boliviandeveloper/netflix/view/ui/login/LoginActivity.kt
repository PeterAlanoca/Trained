package com.boliviandeveloper.netflix.view.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.boliviandeveloper.netflix.databinding.ActivityLoginBinding
import com.boliviandeveloper.netflix.helper.extesion.showToast
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.view.ui.menu.MenuActivity
import com.boliviandeveloper.netflix.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

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
            onAuth()
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

    private fun onAuth() {
        binding.emailEditText.text?.let { email ->
            binding.passwordEditText.text.let { password ->
                viewModel.auth(email.toString(), password.toString())
                    .observe(this) { resource ->
                        when (resource.status) {
                            Resource.Status.SUCCESS -> startActivity(Intent(applicationContext, MenuActivity::class.java))
                            Resource.Status.ERROR -> {
                                resource.message?.let { message ->
                                    showToast(message)
                                }
                            }
                            Resource.Status.LOADING -> {
                                Log.d("clase", "cargando")
                            }
                        }

                }
            }
        }
    }

}