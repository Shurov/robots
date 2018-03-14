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
import android.util.SparseArray
import android.widget.TextView
import com.slesarew.robot.R

internal object RecyclerViewHelper {

    fun getViewHolders(recycler: RecyclerView): List<ViewHolderData> {
        val holders = arrayListOf<ViewHolderData>()

        val adapter = recycler.adapter
        val cache = SparseArray<RecyclerView.ViewHolder>()

        for (position in 0 until recycler.adapter.itemCount) {
            val itemType = adapter.getItemViewType(position)
            var viewHolder = cache.get(itemType)

            if (viewHolder == null) {
                viewHolder = adapter.createViewHolder(recycler, itemType)
                cache.put(itemType, viewHolder)
            }
            adapter.bindViewHolder(viewHolder, position)

            val itemView = viewHolder?.itemView

            itemView?.let {
                val text = it.findViewById<TextView>(R.id.robot_name)

                holders.add(ViewHolderData(text.text.toString()))
            }
        }
        return holders
    }
}

internal data class ViewHolderData(val text: String)