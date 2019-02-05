package com.slesarew.robot.view.robotlist.core

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.not

internal fun ViewInteraction.checkIfExists(message: String) = try {
    check(matches(not(doesNotExist())))
} catch (e: AssertionError) {
    throw AssertionError(message, e)
}

internal fun ViewInteraction.check(viewAssertion: ViewAssertion,
                                   message: String) = try {
    check(viewAssertion)
} catch (e: AssertionError) {
    throw AssertionError(message, e)
}

internal fun ViewInteraction.performWith(viewAction: ViewAction,
                                         message: String) = try {
    perform(viewAction)
} catch (e: AssertionError) {
    throw AssertionError(message, e)
}