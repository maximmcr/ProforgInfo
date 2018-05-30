package com.maximmcr.proforginfo.ui.base

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), MvpView {

    abstract fun attachPresenter()
    abstract fun detachPresenter()
    abstract fun finishPresenter()

    override fun onStart() {
        super.onStart()
        attachPresenter()
    }
    override fun onStop() {
        super.onStop()
        detachPresenter()
    }
    override fun finish() {
        super.finish()
        finishPresenter()
    }
}