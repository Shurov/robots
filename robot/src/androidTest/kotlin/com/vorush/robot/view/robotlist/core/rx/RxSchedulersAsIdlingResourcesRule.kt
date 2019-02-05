package com.vorush.robot.view.robotlist.core.rx

import android.support.test.espresso.IdlingRegistry
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

object RxSchedulersAsIdlingResourcesRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            @Throws(Throwable::class)
            override fun evaluate() {
                val scheduler = Schedulers.from(ExecutorIdlingResource)
                RxJavaPlugins.setInitComputationSchedulerHandler { scheduler }
                RxJavaPlugins.setComputationSchedulerHandler { scheduler }
                IdlingRegistry.getInstance().register(ExecutorIdlingResource)
                base.evaluate()
                RxJavaPlugins.reset()
                IdlingRegistry.getInstance().unregister(ExecutorIdlingResource)
            }
        }
    }
}