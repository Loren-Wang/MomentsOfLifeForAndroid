package com.moments_of_life.android.mvp.user

import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.mvp.BasePresenter
import com.moments_of_life.android.net.BaseRepDataOptionsCallback
import com.moments_of_life.android.utils.UserInfoUtils
import com.moments_of_life.base.bean.response.UserInfoRepBean
import javabase.lorenwang.tools.common.JtlwClassUtils

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
class UserPresenter(activity: BaseActivity) : BasePresenter(activity) {
    override fun releasePresenterChild() {
    }

    /**
     * 登录操作
     * @param account 账户
     * @param verificationCode 验证码
     * @param reqCode 网络请求code
     */
    fun login(account: String, verificationCode: String, reqCode: Int) {
        JtlwClassUtils.getInstance().getClassEntity(UserModel::class.java)
            .login(
                account,
                verificationCode,
                getModelCallback(object : BaseRepDataOptionsCallback<UserInfoRepBean>(false) {
                    override fun repDataError(code: Any?, message: String?) {
                        activity.netReqFail(reqCode, code, message)
                    }

                    override fun repOptionsData(data: UserInfoRepBean) {
                        super.repOptionsData(data)
                        UserInfoUtils.instance.upDateUserInfo(data)
                        activity.netReqSuccess(reqCode, data)
                    }
                })
            )
    }

}
