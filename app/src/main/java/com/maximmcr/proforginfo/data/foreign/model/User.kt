package com.maximmcr.proforginfo.data.foreign.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val name: String,
        val phone: String,
        val addContact: String,
        val groupName: String
) : Parcelable