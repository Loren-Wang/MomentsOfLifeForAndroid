package com.moments_of_life.android.utils

import android.lorenwang.tools.app.AtlwSharedPrefUtils
import com.moments_of_life.android.BuildConfig
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.config.NetRepCodeList.USER_LOGIN_STATUS_LOSE_EFFICACY
import com.moments_of_life.base.bean.response.UserInfoRepBean
import com.moments_of_life.base.utils.MolNetDataTransmitUtils
import javabase.lorenwang.dataparse.JdplwJsonUtils
import kotlinbase.lorenwang.tools.extend.emptyCheck
import kotlinbase.lorenwang.tools.extend.toJsonData

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
    /**
     * 用户信息存储key
     */
    private val userInfoSaveKey = "user_info"
    /**
     * 用户信息
     */
    private var userInfoBean: UserInfoRepBean? = null

    init {
        //读取用户信息
        var infoStr = AtlwSharedPrefUtils.getInstance().getString(userInfoSaveKey, "")
        if (!infoStr.isNullOrEmpty()) {
            //数据不为空，进行数据解密
            infoStr = MolNetDataTransmitUtils.instance.decodeData(infoStr, !BuildConfig.DEBUG)
            //数据解密成功，开始做数据转换
            userInfoBean = JdplwJsonUtils.fromJson(infoStr, UserInfoRepBean::class.java)
        }
    }

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
        return if (userInfoBean != null) {
            true
        } else {
            activity.userLoginStatusError(USER_LOGIN_STATUS_LOSE_EFFICACY, null)
            false
        }
    }

    /**
     * 清理用户信息并去登录
     * @param activity activity实例
     */
    fun clearUserInfoAndToLogin(activity: BaseActivity) {
        this.userInfoBean = null
        AtlwSharedPrefUtils.getInstance().putString(userInfoSaveKey, "")
        activity.userLoginStatusError(USER_LOGIN_STATUS_LOSE_EFFICACY, null)
    }

    /**
     * 更新用户信息
     */
    fun upDateUserInfo(infoBean: UserInfoRepBean) {
        //存储数据并进行加密（正式环境下加密）
        AtlwSharedPrefUtils.getInstance().putString(
            userInfoSaveKey,
            MolNetDataTransmitUtils.instance.encryptData(infoBean.toJsonData(), !BuildConfig.DEBUG)
        )
        //变量数据存储
        this.userInfoBean = infoBean
    }

}
