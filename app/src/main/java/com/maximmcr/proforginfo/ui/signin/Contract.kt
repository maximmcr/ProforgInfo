package com.maximmcr.proforginfo.ui.signin

import com.maximmcr.proforginfo.data.foreign.model.User
import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {
    interface Presenter<V: View>: MvpPresenter<V> {
        fun register(user: User)
    }

    interface View: MvpView {
        fun openGroupList()
        fun showError()
    }
}