package com.moments_of_life.android.activity

import android.graphics.Color
import android.lorenwang.common_base_frame.adapter.AcbflwBaseRecyclerAdapter
import android.lorenwang.common_base_frame.adapter.AcbflwBaseRecyclerViewHolder
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentTransaction
import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.fragment.HomeFragment
import com.moments_of_life.android.fragment.HouseholdObjectsFragment
import com.moments_of_life.android.fragment.UserFragment
import kotlinx.android.synthetic.main.bottom_view_type_1.*

open class MainActivity : BaseActivity() {
    private var homeFragment: HomeFragment? = null
    private var householdObjectsFragment: HouseholdObjectsFragment? = null
    private var userFragment: UserFragment? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        addContentView(R.layout.activity_main, null, R.layout.bottom_view_type_1)
        changePage(btnHome)
    }

    /**
     * 修改页面到指定位置
     */
    private fun changePage(view: AppCompatButton) {
        btnHome.setTextColor(Color.GRAY)
        btnHouseholdObjects.setTextColor(Color.GRAY)
        btnUser.setTextColor(Color.GRAY)
        view.setTextColor(Color.BLACK)
        val transaction = supportFragmentManager.beginTransaction()
        when (view.id) {
            R.id.btnHome -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment(this)
                }
                transaction.replace(R.id.frameLayout, homeFragment!!)

            }
            R.id.btnHouseholdObjects -> {
                if (householdObjectsFragment == null) {
                    householdObjectsFragment = HouseholdObjectsFragment(this)
                }
                transaction.replace(R.id.frameLayout, householdObjectsFragment!!)
            }
            R.id.btnUser -> {
                if (userFragment == null) {
                    userFragment = UserFragment(this)
                }
                transaction.replace(R.id.frameLayout, userFragment!!)
            }
            else -> {

            }
        }
        transaction.commit()
    }

    open fun mainTabOnClick(view: View) {
        if (view is AppCompatButton) {
            changePage(view)
        }
    }
}
