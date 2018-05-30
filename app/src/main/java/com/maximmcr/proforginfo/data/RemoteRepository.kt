package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.data.foreign.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteGroupsRepo {
    fun add(group: Group): Single<String>
    fun getAll(): Observable<List<Group>>
    fun delete(group: Group): Single<Boolean>
    fun change(group: Group): Single<Boolean>
}

interface RemoteUsersRepo {
    fun isRegistered(uid: String): Single<Boolean>
    fun isAdmin(groupId: String, uid: String): Single<Boolean>
    fun addUser(uid: String, user: User): Single<Boolean>
}