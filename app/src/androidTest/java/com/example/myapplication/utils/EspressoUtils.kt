package com.example.myapplication.utils

import android.view.View
import androidx.annotation.IntRange
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.StringDescription
import java.util.concurrent.Callable
import java.util.concurrent.TimeoutException

fun withBottomNavViewCheckedItem(id: Int): Matcher<View> {
    return object : BoundedMatcher<View, BottomNavigationView>(BottomNavigationView::class.java) {

        override fun matchesSafely(bottomNavigationView: BottomNavigationView): Boolean {
            return bottomNavigationView.menu.findItem(id)?.isChecked ?: false
        }

        override fun describeTo(description: Description) {
            description.appendText("Menu item is checked")
        }
    }
}

/**
 * Waits for the given `condition` to return `true`.
 * If the timeout elapses before the condition returns `true`, this method throws an exception.
 * @param reason    Reason printed when waiting for condition timeouts.
 * @param condition Condition to wait for.
 * @param timeout   Timeout in ms.
 */
fun waitForCondition(reason: String, condition: Callable<Boolean>, @IntRange(from = 0) timeout: Long) {
    val end = System.currentTimeMillis() + timeout

    try {
        while (!condition.call()) {
            if (System.currentTimeMillis() > end) {
                throw AssertionError(reason)
            }

            Thread.sleep(16)
        }
    } catch (e: Exception) {
        throw e
    }
}

fun waitForView(viewMatcher: Matcher<View>, timeout: Long = 10000, waitForDisplayed: Boolean = true): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return Matchers.any(View::class.java)
        }

        override fun getDescription(): String {
            val matcherDescription = StringDescription()
            viewMatcher.describeTo(matcherDescription)
            return "wait for a specific view <$matcherDescription> to be ${if (waitForDisplayed) "displayed" else "not displayed during $timeout millis."}"
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + timeout
            val visibleMatcher = isDisplayed()

            do {
                val viewVisible = TreeIterables.breadthFirstViewTraversal(view)
                    .any { viewMatcher.matches(it) && visibleMatcher.matches(it) }

                if (viewVisible == waitForDisplayed) return
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            // Timeout happens.
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}
