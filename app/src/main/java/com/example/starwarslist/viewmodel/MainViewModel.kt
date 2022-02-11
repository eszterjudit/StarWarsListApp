package com.example.starwarslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.starwarslist.api.ApiResult
import com.example.starwarslist.api.Repository
import com.example.starwarslist.model.PlanetsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<ApiResult<PlanetsResponse>> = MutableLiveData()
    val response: LiveData<ApiResult<PlanetsResponse>> = _response

    fun fetchPlanets() = viewModelScope.launch {
        repository.getPlanets().collect { values ->
            _response.value = values
        }
    }
}