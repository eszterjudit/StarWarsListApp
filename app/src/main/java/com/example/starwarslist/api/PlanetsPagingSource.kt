package com.example.starwarslist.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarslist.model.Planet
import com.example.starwarslist.util.Constants.Companion.FIRST_PAGE_INDEX

class PlanetsPagingSource(private val apiService: Service) :
    PagingSource<Int, Planet>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = apiService.getPlanets(position)
            val planets = response.results

            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = planets, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition
    }
}