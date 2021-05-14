package com.antony.mybasepackage.view.listeners

import android.view.View

/**
 * Created By antony on 6/4/2019.
 */
interface AdapterClickListener<T> {

    fun onItemClicked(obj: T, position: Int) {
        //default method implementation
    }

    fun onChildClicked(view: View?, item: T, position: Int) {
        //default method implementation
    }
}