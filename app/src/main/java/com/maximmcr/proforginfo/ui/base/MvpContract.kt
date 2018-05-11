package com.maximmcr.proforginfo.ui.base

interface MvpPresenter<in V> {
    fun subscribe(view: V)
    fun unsubscribe()
    fun finish()
}

interface MvpView