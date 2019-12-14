package com.moments_of_life.android.base

import android.lorenwang.common_base_frame.AcbflwBaseActivity
import android.lorenwang.customview.dialog.AvlwBaseDialog
import android.lorenwang.tools.app.AtlwActivityJumpUtils
import android.os.Bundle
import com.moments_of_life.android.activity.LoginActivity
import com.moments_of_life.android.view.LoadingDialog
import kotlinbase.lorenwang.tools.extend.emptyCheck

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
    private var loading: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initChildView(savedInstanceState)
        initListener()
        initData()
    }

    /**
     * 初始化子视图
     */
    open fun initChildView(savedInstanceState: Bundle?) {}

    /**
     * 初始化监听
     */
    open fun initListener() {}

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 隐藏加载中
     */
    override fun hideBaseLoading() {
        loading?.dismiss()
    }

    /**
     * 显示加载中
     */
    override fun showBaseLoading(allowLoadingBackFinishPage: Boolean) {
        if (loading == null) {
            synchronized(LoadingDialog::class.java) {
                if (loading == null) {
                    loading = LoadingDialog(this)
                }
            }
        }
        loading?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    /**
     * 网络请求异常
     */
    override fun netReqFail(netOptionReqCode: Int, code: Any?, message: String?) {
    }

    /**
     * 网络请求成功
     */
    override fun <T> netReqSuccess(netOptionReqCode: Int, data: T?) {
    }

    /**
     * 登录状态失效
     */
    override fun userLoginStatusError(code: Any?, message: String?) {
        AtlwActivityJumpUtils.getInstance().jump(this, LoginActivity::class.java)
    }
}
