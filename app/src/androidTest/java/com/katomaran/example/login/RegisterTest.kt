package com.katomaran.example.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.katomaran.example.login.Activity.SignupActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(SignupActivity::class.java)

    @Test
    fun firstTest() {
        val txtFullName = onView(withId(R.id.nameEdittxt))
        val txtEmail = onView(withId(R.id.emailEdittxt))
        val txtPassword = onView(withId(R.id.passEdittxt))
        val chkCheckBox = onView(withId(R.id.checkBox))
        val btnSignUp = onView(withId(R.id.signup))

        isVisible(txtFullName,5000)
        txtFullName.check(matches(withText("Enter your full name")))
        isVisible(txtEmail,5000)
        txtEmail.check(matches(withText("Your E-mail goes here")))
        isVisible(txtPassword,5000)
        txtPassword.check(matches(withText("********")))
        chkCheckBox.check(matches(isDisplayed()))
        btnSignUp.perform(click())
    }

}