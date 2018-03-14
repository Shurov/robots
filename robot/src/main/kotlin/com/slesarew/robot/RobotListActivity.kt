package com.slesarew.robot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu


class RobotListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_robot_list)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_robot_list, menu)
        return true
    }
}