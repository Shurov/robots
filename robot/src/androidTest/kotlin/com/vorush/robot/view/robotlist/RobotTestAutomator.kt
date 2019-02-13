package com.vorush.robot.view.robotlist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.vorush.robot.view.robotlist.core.UiAutomatorTestSpecification
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RobotTestAutomator : UiAutomatorTestSpecification() {

    var TARGET_PACKAGE = ""

    @Before
    fun startActivity() {
        TARGET_PACKAGE = InstrumentationRegistry.getTargetContext().packageName
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(TARGET_PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE).depth(0)), 5000)
    }

    @Test
    fun should_add_robot() {
        val name = "Robot1"

        val list = device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/robot_list"))
        assertTrue(list.childCount == 0)

        val addRobotButton = device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/add_robot"))
        addRobotButton.click()

        val inputName = device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/robot_input"))
        inputName.text = name

        val okButton = device.findObject(UiSelector().resourceId("android:id/button1"))
        okButton.click()

        val firstRobot = device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/robot_name"))
        assertTrue(firstRobot.text == name)
        firstRobot.click()

        val robotName =
            device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/action_bar"))
                .getChild(UiSelector().className("android.widget.TextView"))
        assertTrue(robotName.text == name)

        val robotImage = device.findObject(UiSelector().resourceId("$TARGET_PACKAGE:id/robot_image"))
        assertTrue(robotImage.contentDescription == name)
    }
}