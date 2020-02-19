package com.moments_of_life.android.mvp.verificationCode

import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.mvp.BasePresenter
import com.moments_of_life.android.net.BaseRepDataOptionsCallback
import javabase.lorenwang.tools.common.JtlwClassUtils

/**
 * 功能作用：验证码逻辑处理
 * 创建时间：2019-12-29 下午 18:58:25
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
open class VerificationCodePresenter(activity: BaseActivity) : BasePresenter(activity) {
    override fun releasePresenterChild() {

    }

    /**
     * 发送登录验证码
     */
    fun sendLogin(phoneNum: String, reqCode: Int) {
        JtlwClassUtils.getInstance().getClassEntity(VerificationCodeModel::class.java)
            .sendLogin(phoneNum, getModelCallback(object : BaseRepDataOptionsCallback<Any>(true) {
                override fun repDataError(code: Any?, message: String?) {
                    activity.netReqFail(reqCode, code, message)
                }

                override fun repOptionsData() {
                    super.repOptionsData()
                    activity.netReqSuccess(reqCode, null)
                }
            }))
    }
}
