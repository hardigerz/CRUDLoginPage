package com.example.crudloginpage.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudloginpage.MainActivity
import com.example.crudloginpage.adapter.PhotoAdapter
import com.example.crudloginpage.databinding.ActivityLoginUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding
    private val viewModel by viewModels<LoginUserViewModel>()
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        photoAdapter = PhotoAdapter()
        binding.rvContent.apply {
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(this@LoginUserActivity)
            setHasFixedSize(true)
        }
        photoAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                (loadState.append is LoadState.Loading && loadState.append.endOfPaginationReached)
            ) {
                // Show progress bar when loading is in progress or when transitioning to the next page
                binding.progressBar.isVisible = true
            } else {
                // Hide progress bar when loading is not in progress
                binding.progressBar.isVisible = false

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }


                if (loadState.refresh is LoadState.Loading ||
                    loadState.append is LoadState.Loading ||
                    loadState.prepend is LoadState.Loading
                ) {
                    // Show progress bar when loading is in progress
                    binding.progressBar.isVisible = true
                } else {
                    // Hide progress bar when loading is not in progress
                    binding.progressBar.isVisible = false

                    // If we have an error, show a toast
                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
        lifecycleScope.launch {
            viewModel.getPhoto().collectLatest { pagingData ->
                photoAdapter.submitData(pagingData)
            }
        }
        viewModel.getPhoto()
        initClick()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LoginUserActivity, MainActivity::class.java)
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