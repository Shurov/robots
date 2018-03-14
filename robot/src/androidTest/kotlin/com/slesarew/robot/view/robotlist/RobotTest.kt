package com.slesarew.robot.view.robotlist

import com.slesarew.robot.view.robotlist.core.EspressoTest
import com.slesarew.robot.view.robotlist.robot.robotdetails.robotDetails
import com.slesarew.robot.view.robotlist.robot.robotform.robotForm
import com.slesarew.robot.view.robotlist.robot.robotslist.robotsList
import org.junit.Test

class RobotTest : EspressoTest() {

    @Test
    fun should_add_robot() {
        robotsList {
            isEmpty()
            pressAdd()
        }

        robotForm {
            type("robot")
            pressOk()
        }

        robotsList {
            hasItem("robot")
            item("robot").press()
        }

        robotDetails {
            hasTitle("robot")
            hasImage("robot")
        }
    }
}