package com.vorush.robot.view.robotlist.robot.robotslist

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.vorush.robot.R
import com.vorush.robot.view.robotlist.core.check
import com.vorush.robot.view.robotlist.core.checkIfExists
import com.vorush.robot.view.robotlist.core.isRecyclerEmpty
import com.vorush.robot.view.robotlist.core.performWith
import com.vorush.robot.view.robotlist.core.recyclerHasItemWithText
import org.hamcrest.Matchers.allOf

val robotsList: RobotsList
    get() = RobotsList(RobotsList.robotsListId)

fun robotsList(func: RobotsList.() -> Unit) = RobotsList(RobotsList.robotsListId).apply(func)

class RobotsList(@IdRes private val robotsListId: Int) {

    companion object {

        @IdRes
        var robotsListId = android.R.id.content

        private fun robotListFrame(@IdRes robotsListId: Int) = withId(robotsListId)
    }

    init {
        onSelf().checkIfExists("Robots list does not exist, but it should!")
    }

    fun isEmpty() {
        onChild(R.id.robot_list).check(matches(isRecyclerEmpty()),
                                       "Robot list is not empty, but it should be!")
    }

    fun pressAdd() {
        onView(withContentDescription(R.string.add_robot)).performWith(click(),
                                                                       "Cannot press add robot button, button is not visible!")
    }

    fun hasItem(text: String) {
        onChild(R.id.robot_list).check(matches(recyclerHasItemWithText(text)),
                                       "Robot list has no item with $text text, but it should have!")
    }

    fun item(text: String): ItemRobot = ItemRobot(text)

    private fun onSelf() = onView(robotListFrame(robotsListId))

    private fun onChild(@IdRes resId: Int) = onView(allOf(isDescendantOfA(robotListFrame(robotsListId)), withId(resId)))
}