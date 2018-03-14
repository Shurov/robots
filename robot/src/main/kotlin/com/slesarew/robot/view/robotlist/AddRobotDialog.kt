package com.slesarew.robot.view.robotlist

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View.inflate
import android.widget.EditText
import com.slesarew.robot.R
import kotlinx.android.synthetic.main.add_robot.robot_input as robotListText

class AddRobotDialog : DialogFragment() {

    private lateinit var robotListener: OnRobotAddedListener

    interface OnRobotAddedListener {

        fun onRobotAdded(name: String)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            robotListener = it as OnRobotAddedListener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = inflate(context, R.layout.add_robot, null)
        val robotInput = view.findViewById<EditText>(R.id.robot_input)

        return AlertDialog.Builder(context!!)
                .setTitle(R.string.add_robot_title)
                .setMessage(R.string.add_robot_description)
                .setView(view)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    robotListener.onRobotAdded(robotInput.text.toString())
                })
                .setNegativeButton(android.R.string.cancel, { _, _ -> })
                .create()
    }
}