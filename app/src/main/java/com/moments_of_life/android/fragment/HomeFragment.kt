package com.moments_of_life.android.fragment

import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.base.BaseFragment

/**
 * 功能作用：主页fragment
 * 创建时间：2020-03-01 17:22
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class HomeFragment(activity: BaseActivity) : BaseFragment(activity) {
    /**
     * 获取fragment资源id
     */
    override fun getFgLayoutResId(): Int {
        return R.layout.fragment_home
    }

    /**
     * 初始化数据
     */
    override fun initData() {
    }

    /**
     * 初始化view
     */
    override fun initView() {
    }
}
