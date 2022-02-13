package com.example.starwarslist.view

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.starwarslist.databinding.ActivityMainBinding
import com.example.starwarslist.view.adapter.PlanetListAdapter
import com.example.starwarslist.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    private lateinit var planetListAdapter: PlanetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPlanetsList()
        setUpAdapter()
    }

    private fun fetchPlanetsList() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.fetchPlanets().collect {
                planetListAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setUpAdapter() {
        planetListAdapter = PlanetListAdapter(this)
        binding.planetsRecyclerview.apply {
            adapter = planetListAdapter
        }
        planetListAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (planetListAdapter.snapshot().isEmpty()) {
                    binding.planetsListProgressbar.visibility = View.VISIBLE
                }
                binding.planetsListErrorText.isVisible = false
            } else {
                binding.planetsListProgressbar.isVisible = false
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (planetListAdapter.snapshot().isEmpty()) {
                        binding.planetsListErrorText.isVisible = true
                        binding.planetsListErrorText.text = it.error.localizedMessage
                    }
                }
            }
        }
    }
}