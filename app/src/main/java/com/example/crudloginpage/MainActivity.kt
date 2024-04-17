package com.example.crudloginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudloginpage.databinding.ActivityMainBinding
import com.example.crudloginpage.ui.login.LoginActivity
import com.example.crudloginpage.ui.register.RegisterActivity
import com.example.crudloginpage.utils.condition1Check
import com.example.crudloginpage.utils.condition2Check
import com.example.crudloginpage.utils.condition3Check
import com.example.crudloginpage.utils.isValidEmail
import com.google.android.material.textfield.TextInputLayout
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
                validateLogin()
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


    private fun validateLogin() {
        val password = binding.passwordEditText.text.toString()
        val email = binding.emailEditText.text.toString()

        if (!email.isValidEmail()) {
            binding.emailEditText.error = "Invalid email address"
            binding.emailEditText.requestFocus()
            return
        }

        // Check which condition the password doesn't meet
        val conditionNotMet = when {
            !password.condition1Check -> "Password must be at least 6 characters long."
            !password.condition2Check -> "Password must be at least 6 characters long, contain at least one digit, and have mixed case."
            !password.condition3Check -> "Password must be at least 6 characters long, contain at least one digit, have mixed case, and contain a special character."
            else -> null // Password meets all conditions
        }

        // Show error message if conditionNotMet is not null
        conditionNotMet?.let {
            binding.passwordEditText.error = it
            binding.passwordEditText.requestFocus()
            binding.passwordTextInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            return
        }

        // Check if two are meet then
        //Navigate to LoginActivity
        binding.passwordTextInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        initCleanData()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)


    }
}