package com.vorush.robot.view

import android.content.pm.ActivityInfo
import android.support.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.vorush.robot.view.robotlist.core.EspressoTestSpecification
import org.junit.After
import org.junit.Before
import org.junit.Test

class OrientationChangeTest : EspressoTestSpecification() {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device = UiDevice.getInstance(instrumentation)

    @Before
    fun showApp() {
        displayScreenFor(4000L)
    }

    @Test
    fun espressoRotateScreen() {
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    @Test
    fun automatorRotateScreen() {
        device.setOrientationLeft()
    }

    @After
    fun showSystemUi() {
        displayScreenFor(1000L)
        device.pressRecentApps()
        displayScreenFor(5000L)
    }

    private fun displayScreenFor(ms: Long) {
        Thread.sleep(ms)
    }
}