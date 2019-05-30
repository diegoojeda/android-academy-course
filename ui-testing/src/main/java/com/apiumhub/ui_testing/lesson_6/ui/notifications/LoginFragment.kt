package com.apiumhub.ui_testing.lesson_6.ui.notifications

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apiumhub.ui_testing.R
import com.apiumhub.ui_testing.lesson_6.ui.LoggedInActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_username_et.addTextChangedListener(textWatcher {
            validateUsername(it)
            updateSubmitBtn()
        })
        login_password_et.addTextChangedListener(textWatcher {
            validatePassword(it)
            updateSubmitBtn()
        })
        login_submit_btn.setOnClickListener {
            Handler().postDelayed({
                startActivity(LoggedInActivity.getCallingIntent(context, login_username_et.text.toString()))
            }, 1500)
        }
    }

    private fun textWatcher(action: (String) -> Unit): EmptyTextWatcher = object : EmptyTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            action(s.toString())
        }
    }


    private fun validateUsername(username: String) {
        if (isValidEmail(username)) {
            login_username_et.error = null
        } else {
            login_username_et.error = "Invalid email"
        }
    }

    private fun validatePassword(password: String) {
        if (password.length < 6) {
            login_password_et.error = "Password too short"
        } else {
            login_password_et.error = null
        }
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun updateSubmitBtn() {
        login_submit_btn.isEnabled = login_username_et.error == null && login_password_et.error == null
    }

}

open class EmptyTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}