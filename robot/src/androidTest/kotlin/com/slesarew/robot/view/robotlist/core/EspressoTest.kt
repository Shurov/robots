package com.slesarew.robot.view.robotlist.core

import android.support.test.rule.ActivityTestRule
import com.slesarew.robot.view.robotlist.RobotListActivity
import org.junit.Rule

abstract class EspressoTest {

    @get:Rule
    @Suppress("unused")
    val activityRule = ActivityTestRule(RobotListActivity::class.java)
}