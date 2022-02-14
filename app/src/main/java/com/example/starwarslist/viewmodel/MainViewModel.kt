package com.example.starwarslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarslist.api.Repository
import com.example.starwarslist.model.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    fun fetchPlanets(): Flow<PagingData<Planet>> {
        return repository.getPlanets().cachedIn(viewModelScope)
    }
}