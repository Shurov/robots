/*
 * Copyright (C) 2017 William Hill. All rights reserved.
 *
 * This software is the confidential and proprietary information of William Hill or one of its
 * subsidiaries. You shall not disclose this confidential information and shall use it only in
 * accordance with the terms of the license agreement or other applicable agreement you entered into
 * with William Hill.
 *
 * WILLIAM HILL MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WILLIAM HILL SHALL NOT BE LIABLE FOR ANY LOSSES
 * OR DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 * ITS DERIVATIVES.
 */

package com.slesarew.robot.view.robotlist.core.rx

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