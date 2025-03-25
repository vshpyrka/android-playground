package com.example.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.architecture.AppArchitectureActivity
import com.example.myapplication.device.DeviceCompatibilityActivity
import com.example.nativelib.JniLauncherActivity
import com.example.navigation.NavigationActivity
import com.example.myapplication.resources.ResourcesActivity
import com.example.myapplication.views.CustomViewActivity
import com.vshpyrka.playground.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {

    private lateinit var scenario: ActivityScenario<LauncherActivity>

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
    fun testOpenNavigation() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(NavigationActivity::class.java.name))
    }

    @Test
    fun testOpenResources() {
        Espresso.onView(ViewMatchers.withId(R.id.resources)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ResourcesActivity::class.java.name))
    }

    @Test
    fun testOpenDevice() {
        Espresso.onView(ViewMatchers.withId(R.id.device)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(DeviceCompatibilityActivity::class.java.name))
    }

    @Test
    fun testOpenAppArchitecture() {
        Espresso.onView(ViewMatchers.withId(R.id.app_architecture)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(AppArchitectureActivity::class.java.name))
    }

    @Test
    fun testOpenCustomViews() {
        Espresso.onView(ViewMatchers.withId(R.id.custom_view)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(CustomViewActivity::class.java.name))
    }

    @Test
    fun testOpenJni() {
        Espresso.onView(ViewMatchers.withId(R.id.jni)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(JniLauncherActivity::class.java.name))
    }
}
