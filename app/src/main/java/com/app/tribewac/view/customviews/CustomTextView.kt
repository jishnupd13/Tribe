package com.app.tribewac.view.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.app.tribewac.R

/**
 * Created By antony on 10/15/2019.
 */
class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        applyCustomFont(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val values = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val fontThickness = values.getInt(R.styleable.CustomTextView_customFontThickness, 0)
        values.recycle()
        when (fontThickness) {
            1 -> setBold(context)
            2 -> setThin(context)
            else -> applyCustomFont(context)
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val values = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val fontThickness = values.getInt(R.styleable.CustomTextView_customFontThickness, 0)
        values.recycle()
        when (fontThickness) {
            1 -> setBold(context)
            2 -> setThin(context)
            else -> applyCustomFont(context)
        }
    }

    private fun applyCustomFont(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "fonts/roboto_regular.ttf")
        this.typeface = face
    }

    private fun setBold(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "fonts/roboto_medium.ttf")
        this.typeface = face
    }

    private fun setThin(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "fonts/roboto_light.ttf")
        this.typeface = face
    }
}