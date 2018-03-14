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