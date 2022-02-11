package com.example.starwarslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.starwarslist.api.ApiResult
import com.example.starwarslist.databinding.ActivityMainBinding
import com.example.starwarslist.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPlanets()
    }

    private fun fetchPlanets() {
        mainViewModel.fetchPlanets()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is ApiResult.Success -> {
                    response.data?.let {
                        binding.hello.text = "First planet in list is " + response.data.results[0].name
                    }
                }
                is ApiResult.Error -> {
                    // show error message
                }
            }
        }
    }
}