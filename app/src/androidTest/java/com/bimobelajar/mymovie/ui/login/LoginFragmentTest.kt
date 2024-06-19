package com.bimobelajar.mymovie.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.bimobelajar.mymovie.MainActivity
import com.bimobelajar.mymovie.R
import com.bimobelajar.mymovie.util.EspressoIdlingResource
import com.bimobelajar.mymovie.util.ToastMatcher
import org.junit.*
import org.junit.runner.RunWith
import org.hamcrest.Matchers.not

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Rule @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testLoginSuccess() {
        // ke register fragment
        onView(withId(R.id.registerButton)).perform(click())

        // mendaftar
        onView(withId(R.id.usernameInput)).perform(typeText("testuser"), closeSoftKeyboard())
        onView(withId(R.id.emailInput)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordInput)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(click())

        // balik lagi ke log in karena udah register
        onView(withId(R.id.emailInput)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.accountImage)).perform(click())

        // verifikasi berhasil ke profile details
        onView(withId(R.id.accountdetails)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginFailure() {
        // Ngetik kredensial salah
        onView(withId(R.id.emailInput)).perform(typeText("wrong@salahinibos.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText("password"), closeSoftKeyboard())

        // Log in
        onView(withId(R.id.loginButton)).perform(click())

        // Ada toast salah = error == test sukses
        onView(withText("E-mail atau sandi salah!")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}
