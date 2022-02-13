package com.example.starwarslist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(

    @SerializedName("name")
    val name: String,

    @SerializedName("population")
    val population: String,

    @SerializedName("surface_water")
    val surfaceWater: String,

    @SerializedName("terrain")
    val terrain: String

) : Parcelable