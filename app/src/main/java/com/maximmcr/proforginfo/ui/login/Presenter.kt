package com.maximmcr.proforginfo.ui.login

import com.google.firebase.auth.FirebaseAuth
import com.maximmcr.proforginfo.data.RemoteUsersRepo
import com.maximmcr.proforginfo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Presenter<V : Contract.View> @Inject constructor(private val fbAuth: FirebaseAuth, private val fsUsers: RemoteUsersRepo)
    : BasePresenter<V>(), Contract.Presenter<V> {

    override fun login() {
        if (fbAuth.currentUser == null) view?.openLoginScreen()
        else checkRegistration(fbAuth.currentUser!!.uid)
    }

    override fun processLoginResult(status: Boolean) {
        when (status) {
            true -> checkRegistration(fbAuth.currentUser!!.uid)
            false -> view?.showErrorMessage()
        }
    }

    fun checkRegistration(uid: String) {
        subscriptions.add(
                fsUsers.isRegistered(uid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { status ->
                        if (status) view?.openGroupList()
                        else view?.openRegistrationForm()
                    }
        )
    }

    override fun subscribe(view: V) {
        super.subscribe(view)
    }
}