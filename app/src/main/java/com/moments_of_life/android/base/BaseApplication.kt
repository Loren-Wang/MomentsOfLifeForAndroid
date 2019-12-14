package com.moments_of_life.android.base

import android.lorenwang.common_base_frame.AcbflwBaseApplication
import com.moments_of_life.android.BuildConfig
import com.moments_of_life.android.base.BaseConfig.APP_INTERCOMPILATION_TYPE_PRO
import es.dmoral.toasty.Toasty

/**
 * 功能作用：
 * 创建时间：2019-12-14 下午 22:23:56
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class BaseApplication : AcbflwBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setStatus(BuildConfig.APP_INTERCOMPILATION_TYPE == APP_INTERCOMPILATION_TYPE_PRO)
    }
}
