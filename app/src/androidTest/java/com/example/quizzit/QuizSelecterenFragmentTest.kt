package com.example.quizzit

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QuizSelecterenFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(StartupActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(10000)
    }

    @Test
    fun quizSelecterenFragmentTest() {
        try {
            val appCompatButton = onView(
                allOf(
                    withId(R.id.btn_play), withText("PLAY"),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.navHost_main),
                            0
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            appCompatButton.perform(click())

            val textView = onView(
                allOf(
                    withId(R.id.txt_selecteerQuiz),
                    withText("Selecteer een van de volgende quizzes: "),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.navHost_main),
                            0
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            textView.check(matches(isDisplayed()))

            val recyclerView = onView(
                allOf(
                    withId(R.id.recycler_QuizRes_quizzes),
                    childAtPosition(
                        childAtPosition(
                            withId(R.id.navHost_main),
                            0
                        ),
                        1
                    ),
                    isDisplayed()
                )
            )
            recyclerView.check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
        }
    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(position)
            }
        }
    }
}
