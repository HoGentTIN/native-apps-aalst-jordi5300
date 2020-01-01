package com.example.quizzit


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QuizSpelenFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(StartupActivity::class.java)

    @Test
    fun quizSpelenFragmentTest() {
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

        val constraintLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recycler_QuizRes_quizzes),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val button = onView(
            allOf(
                withId(R.id.btn_keuze1),
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
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.btn_keuze2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHost_main),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val button3 = onView(
            allOf(
                withId(R.id.btn_keuze3),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHost_main),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))

        val button4 = onView(
            allOf(
                withId(R.id.btn_keuze4),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHost_main),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        button4.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.view_timer), withText("6"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHost_main),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.txt_vraag), withText("Hoe heet de hoofdstad van Zwitserland?"),
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
        textView2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
