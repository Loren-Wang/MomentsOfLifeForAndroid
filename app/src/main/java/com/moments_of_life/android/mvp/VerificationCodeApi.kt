package com.moments_of_life.android.mvp

import android.lorenwang.common_base_frame.network.bean.AcbflwBaseRepBean
import com.moments_of_life.android.bean.request.VerificationCodeBean
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * 功能作用：验证码接口
 * 创建时间：2019-12-29 下午 18:44:22
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
interface VerificationCodeApi {
    @POST("verificationCode/send/login")
    fun sendLogin(@Body() bean: VerificationCodeBean): Observable<Response<AcbflwBaseRepBean<Any?>>>
}
