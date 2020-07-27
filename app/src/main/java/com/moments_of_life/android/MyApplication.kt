package com.moments_of_life.android

import android.lorenwang.commonbaseframe.AcbflwBaseApplication


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
class MyApplication : AcbflwBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //设置编译模式
        setCompileType(BuildConfig.APP_COMPILE_TYPE)
    }
}

