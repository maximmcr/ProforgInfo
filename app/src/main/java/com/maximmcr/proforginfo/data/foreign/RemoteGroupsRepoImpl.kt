package com.maximmcr.proforginfo.data.foreign

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.maximmcr.proforginfo.data.RemoteGroupsRepo
import com.maximmcr.proforginfo.data.foreign.model.Group
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

const val FS_GROUPS = "groups"
const val FS_GROUP_ADMIN = "admin"

class RemoteGroupsRepoImpl @Inject constructor(private val fbAuth: FirebaseAuth, private val fs: FirebaseFirestore): RemoteGroupsRepo {

    override fun add(group: Group): Single<String> = Single.create {
        group.apply {
            val userRef = fs.document("$FS_USERS/${fbAuth.currentUser!!.uid}")
            admin = userRef
            users = listOf(userRef)
        }
        fs.collection(FS_GROUPS).document().apply {
            group.id = this.id
            set(group).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    it.onSuccess(id)
                }
                else it.onError(task.exception!!)
            }
        }
    }
    override fun getAll(): Observable<List<Group>> = Observable.create { emitter ->
        val listener = fs.collection(FS_GROUPS).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                emitter.onError(exception)
            } else {
                val result = mutableListOf<Group>()
                snapshot!!.documentChanges.forEach { t: DocumentChange? ->
                    when (t!!.type) {
                        DocumentChange.Type.ADDED -> result.add(t.document.toObject(Group::class.java).apply { id = t.document.id })
                        DocumentChange.Type.MODIFIED -> return@forEach
                        DocumentChange.Type.REMOVED -> return@forEach
                    }
                }
                emitter.onNext(result)
            }
        }
        emitter.setCancellable { listener.remove() }
    }

    override fun delete(group: Group): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun change(group: Group): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}