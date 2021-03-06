package com.maximmcr.proforginfo.ui.login

import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface Presenter<V: View>: MvpPresenter<V> {
        fun login()
        fun processLoginResult(status: Boolean)
    }

    interface View: MvpView {
        fun openGroupList()
        fun openLoginScreen()
        fun openRegistrationForm()
        fun showErrorMessage()
    }
}