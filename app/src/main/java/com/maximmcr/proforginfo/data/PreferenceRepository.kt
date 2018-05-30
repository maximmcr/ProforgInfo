package com.maximmcr.proforginfo.data

interface PreferenceRepository {

    fun saveGroupId(groupId: String)
    fun getGroupId(): String

}