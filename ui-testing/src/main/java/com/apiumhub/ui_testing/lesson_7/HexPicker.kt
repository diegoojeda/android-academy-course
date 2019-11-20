package com.apiumhub.ui_testing.lesson_7

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.hex_picker.view.*

class HexPicker @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.hex_picker, this, true)

        runCatching  {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HexPicker, defStyleAttr, 0)
            val color = typedArray.getColor(R.styleable.HexPicker_color, 0)
            hexView.setBackgroundColor(color)
            typedArray.recycle()
        }

        hexEt.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                runCatching {
                    hexView.background = ColorDrawable(Color.parseColor(s.toString()))
                }
            }
        })

    }
}

open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}