package com.slesarew.robot

import com.slesarew.robot.domain.Robot
import io.reactivex.Single

interface RobotListContract {

    interface RobotUseCase {

        fun getRobot(name: String): Single<Robot>
    }
}