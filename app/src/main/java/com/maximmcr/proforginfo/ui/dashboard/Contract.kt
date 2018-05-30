package com.maximmcr.proforginfo.ui.dashboard

import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface Presenter<in V: View>: MvpPresenter<V> {
        fun changeMode()
    }

    interface View: MvpView {
        fun showAdminUI()
        fun showUserUI()
        fun showSwitch(status: Boolean)
    }

}