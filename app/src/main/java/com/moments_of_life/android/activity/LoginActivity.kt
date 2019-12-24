package com.moments_of_life.android.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.utils.ToastUtils
import javabase.lorenwang.tools.MatchesRegularCommon
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private val phoneRegex = Regex(MatchesRegularCommon.EXP_MOBILE)

    override fun initChildView(savedInstanceState: Bundle?) {
        super.initChildView(savedInstanceState)
        addContentView(R.layout.activity_login)
    }

    override fun initListener() {
        super.initListener()
        edtAccount.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        btnGetVerificationCode.setOnClickListener {
            it.isEnabled = false
            //开始检测手机号
            val accountInput = edtAccount.text.toString()
            if (!accountInput.matches(phoneRegex)) {
                it.isEnabled = true
                ToastUtils.instance.editErrorHint(R.string.page_login_error_input_account)
                return@setOnClickListener
            }
            showBaseLoading(false)

        }
        btnLogin.setOnClickListener {
            it.isEnabled = false
            //开始检测手机号
            val accountInput = edtAccount.text.toString()
            if (!accountInput.matches(phoneRegex)) {
                it.isEnabled = true
                ToastUtils.instance.editErrorHint(R.string.page_login_error_input_account)
                return@setOnClickListener
            }
            //开始检测验证码
            val verificationCode = edtVerificationCode.text.toString()
            if (verificationCode.length != 6) {
                it.isEnabled = true
                ToastUtils.instance.editErrorHint(R.string.page_login_error_input_verification_code)
                return@setOnClickListener
            }
        }
    }
}
