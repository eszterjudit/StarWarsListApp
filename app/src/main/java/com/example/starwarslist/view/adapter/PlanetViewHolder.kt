package com.example.starwarslist.view.adapter

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarslist.R
import com.example.starwarslist.databinding.PlanetListItemBinding
import com.example.starwarslist.model.Planet
import com.example.starwarslist.util.ColorUtils

class PlanetViewHolder(private val binding: PlanetListItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(planet: Planet?) {
        binding.name.text = planet?.name
        binding.terrain.text =
            String.format(context.getString(R.string.planet_terrain), planet?.terrain)
        binding.population.text =
            String.format(context.getString(R.string.planet_population), planet?.population)
        setBgColorForWater(planet)
        setTextForWater(planet)
    }

    private fun setTextForWater(planet: Planet?) {
        if (planet?.surfaceWater != null && planet.surfaceWater != context.getString(R.string.unknown)) {
            binding.surfaceWater.text = String.format(context.getString(R.string.planet_surface_water_percentage), planet.surfaceWater)
        } else {
            binding.surfaceWater.setText(R.string.planet_unknown_surface_water)
        }
    }

    private fun setBgColorForWater(planet: Planet?) {
        if (planet?.surfaceWater != null && planet.surfaceWater != context.getString(R.string.unknown) && planet.surfaceWater != "0") {
            binding.planetCardBg.setCardBackgroundColor(
                ColorUtils.getColorWithAlpha(
                    ContextCompat.getColor(
                        context,
                        R.color.secondaryColor
                    ), planet.surfaceWater.toFloat()
                )
            )
        } else {
            binding.planetCardBg.setCardBackgroundColor(Color.WHITE)
        }
    }
}