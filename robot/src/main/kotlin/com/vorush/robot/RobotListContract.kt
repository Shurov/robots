package com.vorush.robot

import com.vorush.robot.domain.Robot
import io.reactivex.Single

interface RobotListContract {

    interface RobotUseCase {

        fun getRobot(name: String): Single<Robot>
    }
}