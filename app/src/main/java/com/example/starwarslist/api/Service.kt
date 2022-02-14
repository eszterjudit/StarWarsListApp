package com.example.starwarslist.api

import com.example.starwarslist.model.PlanetsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("planets/?page/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetsResponse
}