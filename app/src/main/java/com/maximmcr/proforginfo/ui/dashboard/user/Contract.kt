package com.maximmcr.proforginfo.ui.dashboard.user

import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface View: MvpView

    interface Presenter<V: View>: MvpPresenter<V>

}