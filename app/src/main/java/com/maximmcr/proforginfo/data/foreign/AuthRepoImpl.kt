package com.maximmcr.proforginfo.data.foreign

import com.google.firebase.auth.FirebaseAuth
import com.maximmcr.proforginfo.data.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(val auth: FirebaseAuth) : AuthRepo {

    override fun isLoggedIn() = auth.currentUser != null

    override fun login() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout() {
        auth.signOut()
    }
}