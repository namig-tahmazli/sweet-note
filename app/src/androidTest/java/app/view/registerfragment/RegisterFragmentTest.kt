package app.view.registerfragment

import android.support.annotation.IdRes
import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.view.View
import app.views.launchactivity.LaunchActivity
import com.example.namigtahmazli.sweetnote.R
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
internal class RegisterFragmentTest : KoinTest {

    @Suppress("MemberVisibilityCanBePrivate")
    @get:Rule
    val activityTestRule: IntentsTestRule<LaunchActivity> = IntentsTestRule(LaunchActivity::class.java, false, false)

    private val context = InstrumentationRegistry.getTargetContext()

    private val emailError: String by lazy {
        "Invalid"
    }

    private val passwordError: String by lazy {
        "Invalid"
    }

    @Before
    fun setUp() {
        activityTestRule.launchActivity(LaunchActivity.getIntent(context))
        onView(withId(R.id.group_register)).perform(click())
    }

    private fun hasError(error: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("Has Error")
            }

            override fun matchesSafely(item: View?): Boolean {
                val testInputLayout = item as? TextInputLayout
                        ?: throw IllegalAccessException("Not TextInputLayout")
                return testInputLayout.error == error
            }

        }
    }

    @Test
    fun typeInvalidEmailErrorWillBeShown() {
        typeText(R.id.et_email, "abc") {
            onView(withId(R.id.email_layout)).check(matches(hasError(emailError)))
        }
    }

    @Test
    fun typeValidEmailErrorWillBeGone() {
        typeText(R.id.et_email, "a@b.co") {
            val matcher = if (it == "a@b.co".lastIndex) {
                not(hasError(emailError))
            } else {
                hasError(emailError)
            }
            onView(withId(R.id.email_layout)).check(matches(matcher))
        }
    }

    @Test
    fun typeInvalidPasswordErrorWillBeShown() {
        typeText(R.id.et_password, "abcnd1234") {
            onView(withId(R.id.password_layout)).check(matches(hasError(passwordError)))
        }
    }

    @Test
    fun typeValidPasswordErrorWillBeGone() {
        typeText(R.id.et_password, "abcAbc123@") {
            val matcher = if (it == "abcAbc123@".lastIndex) {
                not(hasError(passwordError))
            } else {
                hasError(passwordError)
            }
            onView(withId(R.id.password_layout)).check(matches(matcher))
        }
    }

    @Test
    fun whenEmailAndPasswordAreValidRegisterButtonWillBeEnabled() {
        onView(withId(R.id.et_email)).perform(typeText("a@b.co"))
        assertButtonEnabled(false)
        onView(withId(R.id.et_password)).perform(typeText("abcAbc123@"))
        assertButtonEnabled(true)
    }

    private fun assertButtonEnabled(isEnabled: Boolean) {
        val matcher = if (isEnabled) ViewMatchers.isEnabled() else not(ViewMatchers.isEnabled())
        onView(withId(R.id.btn_register)).check(matches(matcher))
    }

    private fun typeText(@IdRes id: Int, text: String, conditionBlock: (Int) -> Unit) {
        text.map { it.toString() }.forEachIndexed { index, it ->
            onView(withId(id)).perform(typeText(it))
            conditionBlock(index)
        }
    }
}