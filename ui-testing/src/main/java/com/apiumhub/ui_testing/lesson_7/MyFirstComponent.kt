package com.apiumhub.ui_testing.lesson_7

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.my_first_component.view.*

class MyFirstComponent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.my_first_component, this)
        firstComponentBtn.setOnClickListener {
            if (firstComponentBtn.text == "Follow") {
                firstComponentBtn.text = "Following"
            } else {
                firstComponentBtn.text = "Follow"
            }
        }
    }


}