package com.moments_of_life.android.mvp

import android.lorenwang.common_base_frame.mvp.AcbflwBasePresenter
import android.lorenwang.common_base_frame.network.AcbflwRepDataOptionsCallback
import android.lorenwang.common_base_frame.network.bean.AcbflwBaseRepBean
import com.moments_of_life.android.base.BaseActivity
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
open class VerificationCodePresenter(activity: BaseActivity) : AcbflwBasePresenter(activity) {
    override fun releasePresenterChild() {

    }

    /**
     * 发送登录验证码
     */
    fun sendLogin(phoneNum: String, reqCode: Int) {
        JtlwClassUtils.getInstance().getClassEntity(VerificationCodeModel::class.java)
            .sendLogin(phoneNum, getNetOptionsCallback(object : AcbflwRepDataOptionsCallback<AcbflwBaseRepBean<Any?>> {
                override fun repDataError(code: Any?, message: String?) {
                    activity.netReqFail(reqCode, code, message)
                }

                override fun viewOptionsData(data: AcbflwBaseRepBean<Any?>?) {
                    activity.netReqSuccess(reqCode, data)
                }
            }))
    }
}
