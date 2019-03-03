package com.katomaran.example.login

import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher
import java.util.concurrent.CountDownLatch

private val TIMEOUT_MILLISECONDS = 5000
private val SLEEP_MILLISECONDS = 100
private var time = 0
private var wasDisplayed = false

@Throws(InterruptedException::class)
fun isVisible(interaction: ViewInteraction, millis: Long=5000): Boolean? {
    interaction.withFailureHandler({ error, viewMatcher -> wasDisplayed = false })
    if (wasDisplayed) {
        return true
    }
    if (time >= millis) {
        return false
    }

    //set it to true if failing handle should set it to false again.
    wasDisplayed = true
    Thread.sleep(SLEEP_MILLISECONDS.toLong())
    time += SLEEP_MILLISECONDS

    interaction.check(matches(isDisplayed()))
    Log.i("ViewChecker", "sleeping")
    return isVisible(interaction)
}

@Throws(InterruptedException::class)
fun viewExists(viewMatcher: Matcher<View>, millis: Long=5000): Boolean? {
    val found = arrayOfNulls<Boolean>(1)

    val latch = CountDownLatch(1)
    val action = object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isRoot()
        }

        override fun getDescription(): String {
            return "wait for a specific view with id <$viewMatcher> during $millis millis."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + millis


            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {

                    if (viewMatcher.matches(child)) {
                        Log.d(ContentValues.TAG, "perform: found match")
                        found[0] = true
                        latch.countDown()
                        return
                    }
                }

                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            found[0] = false
            latch.countDown()
        }
    }
    Espresso.onView(ViewMatchers.isRoot()).perform(action)

    latch.await()
    return found[0]
}