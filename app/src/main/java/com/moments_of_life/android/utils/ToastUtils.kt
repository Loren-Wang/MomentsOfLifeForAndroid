package com.moments_of_life.android.utils

import android.lorenwang.common_base_frame.AcbflwBaseApplication
import android.lorenwang.common_base_frame.AcbflwBaseConfig
import androidx.annotation.StringRes
import es.dmoral.toasty.MyToast

/**
 * 功能作用：toast提示单例
 * 创建时间：2019-12-14 下午 22:22:20
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class ToastUtils private constructor() {
    companion object {
        private var optionsInstance: ToastUtils? = null
        val instance: ToastUtils
            get() {
                if (optionsInstance == null) {
                    synchronized(this::class.java) {
                        if (optionsInstance == null) {
                            optionsInstance = ToastUtils()
                        }
                    }
                }
                return optionsInstance!!
            }
    }

    init {
        MyToast.init(AcbflwBaseApplication.application, AcbflwBaseConfig.baseDebugStatus, true)
    }

    /**
     * 错误提示
     */
    fun errorHint(msg: String) {
        MyToast.error(msg)
    }

    /**
     * 成功提示
     */
    fun successHint(msg: String) {
        MyToast.success(msg)
    }

    /**
     * 编辑内容错误提示
     */
    fun successHint(@StringRes resId: Int) {
        MyToast.success(resId)
    }

    /**
     * 编辑内容错误提示
     */
    fun editErrorHint(@StringRes resId: Int) {
        MyToast.error(resId)
    }

    /**
     * 编辑内容错误提示
     */
    fun editErrorHint(msg: String) {
        MyToast.error(msg)
    }

}
