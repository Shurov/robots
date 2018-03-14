package com.slesarew.robot.view.robotlist.robot.robotdetails

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.slesarew.robot.R
import com.slesarew.robot.view.robotlist.core.check
import com.slesarew.robot.view.robotlist.core.checkIfExists
import org.hamcrest.Matchers.allOf

val robotDetails: RobotsDetails
    get() = RobotsDetails(RobotsDetails.robotsDetailsId)

fun robotDetails(func: RobotsDetails.() -> Unit) = RobotsDetails(RobotsDetails.robotsDetailsId).apply(func)

class RobotsDetails(@IdRes private val robotDetailsId: Int) {

    companion object {

        @IdRes
        var robotsDetailsId = android.R.id.content

        private fun robotDetailsFrame(@IdRes robotDetailsId: Int) = withId(robotDetailsId)
    }

    init {
        onSelf().checkIfExists("Robot details does not exist, but it should!")
    }

    fun hasTitle(title: String) {
        onView(withText(title)).checkIfExists("Details should have title: $title, but it has not!")
    }

    fun hasImage(contentDescription: String) {
        onChild(R.id.robot_image).check(matches(withContentDescription(contentDescription)),
                                        "Details should have image: $contentDescription, but it has not!")
    }

    private fun onSelf() = onView(robotDetailsFrame(robotDetailsId))

    private fun onChild(@IdRes resId: Int) = onView(allOf(isDescendantOfA(robotDetailsFrame(robotDetailsId)), withId(resId)))
}