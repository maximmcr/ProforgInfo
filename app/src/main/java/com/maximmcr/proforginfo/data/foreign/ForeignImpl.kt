package com.maximmcr.proforginfo.data.foreign

import com.google.firebase.database.*
import com.maximmcr.proforginfo.data.GroupRepo
import com.maximmcr.proforginfo.data.UserMonthRepo
import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.data.foreign.model.Order
import com.maximmcr.proforginfo.data.local.model.Month
import io.reactivex.Observable
import io.reactivex.Single

private const val _CHILD_GROUP = "group"
private const val _CHILD_PAYMENT = "isPayed"
private const val _CHILD_USER = "user"
private const val _CHILD_MONTH = "month"
private const val _CHILD_ADMIN = "admin"

class UserMonthImpl(fDb: DatabaseReference) : UserMonthRepo {

    val orderRef = fDb.child("Orders")
    val monthRef = fDb.child("Monthes")

    override fun add(timestamp: String): Boolean {
        throw RuntimeException("Stub!")
    }

    override fun remove(id: Long) {
        throw RuntimeException("Stub!")
    }

    override fun getAll(): Observable<List<Month>> {
        throw RuntimeException("Stub!")
    }

    override fun syncWithRemote(timestamp: String, firebaseId: String): Single<Boolean> {
        throw RuntimeException("Stub!")
    }

    override fun getRemoteId(groupId: String): Observable<Pair<String, String>> = Observable.create<Pair<String, String>> { emitter ->
        monthRef.orderByChild(_CHILD_GROUP)
                .equalTo(groupId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        emitter.onError(p0!!.toException().fillInStackTrace())
                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        val month = p0!!.getValue(com.maximmcr.proforginfo.data.foreign.model.Month::class.java)
                        emitter.onNext(month!!.timestamp to p0.key)
                    }

                })
    }

    override fun sendOrder(order: Order): Single<Boolean> = Single.create {
        orderRef.child(getUserKey(order.user, order.month)).setValue(order) { p0, p1 ->
            when (p0 != null) {
                true -> it.onError(p0.toException().fillInStackTrace())
                false -> it.onSuccess(true)
            }
        }
    }

    override fun changePaymentStatus(status: Boolean, userId: String, monthId: String, timestamp: String): Single<Boolean> = Single.create {
        orderRef.child(getUserKey(userId, monthId))
                .child(_CHILD_PAYMENT)
                .setValue(status) { p0, _ ->
                    when (p0 != null) {
                        true -> it.onError(p0.toException().fillInStackTrace())
                        false -> it.onSuccess(true)
                    }
                }
    }

    private fun getUserKey(userId: String, monthId: String) = "$userId-$monthId"

}

class GroupRepoImpl(fDb: DatabaseReference) : GroupRepo {

    val groupRef = fDb.child("Groups")

    override fun add(group: Group): Single<String> = Single.create {
        groupRef.push().setValue(group) { databaseError, databaseReference ->
            when (databaseError != null) {
                true -> it.onError(databaseError.toException().fillInStackTrace())
                false -> it.onSuccess(databaseReference.key)
            }
        }
    }

    override fun get(id: String): Single<Group> = Single.create {
        groupRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                it.onError(p0!!.toException().fillInStackTrace())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                it.onSuccess(p0!!.getValue(Group::class.java)!!)
            }
        })
    }

    override fun getAll(): Observable<Group> = Observable.create {
        groupRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                it.onError(p0!!.toException().fillInStackTrace())
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                it.onNext(p0!!.getValue(Group::class.java)!!.apply { this.id = p0.key })
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
            }
        })
    }

    override fun change(group: Group): Single<Boolean> = Single.create {
        groupRef.child(group.id)
                .setValue(group) { error, ref ->
                    when (error != null) {
                        true -> it.onError(error.toException().fillInStackTrace())
                        false -> it.onSuccess(true)
                    }
                }
    }

    override fun delete(groupId: String): Single<Boolean> = Single.create {
        groupRef.child(groupId)
                .removeValue { error, ref ->
                    when (error != null) {
                        true -> it.onError(error.toException().fillInStackTrace())
                        false -> it.onSuccess(true)
                    }
                }
    }

    override fun isAdmin(userId: String, groupId: String): Single<Boolean> = Single.create {
        it.onSuccess(
                groupRef.child(groupId)
                        .child(_CHILD_ADMIN)
                        .equals(userId)
        )
    }

}