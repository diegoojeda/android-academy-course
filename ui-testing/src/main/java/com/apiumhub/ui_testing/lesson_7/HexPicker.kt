package com.apiumhub.ui_testing.lesson_7

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.hex_picker.view.*
import java.lang.NumberFormatException

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

        hexEt.addTextChangedListener(SimpleTextWatcher {
            try {
                hexView.setBackgroundColor(Color.parseColor(it))
            } catch (ex: Exception) {
                Log.e("Component", "Invalid color")
            }
        })
    }
}

class SimpleTextWatcher(private val textChanged: (String) -> Unit): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        textChanged(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}