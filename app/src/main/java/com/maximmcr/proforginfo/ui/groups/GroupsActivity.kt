package com.maximmcr.proforginfo.ui.groups

import android.os.Bundle
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.ui.base.BaseActivity

class GroupsActivity : BaseActivity(), Contract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
    }

    override fun getSavedPresenter(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attachPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detachPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showGroups(groups: List<Group>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddGroupDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showGroupScreen(group: Group) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSnack(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
