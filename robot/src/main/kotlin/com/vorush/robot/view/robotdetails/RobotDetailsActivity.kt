package com.vorush.robot.view.robotdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.vorush.robot.R
import com.vorush.robot.view.Robot
import kotlinx.android.synthetic.main.activity_robot_details.robot_image as robotImage

class RobotDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_robot_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        extras?.let {
            val name = Robot.name(it)

            title = name

            robotImage.contentDescription = name
            Glide.with(robotImage)
                    .load("https://robohash.org/$name")
                    .into(robotImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}