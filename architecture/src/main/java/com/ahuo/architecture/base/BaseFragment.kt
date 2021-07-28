package com.ahuo.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ahuo.architecture.ext.dismissLoadingExt
import com.ahuo.architecture.ext.getVmClazz
import com.ahuo.architecture.ext.showLoadingExt
import com.ahuo.architecture.load.LoadManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 基类Activity
 * @author liuhuijie
 * @date 2021/7/20
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int,
    override val variableId: Int = 0,
    private val useActivityViewModelStoreOwner: Boolean = false
) : Fragment(), IContain<ViewDataBinding, BaseViewModel> {

    override lateinit var mBinding: DB
    override lateinit var mViewModel: VM
    override lateinit var mLoadManager: LoadManager

    private var mIsFirst: Boolean = true //是否第一次加载

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //如果fragment的view已经创建则不再重新创建
        if (mIsFirst) {
            mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mBinding.lifecycleOwner = this
        }
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (lifecycle.currentState == Lifecycle.State.STARTED && mIsFirst) {
            lifecycleScope.launch {
                // 延迟加载防止切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿
                delay(300)
                lazyLoadData()
                mIsFirst = false
            }

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mIsFirst) {
            initViewModel()
            observeLiveData(this)
            bindViewModel()
            initLoadManager()
            initView(savedInstanceState)
        }

    }


    /**
     * 初始化loadManager
     */
    override fun initLoadManager() {
        mLoadManager = LoadManager(this)
    }


    /**
     * 创建viewModel
     */
    override fun initViewModel() {
        mViewModel = ViewModelProvider(
            if (useActivityViewModelStoreOwner) {
                activity as AppCompatActivity
            } else {
                this
            }
        ).get(getVmClazz(this))
    }
    /**
     * 展示进度
     */
    override fun showLoading() {
        showLoadingExt()
    }
    /**
     * 隐藏进度
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()
}