package com.maximmcr.proforginfo.ui.signin

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.maximmcr.proforginfo.data.RemoteUsersRepo
import com.maximmcr.proforginfo.data.foreign.model.User
import com.maximmcr.proforginfo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Presenter<V : Contract.View> @Inject constructor(private val fbAuth: FirebaseAuth, private val fsUsers: RemoteUsersRepo)
    : BasePresenter<V>(), Contract.Presenter<V> {
    val LOG_TAG = Presenter::class.java.simpleName

    override fun register(user: User) {
        val uid = fbAuth.currentUser!!.uid
        subscriptions.add(
                fsUsers.addUser(uid, user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {status, throwable ->
                        if (status) view?.openGroupList()
                        else{
                            Log.e(LOG_TAG, throwable.stackTrace.toString())
                            view?.showError()
                        }
                    }
        )
    }
}