package com.moments_of_life.android.mvp.user

import android.lorenwang.commonbaseframe.mvp.AcbflwBasePresenter
import android.lorenwang.commonbaseframe.network.callback.AcbflwRepOptionsByPresenterCallback
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.utils.UserInfoUtils
import com.moments_of_life.base.bean.response.UserInfoRepBean
import javabase.lorenwang.tools.common.JtlwClassUtils
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：用户Presenter
 * 创建时间：2020-02-15 下午 13:20:33
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class UserPresenter(activity: BaseActivity) : AcbflwBasePresenter(activity) {
    override fun releasePresenterChild() {
    }

    /**
     * 登录操作
     * @param account 账户
     * @param verificationCode 验证码
     * @param reqCode 网络请求code
     */
    fun login(account: String, verificationCode: String, reqCode: Int) {
        getModel(UserModel::class.java).login(
            activity, account, verificationCode,
            getNetOptionsCallback(reqCode,object : AcbflwRepOptionsByPresenterCallback<KttlwBaseNetResponseBean<UserInfoRepBean>> {
                override fun repDataError(code: Any?, message: String?) {
                    baseView.netReqFail(reqCode, code, message)
                }

                /**
                 * 返回view操作数据
                 */
                override fun viewOptionsData(data: KttlwBaseNetResponseBean<UserInfoRepBean>) {
                    UserInfoUtils.instance.upDateUserInfo(data.data!!)
                    baseView.netReqSuccess(reqCode, data)
                }
            })
        )
    }

}
