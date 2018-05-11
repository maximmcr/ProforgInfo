package com.maximmcr.proforginfo.ui.login

import com.firebase.ui.auth.AuthUI
import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface Presenter<V: View>: MvpPresenter<V> {
        fun login()
        fun processResult(status: Boolean)
    }

    interface View: MvpView {
        fun openGroupList()
        fun openLoginScreen()
        fun showErrorMessage()
    }
}