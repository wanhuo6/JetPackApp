package com.ahuo.architecture.ext

import java.lang.reflect.ParameterizedType

/**
 * viewModel扩展
 * @author liuhuijie
 * @date   2021/7/20
 */
/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
}