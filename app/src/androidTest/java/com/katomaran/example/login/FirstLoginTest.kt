package com.katomaran.example.login

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
import com.katomaran.example.login.Activity.MainActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstLoginTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun firstTest() {

        val btnAction = onView(allOf(withId(R.id.fab),isDisplayed()))
        val txtEmail = onView(allOf(withId(R.id.emailEdittxt),isDisplayed()))
        val txtPass = onView(allOf(withId(R.id.passEdittxt), isDisplayed()))
        val btnLogin = onView(allOf(withId(R.id.login), isDisplayed()))
        btnAction.perform(click())
        isVisible(txtEmail)
        viewExists(allOf(withId(R.id.emailEdittxt),isDisplayed()),1000)
        txtEmail.perform(replaceText("test@test.com"), closeSoftKeyboard())
        txtPass.perform(replaceText("123456"), closeSoftKeyboard())
        btnLogin.perform(click())
        viewExists(allOf(withText("Please Sign up"),isDisplayed()),3000)
    }

}

