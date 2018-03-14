package com.slesarew.robot.view.robotlist.robot.robotform

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.slesarew.robot.R
import com.slesarew.robot.view.robotlist.core.checkIfExists
import com.slesarew.robot.view.robotlist.core.performWith
import org.hamcrest.Matchers.allOf

val robotForm: RobotForm
    get() = RobotForm(RobotForm.robotFormId)

fun robotForm(func: RobotForm.() -> Unit) = RobotForm(RobotForm.robotFormId).apply(func)

class RobotForm(@IdRes private val robotFormId: Int) {

    companion object {

        @IdRes
        var robotFormId = android.R.id.content

        private fun robotFormFrame(@IdRes robotFormId: Int) = withId(robotFormId)
    }

    init {
        onSelf().checkIfExists("Robot form does not exist, but it should!")
    }

    fun type(text: String) {
        onChild(R.id.robot_input).performWith(typeText(text),
                                              "Cannot type the robot input, input is not visible!")
    }

    fun pressOk() {
        onView(withText(android.R.string.ok)).performWith(click(),
                                                          "Cannot press ok button, button is not visible!")
    }

    private fun onSelf() = onView(robotFormFrame(robotFormId))

    private fun onChild(@IdRes resId: Int) = onView(allOf(isDescendantOfA(robotFormFrame(robotFormId)), withId(resId)))
}