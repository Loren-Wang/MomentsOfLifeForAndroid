package com.moments_of_life.android.base

import android.lorenwang.commonbaseframe.AcbflwBaseFragment
import android.lorenwang.commonbaseframe.mvp.AcbflwBaseView
import com.moments_of_life.android.R
import com.moments_of_life.android.dialog.LoadingDialog

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
abstract class BaseFragment : AcbflwBaseFragment() {
    private var loadingDialog: LoadingDialog? = null
    override fun hideBaseLoading() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
        }
    }

    override fun addContentView(resId: Int) {
        super.addContentView(resId, R.layout.title_bar_head_view_type_1)
    }

    /**
     * 显示空数据
     */
    protected fun showEmptyData() {
        super.showEmptyData(R.layout.empty_data_default)
    }

    override fun showBaseLoading(allowLoadingBackFinishPage: Boolean) {
        if (loadingDialog == null && activity != null) {
            loadingDialog = LoadingDialog(activity)
        }
        if (loadingDialog != null && !loadingDialog!!.isShowing) {
            loadingDialog!!.show(allowLoadingBackFinishPage)
        }
    }

    override fun userLoginStatusError(code: Any?, message: String?) {
        hideBaseLoading()
        if (activity != null && activity is AcbflwBaseView) {
            (activity as AcbflwBaseView?)!!.userLoginStatusError(code, message)
        }
    }

    override fun netReqFail(netOptionReqCode: Int, code: Any?, message: String?) {}

    /**
     * 网络请求成功
     *
     * @param data             响应数据
     * @param netOptionReqCode 网络操作请求code
     */
    override fun <T> netReqSuccess(netOptionReqCode: Int, data: T) {}
}
