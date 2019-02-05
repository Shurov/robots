package com.vorush.robot.domain

import android.graphics.drawable.Drawable
import com.vorush.robot.RobotListContract.RobotUseCase
import io.reactivex.Single

class RobotListRobotUseCase(private val robotDomain: RobotDomain) : RobotUseCase {

    override fun getRobot(name: String): Single<Robot> = robotDomain.getRobot(name)
}

interface RobotDomain {

    fun getRobot(robotName: String): Single<Robot>
}

data class Robot(val name: String,
                 val image: Drawable)