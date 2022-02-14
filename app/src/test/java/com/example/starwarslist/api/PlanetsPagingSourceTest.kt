package com.example.starwarslist.api

import androidx.paging.PagingSource
import com.example.starwarslist.model.Planet
import com.example.starwarslist.model.PlanetsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class PlanetsPagingSourceTest {

    private lateinit var pagingSource: PlanetsPagingSource

    private val mockPlanets = listOf(
        Planet("Tatooine", "10", "1", "Desert")
    )

    private val mockPlanetsResponse = PlanetsResponse(
        60,
        "https://swapi.dev/api/planets/?page=2",
        null,
        mockPlanets
    )

    @MockK
    lateinit var service: Service

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlockingTest {
        pagingSource = PlanetsPagingSource(service)
        coEvery { service.getPlanets(1) } returns mockPlanetsResponse
        assertEquals(
            expected = PagingSource.LoadResult.Page(
                data = mockPlanets,
                prevKey = null,
                nextKey = 2
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )
    }
}