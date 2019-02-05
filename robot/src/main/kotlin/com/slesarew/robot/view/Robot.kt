package com.slesarew.robot.view

import android.graphics.drawable.Drawable
import android.os.Bundle

private const val KEY = "KEY"

data class Robot(val name: String,
                 val image: Drawable?) {

    companion object {

        fun name(bundle: Bundle): String? {
            return bundle.getString(KEY)
        }

        fun bundle(name: String): Bundle {
            val bundle = Bundle()
            bundle.putString(KEY, name)
            return bundle
        }
    }
}