package com.vorush.robot.view.robotlist.core

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.widget.TextView
import com.vorush.robot.R

internal object RecyclerViewHelper {

    fun getViewHolders(recycler: RecyclerView): List<ViewHolderData> {
        val holders = arrayListOf<ViewHolderData>()

        val adapter = requireNotNull(recycler.adapter)
        val cache = SparseArray<RecyclerView.ViewHolder>()

        for (position in 0 until requireNotNull(recycler.adapter).itemCount) {
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