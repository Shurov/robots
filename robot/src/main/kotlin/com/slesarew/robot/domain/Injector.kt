package com.slesarew.robot.domain

import com.slesarew.robot.RobotListContract.RobotUseCase

object Injector {

    fun getRobotUseCase(): RobotUseCase = RobotListRobotUseCase(RobotGlideDomain())
}