package com.maximmcr.proforginfo.ui.signin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.maximmcr.proforginfo.R
import com.maximmcr.proforginfo.data.foreign.model.User
import com.maximmcr.proforginfo.ui.base.BaseActivity
import com.maximmcr.proforginfo.ui.groups.GroupsActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject

class SigninActivity : BaseActivity(), Contract.View {

    @Inject
    lateinit var presenterProvider: Lazy<Presenter<Contract.View>>
    lateinit var presenter: Presenter<Contract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        uiInit()
    }

    fun uiInit() {
        signinBtn.setOnClickListener {
            val name = reg_name.editText?.text.toString()
            val addContact = reg_addcontact.editText?.text.toString()
            val groupName = reg_groupname.editText?.text.toString()
            val phone = reg_phone.editText?.text.toString()
            if (name != "" &&
                    addContact != "" &&
                    groupName != "" &&
                    phone != "") {
                presenter.register(User(name, phone, addContact, groupName))
            } else {
                Snackbar.make(signin_main, "You shell not pass", Snackbar.LENGTH_LONG)
            }
        }
    }

    override fun attachPresenter() {
        presenter = presenterProvider.get().apply { subscribe(this@SigninActivity) }
    }

    override fun detachPresenter() = presenter.unsubscribe()

    override fun finishPresenter() = presenter.finish()

    override fun openGroupList() {
        startActivity(Intent(this, GroupsActivity::class.java))
    }

    override fun showError() {
        Snackbar.make(signin_main, "Error", Snackbar.LENGTH_LONG)
    }

}
