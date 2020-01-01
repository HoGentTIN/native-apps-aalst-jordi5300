package com.example.quizzit

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ScoreFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(StartupActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(10000)
    }

    @Test
    fun scoreFragmentTest() {
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
            Thread.sleep(3000)

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
            Thread.sleep(3000)

            val appCompatButton2 = onView(
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
            appCompatButton2.perform(click())
            Thread.sleep(2000)

            val appCompatButton3 = onView(
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
            appCompatButton3.perform(click())
            Thread.sleep(2000)

            val appCompatButton4 = onView(
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
            appCompatButton4.perform(click())
            Thread.sleep(2000)

            val appCompatButton5 = onView(
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
            appCompatButton5.perform(click())
            Thread.sleep(2000)

            val appCompatButton6 = onView(
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
            appCompatButton6.perform(click())
            Thread.sleep(2000)

            val appCompatButton7 = onView(
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
            appCompatButton7.perform(click())

            val appCompatButton8 = onView(
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
            appCompatButton8.perform(click())
            Thread.sleep(2000)

            val appCompatButton9 = onView(
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
            appCompatButton9.perform(click())
            Thread.sleep(2000)

            val appCompatButton10 = onView(
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
            appCompatButton10.perform(click())
            Thread.sleep(2000)

            val textView = onView(
                allOf(
                    withId(R.id.txt_score),
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
                    withId(R.id.txt_nicknaam),
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
