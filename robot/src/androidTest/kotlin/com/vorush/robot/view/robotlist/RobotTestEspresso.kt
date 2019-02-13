package com.vorush.robot.view.robotlist

import com.vorush.robot.view.robotlist.core.EspressoTestSpecification
import com.vorush.robot.view.robotlist.robot.robotdetails.robotDetails
import com.vorush.robot.view.robotlist.robot.robotform.robotForm
import com.vorush.robot.view.robotlist.robot.robotslist.robotsList
import org.junit.Test

class RobotTestEspresso : EspressoTestSpecification() {

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