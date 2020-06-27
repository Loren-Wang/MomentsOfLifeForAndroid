package com.moments_of_life.android.fragment

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Rect
import android.lorenwang.graphic_code_scan.AgcslwScan
import android.lorenwang.graphic_code_scan.AgcslwScanResultCallback
import android.lorenwang.graphic_code_scan.AgcslwScanView
import android.lorenwang.tools.app.AtlwActivityUtils
import android.lorenwang.tools.app.AtlwPermissionRequestCallback
import android.view.View
import com.moments_of_life.android.R
import com.moments_of_life.android.base.BaseActivity
import com.moments_of_life.android.base.BaseFragment
import com.moments_of_life.android.utils.ToastUtils

/**
 * 功能作用：家用物品信息页面fragment
 * 创建时间：2020-03-01 20:06
 * 创建人：王亮（Loren wang）
 * 思路：
 * 方法：
 * 注意：
 * 修改人：
 * 修改时间：
 * 备注：
 */
class HouseholdObjectsOptionsFragment(activity: BaseActivity) : BaseFragment(activity) {
    private val scan = AgcslwScan()
    private var surfaceView: AgcslwScanView? = null
    /**
     * 获取fragment资源id
     */
    override fun getFgLayoutResId(): Int {
        return R.layout.fragment_household_objects
    }

    /**
     * 初始化view
     */
    override fun initView(view: View) {
        surfaceView = view.findViewById(R.id.surfaceView)
        //请求权限
        AtlwActivityUtils.getInstance().goToRequestPermissions(activity,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0,
                object : AtlwPermissionRequestCallback {

                    override fun permissionRequestFailCallback(
                        permissionList: MutableList<String>?,
                        permissionsRequestCode: Int
                    ) {
                    }

                    override fun permissionRequestSuccessCallback(
                        permissionList: MutableList<String>?,
                        permissionsRequestCode: Int
                    ) {
                        surfaceView?.setAgcslwScan(scan)
                        //开启扫描
                        scan.startScan(activity, surfaceView?.surfaceView, true, true, true, true, true)
                        //扫描结果回调
                        scan.setScanResultCallback(object : AgcslwScanResultCallback {
                            /**
                             * 扫描视图裁剪矩阵变化
                             *
                             * @param cropRect 裁剪矩阵位置,仅相对于扫描控件scanview的坐标
                             */
                            override fun scanViewCropRectChange(cropRect: Rect) {
                            }

                            /**
                             * 扫描文本结果
                             *
                             * @param result 结果内容
                             */
                            override fun scanResult(result: String?) {
                                scan.restartPreviewAfterDelay()
                                result?.let { ToastUtils.instance.successHint(it) }
                            }

                            /**
                             * 返回扫描结果的位图
                             *
                             * @param bitmap 扫描结果位图
                             */
                            override fun scanResultBitmap(bitmap: Bitmap?) {
                            }

                            /**
                             * 无扫描权限
                             *
                             * @param shouldShowRequestPermissionRationale 是否能显示自定义权限弹窗
                             * @param permissions                          权限集合
                             */
                            override fun notPermissions(shouldShowRequestPermissionRationale: Boolean, vararg permissions: String?) {
                            }

                            /**
                             * 扫描解码出错
                             */
                            override fun scanDecodeError() {
                            }

                            /**
                             * 权限请求失败，保留方法，留给子类扩展，后续该框架也可能会使用
                             *
                             * @param permissions 权限列表
                             */
                            override fun permissionRequestFail(vararg permissions: String?) {
                            }

                            /**
                             * 扫描相册图片异常
                             *
                             * @param path              传递的图片地址
                             * @param isPathNotExists   图片地址代表的文件不存在
                             * @param isScanDecodeError 扫描解码异常
                             */
                            override fun scanPhotoAlbumImageError(path: String?, isPathNotExists: Boolean, isScanDecodeError: Boolean) {
                            }

                            /**
                             * 相机初始化异常
                             */
                            override fun cameraInitError() {
                            }
                        })
                    }
                })
    }

    /**
     * 初始化数据
     */
    override fun initData(view: View) {
    }

    override fun onResume() {
        super.onResume()
        scan.onActResumeChange()
    }

    override fun onPause() {
        super.onPause()
        scan.onActPauseChange()
    }

    override fun onDestroy() {
        super.onDestroy()
        scan.onActFinish()
    }

}
