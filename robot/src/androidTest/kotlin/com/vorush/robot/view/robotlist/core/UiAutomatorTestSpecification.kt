/*
 * Copyright (C) 2019 William Hill. All rights reserved.
 *
 *  This software is the confidential and proprietary information of William Hill or one of its
 *  subsidiaries. You shall not disclose this confidential information and shall use it only in
 *  accordance with the terms of the license agreement or other applicable agreement you entered into
 *  with William Hill.
 *
 *  WILLIAM HILL MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WILLIAM HILL SHALL NOT BE LIABLE FOR ANY LOSSES
 *  OR DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 *  ITS DERIVATIVES.
 */

package com.vorush.robot.view.robotlist.core

import android.content.Context
import android.support.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

abstract class UiAutomatorTestSpecification {

    private val instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }
    protected val device: UiDevice by lazy { UiDevice.getInstance(instrumentation) }
    protected val context: Context by lazy { instrumentation.targetContext }
}