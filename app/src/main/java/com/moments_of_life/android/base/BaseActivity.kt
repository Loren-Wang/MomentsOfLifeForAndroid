package com.moments_of_life.android.base

import android.lorenwang.commonbaseframe.AcbflwBaseActivity
import android.lorenwang.tools.app.AtlwActivityJumpUtils
import android.lorenwang.tools.app.AtlwToastHintUtils
import com.moments_of_life.android.R
import com.moments_of_life.android.activity.user.LoginActivity
import com.moments_of_life.android.dialog.LoadingDialog
import com.moments_of_life.android.utils.ToastUtils
import javabase.lorenwang.dataparse.JdplwJsonUtils
import javabase.lorenwang.tools.common.JtlwCheckVariateUtils
import kotlinbase.lorenwang.tools.common.bean.KttlwBaseNetResponseBean

/**
 * 功能作用：基础Activity
 * 创建时间：2019-12-13 下午 22:46:3
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
abstract class BaseActivity : AcbflwBaseActivity() {
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
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        if (!loadingDialog!!.isShowing) {
            loadingDialog!!.show(allowLoadingBackFinishPage)
        }
    }

    override fun userLoginStatusError(code: Any?, message: String?) {
        hideBaseLoading()
        AtlwActivityJumpUtils.getInstance().jump(this, LoginActivity::class.java)
    }

    override fun netReqFail(netOptionReqCode: Int, code: Any?, message: String?) {
        if (JtlwCheckVariateUtils.getInstance().isNotEmpty(message)) {
            val bean = JdplwJsonUtils.fromJson(
                message,
                KttlwBaseNetResponseBean::class.java
            )
            if (bean != null) {
                if (JtlwCheckVariateUtils.getInstance().isNotEmpty(bean.stateMessage)) {
                    ToastUtils.instance.errorHint(bean.stateMessage!!)
                }
            } else {
                ToastUtils.instance.errorHint(message!!)
            }
        }
    }

    /**
     * 网络请求成功
     *
     * @param data             响应数据
     * @param netOptionReqCode 网络操作请求code
     */
    override fun <T> netReqSuccess(netOptionReqCode: Int, data: T) {}
}
