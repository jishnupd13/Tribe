package com.antony.mybasepackage.view.listeners

import android.os.Handler
import android.view.View

interface CustomClickListener : View.OnClickListener {

    fun onClicked(v: View?)

    override fun onClick(v: View?) {
        v?.isClickable = false
        Handler().postDelayed({ v?.isClickable = true }, 500)
        onClicked(v)
    }
}