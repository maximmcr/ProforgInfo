package com.maximmcr.proforginfo.ui.groups

import com.maximmcr.proforginfo.data.PreferenceRepository
import com.maximmcr.proforginfo.data.RemoteGroupsRepo
import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Presenter<V: Contract.View> @Inject constructor(val groupsRepo: RemoteGroupsRepo, val prefs: PreferenceRepository)
    : BasePresenter<V>(), Contract.Presenter<V> {

    var firstLoad = true

    override fun addGroup(newGroup: Group) {
        subscriptions.add(
                groupsRepo.add(newGroup)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { groupUid, throwable ->
                    throwable?.let {
                        throwable.printStackTrace()
                        view?.showError()
                    } ?: view?.showGroup(newGroup.apply { id = groupUid })
                }
        )

    }

    override fun openGroup(group: Group) {
        prefs.saveGroupId(group.id)
        view?.showGroup(group)
    }

    override fun subscribe(view: V) {
        super.subscribe(view)
        if (firstLoad) {
            firstLoad = false
            loadGroups()
        }
    }

    override fun loadGroups() {
        subscriptions.add(
                groupsRepo.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            view?.showGroups(it)
                        }
        )
    }
}