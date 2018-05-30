package com.maximmcr.proforginfo.ui.dashboard.user

import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.ui.base.BaseFragment
import dagger.Lazy
import javax.inject.Inject

class UserFragment @Inject constructor(): BaseFragment(), Contract.View {

    override val TAG: String
        get() = "userFragment"

    lateinit var presenterProvider: Lazy<Contract.Presenter<Contract.View>>
    lateinit var presenter: Contract.Presenter<Contract.View>

    override fun getLayout(): Int = R.layout.fragment_admin

    override fun setupViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attachPresenter() {
        presenter = presenterProvider.get()
        presenter.subscribe(this)
    }
    override fun detachPresenter() {
        presenter.unsubscribe()
    }
    override fun finishPresenter() {
        presenter.finish()
    }
}
