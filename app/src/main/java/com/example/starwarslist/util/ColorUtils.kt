package com.example.starwarslist.util

import android.graphics.Color

object ColorUtils {
    fun getColorWithAlpha(color: Int, ratio: Int): Int {
        val newColor: Int
        val alpha = (Color.alpha(color) * ratio)
        val r: Int = Color.red(color)
        val g: Int = Color.green(color)
        val b: Int = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        return newColor
    }
}