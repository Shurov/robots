package com.vorush.robot.view.robotlist.robot.robotslist

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.vorush.robot.view.robotlist.core.performWith

class ItemRobot(private val text: String) {

    fun press() {
        onView(withText(text)).performWith(click(),
                                           "Robot list has no item with $text text, but it should have!")
    }
}