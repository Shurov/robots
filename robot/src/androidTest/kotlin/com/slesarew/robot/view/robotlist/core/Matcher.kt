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

import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

internal fun isRecyclerEmpty() = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {}

    override fun matchesSafely(view: View): Boolean {
        if (view is RecyclerView) {
            return view.adapter.itemCount == 0
        }
        return false
    }
}

internal fun recyclerHasItemWithText(text: String) = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {}

    override fun matchesSafely(view: View): Boolean {
        if (view is RecyclerView) {
            return RecyclerViewHelper.getViewHolders(view).any { it.text == text }
        }
        return false
    }
}