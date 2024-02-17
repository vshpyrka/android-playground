package com.example.myapplication.architecture

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.R
import com.example.viewmodel.ViewModelActivities
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppArchitectureActivityTest {

    private lateinit var scenario: ActivityScenario<AppArchitectureActivity>

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
    fun testOpenHandlingLifecycle() {
        Espresso.onView(ViewMatchers.withId(R.id.lifecycle)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(LifecycleActivity::class.java.name))
    }

    @Test
    fun testOpenViewModel() {
        Espresso.onView(ViewMatchers.withId(R.id.view_model)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ViewModelActivities::class.java.name))
    }

    @Test
    fun testOpenDataStore() {
        Espresso.onView(ViewMatchers.withId(R.id.datastore)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(com.example.datastore.DataStoreLauncherActivity::class.java.name))
    }
}
