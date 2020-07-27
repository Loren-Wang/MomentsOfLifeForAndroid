package com.moments_of_life.android.fragment

import android.os.Bundle
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
class UserFragment : BaseFragment() {
    /**
     * 初始化视图
     *
     * @param savedInstanceState 页面切换等操作是手动存储的值
     */
    override fun initView(savedInstanceState: Bundle?) {
        addContentView(R.layout.fragment_user,null)
    }
}
