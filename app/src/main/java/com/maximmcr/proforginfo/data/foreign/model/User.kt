package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.database.Exclude


data class User(
		var additionalContact: String,
		var name: String,
		var phone: String,
		var universityGroup: String,
		@Exclude var id: String? = null
)