package com.maximmcr.proforginfo.data.foreign

import com.google.firebase.firestore.FirebaseFirestore
import com.maximmcr.proforginfo.data.RemoteUsersRepo
import com.maximmcr.proforginfo.data.foreign.model.User
import io.reactivex.Single
import javax.inject.Inject

const val FS_USERS = "users"

class RemoteUsersRepoImpl @Inject constructor(private val fs: FirebaseFirestore): RemoteUsersRepo {

    override fun isRegistered(uid: String) = Single.create<Boolean> {
        fs.collection(FS_USERS).document(uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful)
                it.onSuccess(task.result.exists())
            else
                it.onError(task.exception!!)
        }
    }

    override fun isAdmin(groupId: String, uid: String) = Single.create<Boolean> {
        fs.collection(FS_GROUPS).document(groupId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val admin = task.result.getDocumentReference(FS_GROUP_ADMIN)
                it.onSuccess(admin!!.id == uid)
            } else {
                it.onError(task.exception!!)
            }
        }
    }

    override fun addUser(uid: String, user: User) = Single.create<Boolean> {
       fs.collection(FS_USERS).document(uid).set(user).addOnCompleteListener { task ->
           if (task.isSuccessful)
               it.onSuccess(true)
           else
               it.onError(task.exception!!)
       }
    }
}