package com.moments_of_life.android.fragment

import android.view.View
import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.base.BaseFragment

/**
 * 功能作用：用户fragment页面
 * 创建时间：2020-03-01 20:07
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class UserFragment(activity: BaseActivity) : BaseFragment(activity) {
    /**
     * 获取fragment资源id
     */
    override fun getFgLayoutResId(): Int {
        return R.layout.fragment_user
    }

    /**
     * 初始化数据
     */
    override fun initData(view: View) {
    }

    /**
     * 初始化view
     */
    override fun initView(view: View) {
    }
}
