package com.slesarew.robot.view.robotlist.core

import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

internal fun isRecyclerEmpty() = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {}

    override fun matchesSafely(view: View): Boolean {
        if (view is RecyclerView) {
            return requireNotNull(view.adapter).itemCount == 0
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