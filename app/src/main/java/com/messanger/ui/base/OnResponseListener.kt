package com.messanger.ui.base

/**
 * Created by Rahul Abrol on 9/9/20.
 */
interface OnResponseListener<T> {

    fun onSuccess(response: T)

    fun onError(error: String?)
}