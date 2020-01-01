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
class ScoreFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(StartupActivity::class.java)

    @Test
    fun scoreFragmentTest() {
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

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Melbourne"),
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
        appCompatButton2.perform(click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("een staat in Europa"),
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
        appCompatButton3.perform(click())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Bern"),
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
        appCompatButton4.perform(click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Sovjet-Unie"),
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
        appCompatButton5.perform(click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Vaticaan"),
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
        appCompatButton6.perform(click())

        val appCompatButton7 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Fussen"),
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
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Washington"),
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
        appCompatButton8.perform(click())

        val appCompatButton9 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Londen"),
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
        appCompatButton9.perform(click())

        val appCompatButton10 = onView(
            allOf(
                withId(R.id.btn_keuze1), withText("Finland"),
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
        appCompatButton10.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.txt_score), withText("Uw score op de quiz is: 5 op: 9 in 35 seconden"),
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

        val editText = onView(
            allOf(
                withId(R.id.txt_nicknaam), withText("Nicknaam"),
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
        editText.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.btn_submitscore),
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
        button.check(matches(isDisplayed()))

        val recyclerView = onView(
            allOf(
                withId(R.id.recycler_scoreRes_scores),
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
        recyclerView.check(matches(isDisplayed()))
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
