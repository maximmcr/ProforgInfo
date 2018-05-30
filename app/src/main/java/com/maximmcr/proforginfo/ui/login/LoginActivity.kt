package com.maximmcr.proforginfo.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.ui.base.BaseActivity
import com.maximmcr.proforginfo.ui.groups.GroupsActivity
import com.maximmcr.proforginfo.ui.signin.SigninActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

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
        startActivity(Intent(this, GroupsActivity::class.java))
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
                presenter.processLoginResult(true)
            } else {
                response?.error?.printStackTrace()
                presenter.processLoginResult(false)
            }
        }
    }

    override fun showErrorMessage() {
        Snackbar.make(login_main, "Something get wrong", Snackbar.LENGTH_LONG)
    }

    override fun attachPresenter() {
        presenter = presenterProvider.get().apply { subscribe(this@LoginActivity) }
    }

    override fun detachPresenter() = presenter.unsubscribe()

    override fun finishPresenter() = presenter.finish()

    override fun openRegistrationForm() {
        startActivity(Intent(this, SigninActivity::class.java))
    }
}
