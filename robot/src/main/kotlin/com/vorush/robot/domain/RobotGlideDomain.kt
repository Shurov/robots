package com.vorush.robot.domain

import com.bumptech.glide.Glide
import com.vorush.robot.App
import io.reactivex.Single

class RobotGlideDomain : RobotDomain {

    override fun getRobot(robotName: String): Single<Robot> {
        return Single.create {
            val futureTarget = Glide.with(App.context)
                    .load("https://robohash.org/$robotName")
                    .submit()

            val drawable = futureTarget.get()

            it.onSuccess(Robot(robotName, drawable))
        }
    }
}