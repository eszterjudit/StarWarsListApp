package com.example.starwarslist.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarslist.R
import com.example.starwarslist.view.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testProgressShowsOnStart() {
        onView(withId(R.id.planets_list_progressbar)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewShows() {
        CountingIdlingResourceSingleton.increment()
        val job = GlobalScope.launch {
            delay(3000)
        }
        job.invokeOnCompletion {
            CountingIdlingResourceSingleton.decrement()
            onView(withId(R.id.planets_recyclerview)).check(matches(isDisplayed()))
        }
    }
}