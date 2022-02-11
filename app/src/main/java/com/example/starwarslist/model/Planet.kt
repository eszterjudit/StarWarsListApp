package com.example.starwarslist.model

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name")
    val name: String
)