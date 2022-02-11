package com.example.starwarslist.api

import com.example.starwarslist.model.PlanetsResponse
import com.example.starwarslist.util.Constants
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("planets")
    suspend fun getPlanets(): Response<PlanetsResponse>
}