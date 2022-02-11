package com.example.starwarslist.model

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Planet>
)