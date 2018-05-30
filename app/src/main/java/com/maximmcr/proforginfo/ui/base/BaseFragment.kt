package com.maximmcr.proforginfo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment(), MvpView {

    abstract val TAG: String
    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayout(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    abstract fun setupViews()

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

    override fun onDestroy() {
        super.onDestroy()
        finishPresenter()
    }
}