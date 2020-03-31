package com.moments_of_life.android.view

import android.app.Activity
import android.graphics.Color
import android.lorenwang.customview.dialog.AvlwBaseDialog
import android.lorenwang.tools.app.AtlwScreenUtils
import android.lorenwang.tools.app.AtlwViewUtils
import android.view.View
import android.view.ViewGroup
import com.moments_of_life.android.R

/**
 * 功能作用：加载中弹窗
 * 创建时间：2019-12-13 下午 23:52:59
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class LoadingDialog(activity: Activity) : AvlwBaseDialog(
    activity, R.layout.dialog_loading, R.style.avlw_loading_dialog_type1, R.style.avlw_dialog_anim_for_center,
    false, true, true
) {

    init {
        view.minimumHeight = AtlwScreenUtils.getInstance().getScreenHeight()
    }
}
