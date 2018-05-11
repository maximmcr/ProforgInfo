package com.maximmcr.proforginfo.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V: MvpView>: MvpPresenter<V> {

    var view: V? = null
    private val subscriptions = CompositeDisposable()

    override fun finish() {
        subscriptions.clear()
    }

    override fun subscribe(view: V) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }
}