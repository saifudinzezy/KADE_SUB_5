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
class SeachActivityTest : SearchView {
    private lateinit var presenter: SearchPresenter

    @get:Rule
    var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testSearch() {

        onView(withId(R.id.edQuery)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText("Liverpool"), closeSoftKeyboard())
        onView(withId(R.id.btnCari)).perform(click())

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)
        //Memberitahukan Espresso bahwa aplikasi sedang sibuk
        EspressoIdlingResource.increment()
        presenter.getSearch("Liverpool")

       /* onView(withId(R.id.rv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))*/
    }


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamList(data: List<EventItem>) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            //Memberitahukan bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }
        /*swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()*/
        onView(withId(R.id.rv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }
}
