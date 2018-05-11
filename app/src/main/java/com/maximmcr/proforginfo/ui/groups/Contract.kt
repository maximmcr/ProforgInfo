package com.maximmcr.proforginfo.ui.groups

import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface Presenter<V: View> : MvpPresenter<V> {
        fun addGroup(newGroup: Group)
        fun openGroup(group: Group)
    }

    interface View : MvpView {
        fun showGroups(groups: List<Group>)
        fun showAddGroupDialog()
        fun showGroupScreen(group: Group)
        fun showSnack(msg: String)
    }

}