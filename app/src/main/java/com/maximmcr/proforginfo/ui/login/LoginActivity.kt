package com.maximmcr.proforginfo.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.firebase.ui.auth.AuthUI
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.ui.base.BaseActivity
import com.maximmcr.proforginfo.ui.groups.GroupsActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_groups.*
import javax.inject.Inject
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), Contract.View {

    companion object {
        private const val RC_SIGN_IN = 100
    }

    @Inject
    lateinit var presenterProvider: Lazy<Contract.Presenter<Contract.View>>
    lateinit var presenter: Contract.Presenter<Contract.View>

    @Inject
    lateinit var authUiprovider: Lazy<AuthUI>
    var authUi: AuthUI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener { presenter.login() }
    }

    override fun openGroupList() {
        val i = Intent(this, GroupsActivity::class.java)
        startActivity(i)
    }

    override fun openLoginScreen() {
        val authProviders = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )
        if (authUi == null) authUi = authUiprovider.get()
        startActivityForResult(
                authUi!!.createSignInIntentBuilder()
                        .setAvailableProviders(authProviders)
                        .build(),
                RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                presenter.processResult(true)
            } else {
                response?.error?.printStackTrace()
                presenter.processResult(false)
            }
        }
    }

    override fun showErrorMessage() {
        Snackbar.make(main, "Something gone wrong", Snackbar.LENGTH_LONG)
    }

    override fun getSavedPresenter(): Any = presenter

    override fun attachPresenter() {
        presenter = lastCustomNonConfigurationInstance as Contract.Presenter<Contract.View>? ?: presenterProvider.get()
        presenter.subscribe(this)
    }

    override fun detachPresenter() {
        presenter.unsubscribe()
    }

    override fun finishPresenter() {
        presenter.finish()
    }
}
