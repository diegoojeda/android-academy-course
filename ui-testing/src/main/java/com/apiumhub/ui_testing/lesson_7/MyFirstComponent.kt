package com.apiumhub.ui_testing.lesson_7

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.apiumhub.ui_testing.R
import kotlinx.android.synthetic.main.my_first_component.view.*

class MyFirstComponent @JvmOverloads constructor
    (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var username: CharSequence
        get() = firstComponentUsername.text
        set(value) {
            firstComponentUsername.text = value
            firstComponentDescription.text = "Description for $value"
        }

    init {
        View.inflate(context, R.layout.my_first_component, this)
        firstComponentBtn.setOnClickListener {
            firstComponentBtn.text = if (firstComponentBtn.text == "Follow") "Unfollow" else "Follow"
        }
    }


}