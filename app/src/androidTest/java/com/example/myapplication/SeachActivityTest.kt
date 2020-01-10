package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.football2.api.ApiRepository
import com.example.myapplication.helper.EspressoIdlingResource
import com.example.myapplication.model.search.EventItem
import com.example.myapplication.presenter.SearchPresenter
import com.example.myapplication.ui.search.SearchActivity
import com.example.myapplication.view.SearchView
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SeachActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testSearch() {
        //https://www.dicoding.com/blog/menerapkan-espresso-idling-resource-pada-instrumentation-testing-tutorial-kelas-kade/
        //https://android.jlelse.eu/integrate-espresso-idling-resources-in-your-app-to-build-flexible-ui-tests-c779e24f5057
        onView(withId(R.id.edQuery)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText("Liverpool"), closeSoftKeyboard())
        onView(withId(R.id.btnCari)).perform(click())

        onView(withId(R.id.rv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }
}
