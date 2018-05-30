package com.maximmcr.proforginfo.data.settings

import android.content.SharedPreferences
import com.maximmcr.proforginfo.data.PreferenceRepository
import javax.inject.Inject

class PreferenceRepoImpl @Inject constructor(private val prefs: SharedPreferences): PreferenceRepository {

    companion object {
        const val GROUP_ID = "groupId"
    }

    override fun saveGroupId(groupId: String) = with(prefs.edit()) {
        putString(GROUP_ID, groupId)
        apply()
    }

    override fun getGroupId(): String = prefs.getString(GROUP_ID, "")
}