package com.moments_of_life.android.utils

import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.config.NetRepCodeList.USER_LOGIN_STATUS_LOSE_EFFICACY

/**
 * 功能作用：用户数据单例类
 * 创建时间：2019-12-14 上午 00:15:41
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class UserInfoUtils private constructor() {
    companion object {
        private var optionsInstance: UserInfoUtils? = null
        val instance: UserInfoUtils
            get() {
                if (optionsInstance == null) {
                    synchronized(this::class.java) {
                        if (optionsInstance == null) {
                            optionsInstance = UserInfoUtils()
                        }
                    }
                }
                return optionsInstance!!
            }
    }

    /**
     * 登录状态检测
     * @return true:检测通过，false：检测失败同时会回调Baseview中登录状态异常
     */
    fun loginStatusCheck(activity: BaseActivity): Boolean {
        activity.userLoginStatusError(USER_LOGIN_STATUS_LOSE_EFFICACY, null)
        return false
    }

}
