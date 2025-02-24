package com.example.glance.resizable

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.LocalContext
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasTestTag
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
@LooperMode(LooperMode.Mode.PAUSED)
class GlanceAppResizableWidgetTest {

    private val devices = buildList {
        HomeDevice.entries.forEach {
            add(HomeDeviceStatus(device = it, isOn = false))
        }
    }

    @Test
    fun testResponsiveGlanceWidgetSmall() = runGlanceAppWidgetUnitTest{
        setAppWidgetSize(DpSize(100.dp, 100.dp))
        provideComposable {
            //  Override the LocalContext with the Robolectric context to access resources via Context class
            CompositionLocalProvider(LocalContext provides RuntimeEnvironment.getApplication()) {
                ResizableWidgetContent(devices)
            }
        }
        onAllNodes(
            hasTestTag("device_round_icon")
        ).assertCountEquals(devices.size)
    }

    @Test
    fun testResponsiveGlanceWidgetNormal() = runGlanceAppWidgetUnitTest{
        setAppWidgetSize(DpSize(250.dp, 200.dp))
        provideComposable {
            //  Override the LocalContext with the Robolectric context to access resources via Context class
            CompositionLocalProvider(LocalContext provides RuntimeEnvironment.getApplication()) {
                ResizableWidgetContent(devices)
            }
        }
        onAllNodes(
            hasTestTag("clickable_box")
        ).assertCountEquals(devices.size)

        onNode(
            hasTestTag("refresh")
        ).assertExists()
        onNode(
            hasTestTag("power")
        ).assertDoesNotExist()
    }

    @Test
    fun testResponsiveGlanceWidgetWide() = runGlanceAppWidgetUnitTest{
        setAppWidgetSize(DpSize(350.dp, 200.dp))
        provideComposable {
            //  Override the LocalContext with the Robolectric context to access resources via Context class
            CompositionLocalProvider(LocalContext provides RuntimeEnvironment.getApplication()) {
                ResizableWidgetContent(devices)
            }
        }
        onAllNodes(
            hasTestTag("clickable_box")
        ).assertCountEquals(devices.size)
        onNode(
            hasTestTag("refresh")
        ).assertExists()
        onNode(
            hasTestTag("power")
        ).assertExists()
    }

}
