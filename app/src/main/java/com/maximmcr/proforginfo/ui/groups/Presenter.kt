package com.maximmcr.proforginfo.ui.groups

import com.maximmcr.proforginfo.ui.base.BasePresenter
import javax.inject.Inject

class Presenter<V: Contract.View> @Inject constructor() : BasePresenter<V>(), Contract.Presenter<V> {

    override fun addGroup(newGroup: Group) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openGroup(group: Group) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}