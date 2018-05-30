package com.maximmcr.proforginfo.ui.groups

import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.ui.base.MvpPresenter
import com.maximmcr.proforginfo.ui.base.MvpView

interface Contract {

    interface Presenter<in V: View> : MvpPresenter<V> {
        fun addGroup(newGroup: Group)
        fun openGroup(group: Group)
        fun loadGroups()
    }

    interface View : MvpView {
        fun showGroups(groups: List<Group>)
        fun showNewGroup(group: Group)
        fun showAddGroupDialog()
        fun showGroup(group: Group)
        fun showError()
    }

}