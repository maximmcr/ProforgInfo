package com.maximmcr.proforginfo.ui.dashboard

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.ui.base.BaseActivity
import com.maximmcr.proforginfo.ui.dashboard.admin.AdminFragment
import com.maximmcr.proforginfo.ui.dashboard.user.UserFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity(), Contract.View {

    private var optionsMenu: Menu? = null

    @Inject
    lateinit var presenterProvider: Lazy<Contract.Presenter<Contract.View>>
    lateinit var presenter: Contract.Presenter<Contract.View>

    @Inject
    lateinit var adminFragProvider: Lazy<AdminFragment>
    var adminFrag: AdminFragment? = null

    @Inject
    lateinit var userFragProvider: Lazy<UserFragment>
    var userFrag: UserFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        optionsMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_switch -> {
            presenter.changeMode()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showAdminUI() {
//        adminFrag = adminFragProvider.get()
//        supportFragmentManager.apply {
//            beginTransaction()
//
//        }
        Log.v("DASHBOARD", "ADMIN PANEL")
    }

    override fun showUserUI() {
        Snackbar.make(dash_coordinator, "UserUI", Snackbar.LENGTH_LONG)
        Log.v("DASHBOARD", "USER PANEL")
    }

    override fun showSwitch(status: Boolean) {
        optionsMenu?.findItem(R.id.action_switch)?.isVisible = status
    }


    override fun attachPresenter() {
        presenter = presenterProvider.get().apply { subscribe(this@DashboardActivity) }
    }

    override fun detachPresenter() = presenter.unsubscribe()

    override fun finishPresenter() = presenter.finish()
}
