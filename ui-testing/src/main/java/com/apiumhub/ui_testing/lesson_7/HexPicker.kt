package com.apiumhub.ui_testing.lesson_7

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.hex_picker.view.*

class HexPicker @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    @ColorRes
    fun getColor(): Int = (hexView.background as? ColorDrawable)?.color ?: 0

    init {
        LayoutInflater.from(context).inflate(R.layout.hex_picker, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HexPicker, defStyleAttr, 0)
        val color = attributes.getColor(R.styleable.HexPicker_color, ContextCompat.getColor(context, R.color.colorPrimary))
        attributes.recycle()

        hexView.setBackgroundColor(color)
    }
}