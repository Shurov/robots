package com.vorush.robot.view.robotlist.core

import android.content.Context
import android.support.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

abstract class UiAutomatorTestSpecification {

    private val instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }
    protected val device: UiDevice by lazy { UiDevice.getInstance(instrumentation) }
    protected val context: Context by lazy { instrumentation.targetContext }
}