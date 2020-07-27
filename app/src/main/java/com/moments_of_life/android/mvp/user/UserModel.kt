package com.moments_of_life.android.mvp.user

import android.app.Activity
import android.lorenwang.commonbaseframe.mvp.AcbflwBaseModel
import android.lorenwang.commonbaseframe.network.AcbflwNetworkManager
import android.lorenwang.commonbaseframe.network.callback.AcbflwNetOptionsByModelCallback
import com.moments_of_life.base.bean.request.user.UserLoginReqBean
import com.moments_of_life.base.bean.response.UserInfoRepBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：用户model
 * 创建时间：2020-02-15 下午 13:19:46
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class UserModel : AcbflwBaseModel() {
    /**
     * 发起登录请求
     * @param act 页面实例
     * @param account 账户
     * @param verificationCode 验证码
     * @param netOptionsCallback 网络请求回调
     */
    fun login(act:Activity?,
        account: String, verificationCode: String,
        callback: AcbflwNetOptionsByModelCallback<UserInfoRepBean, KttlwBaseNetResponseBean<UserInfoRepBean>>
    ) {
        val reqBean = UserLoginReqBean()
        reqBean.phoneNum = account
        reqBean.verificationCode = verificationCode
        AcbflwNetworkManager.instance.create(UserApi::class.java)?.login(reqBean)
            ?.subscribeOn(Schedulers.io())           //在IO线程进行网络请求
            ?.observeOn(AndroidSchedulers.mainThread())//回到主线程处理请求结果
            ?.subscribe(getBaseObserver(act,null, null, callback));
    }
}
