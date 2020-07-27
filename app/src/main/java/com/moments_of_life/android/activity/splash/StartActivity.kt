package com.moments_of_life.android.activity.splash

import android.lorenwang.commonbaseframe.mvp.AcbflwNetRepCode.repCodeLoginStatusError
import android.lorenwang.commonbaseframe.mvp.AcbflwNetRepCode.repCodeSuccess
import android.lorenwang.commonbaseframe.network.AcbflwNetworkManager
import android.lorenwang.tools.AtlwConfig
import android.lorenwang.tools.app.AtlwActivityJumpUtils
import android.lorenwang.tools.app.AtlwThreadUtils
import android.os.Bundle
import com.moments_of_life.android.BuildConfig
import com.moments_of_life.android.R
import com.moments_of_life.android.activity.MainActivity
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.utils.UserInfoUtils


class StartActivity : BaseActivity() {

    /**
     * 初始化view
     */
    override fun initView(savedInstanceState: Bundle?) {
        addContentView(R.layout.activity_start)
        //初始化网络请求
        AcbflwNetworkManager.instance.initRetrofit(
            BuildConfig.APP_COMPILE_TYPE,
            "https://qtoolsapp.qtoolsbaby.cn/",
            "http://qtoolsapp-hd.qtoolsbaby.cn/",
            "http://qtoolsapp-hd.qtoolsbaby.cn/",
            null, null, null
        )
//        //初始化插件参数
//        AcbflwPluginUtils.getInstance().initWeChatConfigInfo(
//                new AcbflwWeChatConfigInfoBean.Build()
//                        .setAppid("BuildConfig.WEIXIN_ID")
//                        .setWeChatApplyId("BuildConfig.WEIXIN_MINI")
//                        .setWeiChatSecret("BuildConfig.WEIXIN_SECRET")
//                        .setWeChatId("BuildConfig.WEIXIN_ID")
//                        .setCheckSignature(true)
//                        .build());
        //配置全局参数
        repCodeSuccess = 200
        val loginStatusError: ArrayList<Any> = ArrayList()
        repCodeLoginStatusError = loginStatusError


        AtlwThreadUtils.getInstance().postOnUiThreadDelayed({
            if (UserInfoUtils.instance.loginStatusCheck(this)) {
                AtlwActivityJumpUtils.getInstance().jump(this, MainActivity::class.java)
            }
            finish()
        }, 100)
    }
}
