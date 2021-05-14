package com.antony.mybasepackage.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.antony.mybasepackage.view.listeners.CustomClickListener

/**
 * Created By antony on 6/17/2019.
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.toggle() {
    visibility = when (visibility) {
        View.VISIBLE -> View.GONE
        else -> View.VISIBLE
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.setCustomClickListener(
    click: () -> Unit
) {
    val listener = object : CustomClickListener {
        override fun onClicked(v: View?) {
            click()
        }
    }
    this.setOnClickListener(listener)
}

fun <T : ViewDataBinding?> AppCompatActivity.getViewBinding(@LayoutRes resId: Int): T {
    return DataBindingUtil.setContentView<T>(this, resId)
}

inline fun <reified T : ViewDataBinding> Fragment.viewBinding(): FragViewBinder<T> {
    return FragViewBinder {
        T::class.java.getMethod("bind", View::class.java).invoke(null, this.requireView()) as T
    }
}

