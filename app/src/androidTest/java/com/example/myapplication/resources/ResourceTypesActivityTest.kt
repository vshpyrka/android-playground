package com.example.myapplication.resources

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vshpyrka.playground.R
import com.example.resources.types.ResourceTypeAnimActivity
import com.example.resources.types.ResourceTypeColorStateListActivity
import com.example.resources.types.ResourceTypeDrawableActivity
import com.example.resources.types.ResourceTypeFontActivity
import com.example.resources.types.ResourceTypeMenuActivity
import com.example.resources.types.ResourceTypeMoreActivity
import com.example.resources.types.ResourceTypeStringActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourceTypesActivityTest {

    private lateinit var scenario: ActivityScenario<ResourceTypesActivity>

    @Before
    fun setUp() {
        scenario = launchActivity()
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun testOpenResourceTypeAnimation() {
        onView(withId(R.id.anim)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeAnimActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeColorStateList() {
        onView(withId(R.id.colorStateList)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeColorStateListActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeDrawables() {
        onView(withId(R.id.drawables)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeDrawableActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeMenu() {
        onView(withId(R.id.menu)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeMenuActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeStrings() {
        onView(withId(R.id.strings)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeStringActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeFonts() {
        onView(withId(R.id.fonts)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeFontActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypeMore() {
        onView(withId(R.id.more)).perform(click())
        intended(IntentMatchers.hasComponent(ResourceTypeMoreActivity::class.java.name))
    }
}
