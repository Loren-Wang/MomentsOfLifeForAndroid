package com.moments_of_life.android.mvp.verificationCode

import android.lorenwang.common_base_frame.network.AcbflwNetworkManager
import android.lorenwang.common_base_frame.network.callback.AcbflwNetOptionsByModelCallback
import com.moments_of_life.android.bean.request.VerificationCodeBean
import com.moments_of_life.android.mvp.BaseModel
import com.moments_of_life.android.net.BaseNetOptionsByModelCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：验证码model
 * 创建时间：2019-12-29 下午 18:45:26
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
open class VerificationCodeModel : BaseModel() {
    /**
     * 登录验证码
     */
    fun sendLogin(phoneNum: String, callback: AcbflwNetOptionsByModelCallback<Any, KttlwBaseNetResponseBean<Any>>) {
        AcbflwNetworkManager.instance
            .create(VerificationCodeApi::class.java)
            ?.sendLogin(VerificationCodeBean(phoneNum))
            ?.subscribeOn(Schedulers.io())           //在IO线程进行网络请求
            ?.observeOn(AndroidSchedulers.mainThread())//回到主线程处理请求结果
            ?.subscribe(getBaseObserver(null, null, callback));
    }
}
