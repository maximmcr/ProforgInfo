package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.foreign.model.Group
import com.maximmcr.proforginfo.data.foreign.model.User

interface RemoteGroupsRepo {
    fun add(group: Group)
    fun getAll()
    fun delete()
    fun change()
    fun getGroup()
}

interface RemoteUsersRepo {
    fun add(user: User)
    fun change(id: String)
}