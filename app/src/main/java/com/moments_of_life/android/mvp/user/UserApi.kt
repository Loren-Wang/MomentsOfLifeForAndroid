package com.moments_of_life.android.mvp.user

import com.moments_of_life.base.bean.request.user.UserLoginReqBean
import com.moments_of_life.base.bean.response.UserInfoRepBean
import io.reactivex.Observable
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 功能作用：用户相关API
 * 创建时间：2020-02-15 下午 13:19:13
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
interface UserApi {
    @POST("user/login")
    fun login(@Body reqBean: UserLoginReqBean): Observable<Response<KttlwBaseNetResponseBean<UserInfoRepBean>>>
}
