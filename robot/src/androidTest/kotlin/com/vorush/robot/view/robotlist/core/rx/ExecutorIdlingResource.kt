package com.vorush.robot.view.robotlist.core.rx

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.IdlingResource.ResourceCallback
import com.vorush.robot.view.robotlist.core.rx.ExecutorIdlingResource.Companion.CORE_POOL
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

object ExecutorIdlingResource :
        ScheduledThreadPoolExecutor(CORE_POOL),
        IdlingResource {

    private var wasPreviouslyIdle = true
    // We are not able to unambiguously determine when the executor is in idle state
    // so we have to poll to check current queue and activeCount state in order to notify espresso
    // that we are idle.
    private val idleCheckExecutor = ScheduledThreadPoolExecutor(1)

    init {
        idleCheckExecutor.scheduleAtFixedRate({ isIdleNow }, 0, 200, TimeUnit.MILLISECONDS)
    }

    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
    private val MAX_POOL_SIZE = CPU_COUNT * 2 + 1

    object Companion {
        val CORE_POOL: Int = Math.max(2, Math.min(CPU_COUNT - 1, 4))
    }

    private var callback: ResourceCallback? = null

    init {
        maximumPoolSize = MAX_POOL_SIZE
    }

    override fun getName() = "RX_SCHEDULERS_IDLING_RESOURCE"

    override fun isIdleNow(): Boolean {
        val isIdle = queue.isEmpty() && activeCount == 0
        if (isIdle && !wasPreviouslyIdle) {
            callback?.onTransitionToIdle()
        }
        wasPreviouslyIdle = isIdle
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        ExecutorIdlingResource.callback = callback
    }

    override fun afterExecute(r: Runnable?, t: Throwable?) {
        super.afterExecute(r, t)
        isIdleNow
    }

    override fun remove(task: Runnable?): Boolean {
        isIdleNow
        return super.remove(task)
    }

    override fun shutdownNow(): MutableList<Runnable> {
        isIdleNow
        return super.shutdownNow()
    }

    override fun shutdown() {
        isIdleNow
        super.shutdown()
    }
}