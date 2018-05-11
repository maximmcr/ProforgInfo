package com.maximmcr.proforginfo.ui.login

import com.google.firebase.auth.FirebaseAuth
import com.maximmcr.proforginfo.ui.base.BasePresenter
import javax.inject.Inject

class Presenter<V : Contract.View> @Inject constructor(private val fbAuth: FirebaseAuth)
    : BasePresenter<V>(), Contract.Presenter<V> {

    override fun login() {
        if (fbAuth.currentUser != null) view?.openGroupList()
        else view?.openLoginScreen()
    }

    override fun processResult(status: Boolean) {
        when (status) {
            true -> view?.openGroupList()
            false -> view?.showErrorMessage()
        }
    }

    override fun subscribe(view: V) {
        super.subscribe(view)
        if (fbAuth.currentUser != null) this.view?.openGroupList()
    }
}