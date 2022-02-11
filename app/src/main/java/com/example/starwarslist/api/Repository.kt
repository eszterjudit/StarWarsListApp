package com.example.starwarslist.api

import com.example.starwarslist.model.PlanetsResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val starWarsApi: StarWarsApi
) : BaseResponse() {

    suspend fun getPlanets(): Flow<ApiResult<PlanetsResponse>> {
        return flow {
            emit(safeApiCall { starWarsApi.getPlanets() })
        }.flowOn(Dispatchers.IO)
    }

}