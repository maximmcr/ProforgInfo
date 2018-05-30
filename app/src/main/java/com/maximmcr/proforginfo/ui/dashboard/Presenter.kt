package com.maximmcr.proforginfo.ui.dashboard

import com.google.firebase.auth.FirebaseAuth
import com.maximmcr.proforginfo.data.PreferenceRepository
import com.maximmcr.proforginfo.data.RemoteUsersRepo
import com.maximmcr.proforginfo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Presenter<V : Contract.View> @Inject constructor(
        val prefs: PreferenceRepository,
        val userRepo: RemoteUsersRepo,
        val fbAuth: FirebaseAuth
) : BasePresenter<V>(), Contract.Presenter<V> {

    private var adminMode = true

    override fun changeMode() = if (adminMode) {
        view?.showUserUI()
        adminMode = false
    } else {
        view?.showAdminUI()
        adminMode = true
    }


    override fun subscribe(view: V) {
        super.subscribe(view)
        subscriptions.add(
                userRepo.isAdmin(prefs.getGroupId(), fbAuth.currentUser!!.uid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { status, e ->
                            if (e == null) {
                                view.showSwitch(status)
                                if (status && adminMode) view.showAdminUI() else view.showUserUI()
                            } else {
                                view.showSwitch(false)
                                view.showUserUI()
                            }
                        }
        )
    }
}