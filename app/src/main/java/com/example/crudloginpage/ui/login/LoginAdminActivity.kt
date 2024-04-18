package com.example.crudloginpage.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.crudloginpage.MainActivity
import com.example.crudloginpage.databinding.ActivityLoginAdminBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initClick()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LoginAdminActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // F
            }
        })
    }

    private fun initClick() {
        binding.apply {
            logoutButton.setOnClickListener {
                finish()
            }
        }
    }


}