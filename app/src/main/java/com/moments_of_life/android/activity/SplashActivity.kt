package com.moments_of_life.android.activity

import android.lorenwang.tools.app.AtlwActivityJumpUtils
import android.lorenwang.tools.app.AtlwThreadUtils
import android.os.Bundle
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.utils.UserInfoUtils

class SplashActivity : BaseActivity() {
    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        AtlwThreadUtils.getInstance().postOnUiThreadDelayed({
            if (UserInfoUtils.instance.loginStatusCheck(this)) {
                AtlwActivityJumpUtils.getInstance().jump(this, MainActivity::class.java)
            }
            finish()
        }, 100)
    }
}
