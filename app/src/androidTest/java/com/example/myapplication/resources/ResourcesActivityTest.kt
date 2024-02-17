package com.example.myapplication.resources

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.language.PerAppLanguageActivity
import com.example.myapplication.R
import com.example.resources.ComplexXMLResourceActivity
import com.example.resources.ConfigurationChangeActivity
import com.example.resources.DrawableLocalizedResourceActivity
import com.example.resources.InternationalizationResourceActivity
import com.example.resources.RTLResourceActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourcesActivityTest {

    private lateinit var scenario: ActivityScenario<ResourcesActivity>

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
    fun testOpenLocalizedDrawableResource() {
        onView(withId(R.id.localizedDrawableResource)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(DrawableLocalizedResourceActivity::class.java.name))
    }

    @Test
    fun testOpenRTLResource() {
        onView(withId(R.id.rtlResource)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(RTLResourceActivity::class.java.name))
    }

    @Test
    fun testOpenConfigurationChange() {
        onView(withId(R.id.configChange)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(ConfigurationChangeActivity::class.java.name))
    }

    @Test
    fun testOpenInternationalization() {
        onView(withId(R.id.internationalization)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(InternationalizationResourceActivity::class.java.name))
    }

    @Test
    fun testOpenComplexXml() {
        onView(withId(R.id.complexXml)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(ComplexXMLResourceActivity::class.java.name))
    }

    @Test
    fun testOpenResourceTypes() {
        onView(withId(R.id.resourceTypes)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(ResourceTypesActivity::class.java.name))
    }

    @Test
    fun testOpenPerAppLanguage() {
        onView(withId(R.id.perAppLanguage)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(PerAppLanguageActivity::class.java.name))
    }
}
