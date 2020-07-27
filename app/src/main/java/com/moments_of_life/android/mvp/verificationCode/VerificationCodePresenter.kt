package com.moments_of_life.android.mvp.verificationCode

import android.lorenwang.commonbaseframe.mvp.AcbflwBasePresenter
import android.lorenwang.commonbaseframe.network.callback.AcbflwRepOptionsByPresenterCallback
import com.moments_of_life.android.base.BaseActivity
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

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
        getModel(VerificationCodeModel::class.java)
            .sendLogin(
                activity, phoneNum, getNetOptionsCallback(reqCode, true,
                    object : AcbflwRepOptionsByPresenterCallback<KttlwBaseNetResponseBean<Any>> {
                        /**
                         * 响应数据异常
                         * @param code 错误码
                         */
                        override fun repDataError(code: Any?, message: String?) {
                            baseView.netReqFail(reqCode, code, message)
                        }

                        /**
                         * 返回view操作数据
                         */
                        override fun viewOptionsData(data: KttlwBaseNetResponseBean<Any>) {
                            baseView.netReqSuccess(reqCode, null)
                        }

                    })
            )
    }
}
