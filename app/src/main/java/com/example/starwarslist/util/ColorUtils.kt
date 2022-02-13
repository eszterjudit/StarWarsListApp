package com.example.starwarslist.util

import android.graphics.Color
import kotlin.math.roundToInt

class ColorUtils {
    companion object {
        fun getColorWithAlpha(color: Int, ratio: Float): Int {
            val newColor: Int
            val alpha = (Color.alpha(color) * ratio).roundToInt()
            val r: Int = Color.red(color)
            val g: Int = Color.green(color)
            val b: Int = Color.blue(color)
            newColor = Color.argb(alpha, r, g, b)
            return newColor
        }
    }
}