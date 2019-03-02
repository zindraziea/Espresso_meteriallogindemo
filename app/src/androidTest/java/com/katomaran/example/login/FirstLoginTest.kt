package com.katomaran.example.login

import android.support.test.espresso.action.ViewActions
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstLoginTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun firstTest() {

        val btnAction = onView(allOf(withId(R.id.fab),isDisplayed()))
        val txtemail = onView(allOf(withId(R.id.emailEdittxt),isDisplayed()))
        val txtpass = onView(allOf(withId(R.id.passEdittxt), isDisplayed()))
        val btnLogin = onView(allOf(withId(R.id.login), isDisplayed()))
        btnAction.perform(click());
        Thread.sleep(2000)
        txtemail.perform(replaceText("test@test.com"), closeSoftKeyboard())
        txtpass.perform(replaceText("123456"), closeSoftKeyboard())
        Thread.sleep(2000)
        btnLogin.perform(click())
    }
}
