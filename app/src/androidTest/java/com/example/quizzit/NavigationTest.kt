package com.example.quizzit

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onStartUp_showCorrectFragment() {
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickPlay_goToGameFragment() {
        onView(withId(R.id.playButton)).perform(click())
        onView(withId(R.id.quizFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun quizScreen_upButtonClicked_backToHome() {
        onView(withId(R.id.playButton)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }
}
