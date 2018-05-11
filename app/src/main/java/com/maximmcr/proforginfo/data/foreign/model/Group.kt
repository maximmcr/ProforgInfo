package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.database.Exclude

data class Group(
		var admin: String,
		var name: String,
		var payment: Int,
        @Exclude var id: String? = null
)