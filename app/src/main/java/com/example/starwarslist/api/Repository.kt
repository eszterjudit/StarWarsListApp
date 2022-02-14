package com.example.starwarslist.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarslist.model.Planet
import com.example.starwarslist.util.Constants.Companion.NETWORK_PAGE_SIZE
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val service: Service) {
    fun getPlanets(): Flow<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PlanetsPagingSource(service)
            }
        ).flow
    }
}