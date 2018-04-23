package com.maximmcr.proforginfo.ui.groups

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.maximmcr.proforginfo.R
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : AppCompatActivity() {

    companion object {
        val RC_SIGN_IN = 123
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var fbUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        fbUser = auth.currentUser!!
        Snackbar.make(main, "OK - ${fbUser.uid}", Snackbar.LENGTH_LONG).show()
        val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )
        button.setOnClickListener {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(main, "OK - ${fbUser.uid}", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
