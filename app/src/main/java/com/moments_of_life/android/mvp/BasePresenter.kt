package com.moments_of_life.android.mvp

import android.lorenwang.common_base_frame.mvp.AcbflwBasePresenter
import android.lorenwang.common_base_frame.network.callback.AcbflwNetOptionsByModelCallback
import android.lorenwang.common_base_frame.network.callback.AcbflwRepDataOptionsCallback
import android.lorenwang.common_base_frame.network.callback.AcbflwRepOptionsByPresenterCallback
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.net.BaseNetOptionsByModelCallback
import com.moments_of_life.android.net.BaseRepDataOptionsCallback
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：基础BasePresenter
 * 创建时间：2020-02-18 下午 19:14:53
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
abstract class BasePresenter(activity: BaseActivity) : AcbflwBasePresenter(activity = activity) {
    fun <D> getModelCallback(repOptionsCallback: BaseRepDataOptionsCallback<D>): AcbflwNetOptionsByModelCallback<D, KttlwBaseNetResponseBean<D>> {
        return this.getNetOptionsCallback(repOptionsCallback)
    }
}
