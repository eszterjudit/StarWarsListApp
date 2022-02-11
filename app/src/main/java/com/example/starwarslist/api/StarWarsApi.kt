package com.example.starwarslist.api

import javax.inject.Inject

class StarWarsApi @Inject constructor(private val service: Service) {

    suspend fun getPlanets() = service.getPlanets()
}