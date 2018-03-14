package com.slesarew.robot

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        context = base
    }
}