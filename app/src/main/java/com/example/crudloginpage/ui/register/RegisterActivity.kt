package com.example.crudloginpage.ui.register


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudloginpage.R
import com.example.crudloginpage.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRole()
        initClick()
    }
    private fun initClick(){
        binding.apply {

            submitButton.setOnClickListener {

                coroutineScope.launch {
                    delay(2000) // Delay for 5 seconds (5000 milliseconds)
                    showToastAndFinish()
                }
            }
            cancelButton.setOnClickListener {
                finish()
            }

        }
    }

    private fun showToastAndFinish() {
        Toast.makeText(this@RegisterActivity, "Berhasil", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun initRole(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.roles_array,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
       binding.roleSpinner.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}