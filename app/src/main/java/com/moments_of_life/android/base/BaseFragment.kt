package com.moments_of_life.android.base

import android.lorenwang.common_base_frame.AcbflwBaseFragment
import androidx.fragment.app.Fragment
import com.moments_of_life.android.view.LoadingDialog

/**
 * 功能作用：基础fragment
 * 创建时间：2020-03-01 17:23
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
abstract class BaseFragment(var activity: BaseActivity) : AcbflwBaseFragment() {
    private var loading: LoadingDialog? = null
    /**
     * 隐藏加载中
     */
    override fun hideBaseLoading() {
        loading?.dismiss()
    }

    /**
     * 网络请求失败
     * @param netOptionReqCode 网络操作请求code
     * @param code 错误码
     * @param message 错误信息
     */
    override fun netReqFail(netOptionReqCode: Int, code: Any?, message: String?) {
        activity.netReqFail(netOptionReqCode, code, message)
    }

    /**
     * 网络请求成功
     * @param data 响应数据
     * @param netOptionReqCode 网络操作请求code
     */
    override fun <T> netReqSuccess(netOptionReqCode: Int, data: T?) {
        activity.netReqSuccess(netOptionReqCode, data)
    }

    /**
     * 显示加载中
     * @param allowLoadingBackFinishPage 是否允许后退结束当前页面
     */
    override fun showBaseLoading(allowLoadingBackFinishPage: Boolean) {
        if (loading == null) {
            synchronized(LoadingDialog::class.java) {
                if (loading == null) {
                    loading = LoadingDialog(activity)
                }
            }
        }
        loading?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }
}
