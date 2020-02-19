package com.moments_of_life.android.net

import android.lorenwang.common_base_frame.network.callback.AcbflwRepDataOptionsCallback
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：接口数据响应回调
 * 创建时间：2020-02-18 下午 15:43:56
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
abstract class BaseRepDataOptionsCallback<D>(emptyData: Boolean) : AcbflwRepDataOptionsCallback<D, KttlwBaseNetResponseBean<D>>(emptyData) {
    override fun repOptionsData() {
    }

    override fun repOptionsData(data: D) {
    }
}
