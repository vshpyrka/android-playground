package com.example.myapplication.device

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.R
import com.example.embeddedactivity.ChatListActivity
import com.example.slidingpanelayout.SlidingPaneLayoutAdaptiveDesignActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceCompatibilityActivityTest {

    private lateinit var scenario: ActivityScenario<DeviceCompatibilityActivity>

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
    fun testOpenDifferentAspectRatio() {
        Espresso.onView(ViewMatchers.withId(R.id.aspect_ratio)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ResizeAspectRatioActivity::class.java.name))
    }

    @Test
    fun testOpenDifferentLocales() {
        Espresso.onView(ViewMatchers.withId(R.id.locales)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(SupportDifferentLanguageLocalesActivity::class.java.name))
    }

    @Test
    fun testOpenMediaProjection() {
        Espresso.onView(ViewMatchers.withId(R.id.media_projection)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(com.example.mediaprojection.MediaProjectionActivity::class.java.name))
    }

    @Test
    fun testOpenSupportedScreenSizes() {
        Espresso.onView(ViewMatchers.withId(R.id.supported_screen_size)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(SupportDifferentScreenSizesActivity::class.java.name))
    }

    @Test
    fun testOpenSlidingPaneAdaptiveDesign() {
        Espresso.onView(ViewMatchers.withId(R.id.sliding_pane_adaptive_design)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(SlidingPaneLayoutAdaptiveDesignActivity::class.java.name))
    }

    @Test
    fun testOpenEmbeddedActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.embedded_activity)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ChatListActivity::class.java.name))
    }
}
