package com.maximmcr.proforginfo.ui.groups

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.ui.base.BaseActivity
import com.maximmcr.proforginfo.ui.customview.NewGroupDialogFragment
import com.maximmcr.proforginfo.ui.dashboard.DashboardActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_groups.*
import javax.inject.Inject

class GroupsActivity : BaseActivity(), Contract.View {

    companion object {
        const val DIALOG_NEW_GROUP = "newGroupDialog"
        const val INTENT_EXTRA_GROUP = "extraGroup"
    }

    @Inject
    lateinit var presenterProvider: Lazy<Contract.Presenter<Contract.View>>
    lateinit var presenter: Contract.Presenter<Contract.View>

    lateinit var groupsAdapter: GroupsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        groupsAdapter = GroupsAdapter(mutableListOf()) { presenter.openGroup(it) }
    }

    override fun attachPresenter() {
        presenter = presenterProvider.get().apply { subscribe(this@GroupsActivity) }
    }

    override fun detachPresenter() = presenter.unsubscribe()

    override fun finishPresenter() = presenter.finish()

    override fun showGroups(groups: List<Group>) {
        groupsAdapter.addAll(groups)
    }

    override fun showAddGroupDialog() {
        val dialog = NewGroupDialogFragment()
        dialog.onSuccess = presenter::addGroup
        dialog.show(fragmentManager, DIALOG_NEW_GROUP)
    }

    override fun showGroup(group: Group) {
        Snackbar.make(groups_main, "onClickResponse", Snackbar.LENGTH_LONG)
        startActivity(Intent(applicationContext, DashboardActivity::class.java).apply { putExtra(INTENT_EXTRA_GROUP, group.id) } )
        finish()
    }

    override fun showNewGroup(group: Group) {
        groupsAdapter.add(group)
    }

    override fun showError() {
        Snackbar.make(groups_main, "Something get wrong", Snackbar.LENGTH_LONG)
    }

    override fun onStart() {
        super.onStart()
        groups_fab.setOnClickListener {
            NewGroupDialogFragment()
                    .apply { onSuccess = presenter::addGroup }.
                    show(fragmentManager, DIALOG_NEW_GROUP)
        }
        groups_list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = groupsAdapter
        }
    }
}
