package com.moments_of_life.android.activity

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.lorenwang.tools.app.AtlwActivityJumpUtils
import android.lorenwang.tools.app.AtlwViewUtils
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.moments_of_life.android.MainActivity
import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.mvp.user.UserPresenter
import com.moments_of_life.android.mvp.verificationCode.VerificationCodePresenter
import com.moments_of_life.android.utils.ToastUtils
import javabase.lorenwang.tools.MatchesRegularCommon
import javabase.lorenwang.tools.thread.CountDownCallback
import javabase.lorenwang.tools.thread.JtlwTimerUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private val phoneRegex = Regex(MatchesRegularCommon.EXP_MOBILE)
    private lateinit var optionsPresenter: VerificationCodePresenter
    /**
     * 用户操作
     */
    private lateinit var userPresenter: UserPresenter
    /**
     * 允许操作验证码发送颜色
     */
    private lateinit var getVerificationCodeY: ColorStateList
    /**
     * 禁止操作验证码发送颜色
     */
    private lateinit var getVerificationCodeN: ColorStateList
    /**
     * 网络请求验证码请求
     */
    private val netRequestCodeVerification = 0
    /**
     * 网络请求登录请求
     */
    private val netRequestCodeLogin = 1
    /**
     * 倒计时回调
     */
    private val countDownCallback = object : CountDownCallback {
        @SuppressLint("SetTextI18n")
        override fun countDownTime(sumTime: Long, nowTime: Long) {
            runOnUiThread {
                btnGetVerificationCode.text = "${nowTime / 1000}s"
            }
        }

        override fun finish() {
            runOnUiThread {
                btnGetVerificationCode.setText(R.string.page_login_button_get_verification_code)
                btnGetVerificationCode.isEnabled = true
                AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeY)
            }
        }
    };

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        addContentView(R.layout.activity_login)
        optionsPresenter = VerificationCodePresenter(this)
        userPresenter = UserPresenter(this)
        getVerificationCodeY = getColorStateList(R.color.colorAccent)
        getVerificationCodeN = getColorStateList(R.color.defaultButtonDisableColor)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        super.initListener(savedInstanceState)
        //文本改变监听
        edtAccount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    btnGetVerificationCode.setText(R.string.page_login_button_get_verification_code)
                    if (it.matches(phoneRegex)) {
                        if (!btnGetVerificationCode.isEnabled) {
                            btnGetVerificationCode.isEnabled = true
                            AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeY)
                        }
                    } else {
                        if (btnGetVerificationCode.isEnabled) {
                            btnGetVerificationCode.isEnabled = false
                            AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeN)
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        edtVerificationCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    btnLogin.setText(R.string.page_login_button_login)
                    if (it.length == 4) {
                        if (!btnLogin.isEnabled) {
                            btnLogin.isEnabled = true
                            AtlwViewUtils.getInstance().setBackgroundTint(btnLogin, getVerificationCodeY)
                        }
                    } else {
                        if (btnLogin.isEnabled) {
                            btnLogin.isEnabled = false
                            AtlwViewUtils.getInstance().setBackgroundTint(btnLogin, getVerificationCodeN)
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        //发送验证码点击
        btnGetVerificationCode.setOnClickListener {
            it.isEnabled = false
            AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeN)
            //开始检测手机号
            val accountInput = edtAccount.text.toString()
            if (!accountInput.matches(phoneRegex)) {
                it.isEnabled = true
                AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeY)
                ToastUtils.instance.editErrorHint(R.string.page_login_error_input_account)
                return@setOnClickListener
            }
            optionsPresenter.sendLogin(accountInput, netRequestCodeVerification)
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
            if (verificationCode.length != 4) {
                it.isEnabled = true
                ToastUtils.instance.editErrorHint(R.string.page_login_error_input_verification_code)
                return@setOnClickListener
            }
            userPresenter.login(accountInput, verificationCode, netRequestCodeLogin)
        }
    }

    override fun <T> netReqSuccess(netOptionReqCode: Int, data: T?) {
        super.netReqSuccess(netOptionReqCode, data)
        when (netOptionReqCode) {
            //验证码发送成功
            netRequestCodeVerification -> {
                ToastUtils.instance.successHint(R.string.page_login_success_send_verification_code)
                //开启倒计时
                JtlwTimerUtils.getInstance().countDownTask(1, countDownCallback, 60000, 1000);
            }
            netRequestCodeLogin -> {
                ToastUtils.instance.successHint(R.string.page_login_success_login)
                AtlwActivityJumpUtils.getInstance().jump(this, MainActivity::class.java)
            }
            else -> {

            }
        }
    }

    override fun netReqFail(netOptionReqCode: Int, code: Any?, message: String?) {
        super.netReqFail(netOptionReqCode, code, message)
        when (netOptionReqCode) {
            //验证码发送成功
            netRequestCodeVerification -> {
                btnGetVerificationCode.setText(R.string.page_login_button_get_verification_code)
                btnGetVerificationCode.isEnabled = true
                AtlwViewUtils.getInstance().setBackgroundTint(btnGetVerificationCode, getVerificationCodeY)
            }
            netRequestCodeLogin -> {
                btnLogin.isEnabled = true
            }
            else -> {

            }
        }
    }

}
