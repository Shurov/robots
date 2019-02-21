package com.vorush.robot.view.robotlist.core

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.IdlingResource.ResourceCallback

class CustomIdlingResource : IdlingResource {

    private lateinit var resourceCallback: ResourceCallback

    override fun getName(): String = javaClass.name

    override fun isIdleNow(): Boolean = false

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        resourceCallback = callback
    }
}