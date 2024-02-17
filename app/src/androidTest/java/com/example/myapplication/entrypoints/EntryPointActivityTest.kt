package com.example.myapplication.entrypoints

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.documents.DocumentsLauncherActivity
import com.example.myapplication.R
import com.example.shortcuts.ShortcutLauncherActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryPointActivityTest {

    private lateinit var scenario: ActivityScenario<EntryPointActivity>

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
    fun testDocumentsNavigation() {
        Espresso.onView(ViewMatchers.withId(R.id.documents)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(DocumentsLauncherActivity::class.java.name))
    }

    @Test
    fun testShortcutsNavigation() {
        Espresso.onView(ViewMatchers.withId(R.id.shortcut)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ShortcutLauncherActivity::class.java.name))
    }

}
