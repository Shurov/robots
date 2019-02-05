package com.vorush.robot.domain

import com.vorush.robot.RobotListContract.RobotUseCase

object Injector {

    fun getRobotUseCase(): RobotUseCase = RobotListRobotUseCase(RobotGlideDomain())
}