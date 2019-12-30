package com.example.quizzit

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizNavigationTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onStartUp_showCorrectFragment() {
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickPlay_goToQuizSelectFragment() {
        onView(withId(R.id.btn_play)).perform(click())
        onView(withId(R.id.quizSelecterenFragment)).check(matches(isDisplayed()))
    }
}
