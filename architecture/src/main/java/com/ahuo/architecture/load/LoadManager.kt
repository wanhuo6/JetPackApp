package com.ahuo.architecture.load

import android.app.Activity
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @author liuhuijie
 * @date   2021/7/27
 */
class LoadManager {

    /**展示载体**/
    private lateinit var parentView: ViewGroup

    /**展示视图**/
    private lateinit var loadView: LoadView

    private constructor() {

    }

    constructor(fragment: Fragment) {
        val viewGroup: ViewGroup = fragment.view as ViewGroup
        initParam(viewGroup)
    }

    constructor(activity: Activity) {
        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content) ?: return
        initParam(viewGroup)
    }

    constructor(viewGroup: ViewGroup) {
        initParam(viewGroup)
    }

    private fun initParam(viewGroup: ViewGroup) {
        this.parentView = viewGroup
        loadView = LoadView(viewGroup.context)
    }

    /**
     * 展示加载视图
     */
    fun showLoading() {
        parentView.run {
            remove(this)
            addView(
                loadView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    /**
     * 展示成功视图
     */
    fun showSuccess() {
        parentView.run {
            remove(this)
        }
    }

    /**
     * 展示错误视图
     */
    fun showError(callBack: () -> Unit) {
        parentView.run {
            remove(this)
            addView(
                loadView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            loadView.showError {
                callBack.invoke()
            }

        }
    }

    /**
     * 展示空视图
     */
    fun showEmpty() {
        parentView.run {
            remove(this)
            addView(
                loadView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            loadView.showEmpty()
        }
    }

    /**
     * 从载体移除视图
     */
    private fun remove(viewGroup: ViewGroup) {
        var view = viewGroup.getChildAt((viewGroup).childCount - 1)
        if (view is LoadView) {
            viewGroup.removeView(view)
        }
    }

}