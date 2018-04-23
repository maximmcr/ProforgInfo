package com.maximmcr.proforginfo.ui.base

interface BasePresenter<in V> {
    fun attachView(view: V)
}