package com.katomaran.example.login

import android.support.test.espresso.IdlingRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.katomaran.example.login.Activity.MainActivity
import com.katomaran.example.login.Activity.util.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstLoginTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun firstTest() {

        val btnAction = onView(allOf(withId(R.id.fab),isDisplayed()))
        val txtemail = onView(allOf(withId(R.id.emailEdittxt),isDisplayed()))
        val txtpass = onView(allOf(withId(R.id.passEdittxt), isDisplayed()))
        val btnLogin = onView(allOf(withId(R.id.login), isDisplayed()))
        btnAction.perform(click())
        isVisible(txtemail)
        isVisible(allOf(withId(R.id.emailEdittxt),isDisplayed()),1000)
        txtemail.perform(replaceText("test@test.com"), closeSoftKeyboard())
        txtpass.perform(replaceText("123456"), closeSoftKeyboard())
        btnLogin.perform(click())
    }

    @Test
    fun idlingResourceTest() {

        val btnAction = onView(allOf(withId(R.id.fab),isDisplayed()))
        val txtemail = onView(allOf(withId(R.id.emailEdittxt),isDisplayed()))
        val txtpass = onView(allOf(withId(R.id.passEdittxt), isDisplayed()))
        val btnLogin = onView(allOf(withId(R.id.login), isDisplayed()))
        btnAction.perform(click())
        isVisible(txtemail)
        isVisible(allOf(withId(R.id.emailEdittxt),isDisplayed()),1000)
        txtemail.perform(replaceText("test@test.com"), closeSoftKeyboard())
        txtpass.perform(replaceText("123456"), closeSoftKeyboard())
        btnLogin.perform(click())
    }

}

