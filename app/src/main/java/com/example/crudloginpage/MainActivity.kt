package com.example.crudloginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudloginpage.databinding.ActivityMainBinding
import com.example.crudloginpage.ui.login.LoginActivity
import com.example.crudloginpage.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initClick()
    }

    private fun initClick() {
        binding.apply {
            loginButton.setOnClickListener {
                // Navigate to LoginActivity
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            registerButton.setOnClickListener {
                //If User input login and then click register
                initCleanData()
                // Navigate to LoginActivity
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun initCleanData() {
        binding.apply {
            passwordEditText.text?.clear()
            emailEditText.text?.clear()
            passwordTextInputLayout.clearFocus()
            emailTextInputLayout.clearFocus()
            passwordEditText.clearFocus()
            emailTextInputLayout.clearFocus()

        }
    }
}