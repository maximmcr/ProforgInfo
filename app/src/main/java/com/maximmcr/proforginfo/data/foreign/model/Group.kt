package com.maximmcr.proforginfo.data.foreign.model


data class Group(
		var admin: String,
		var members: List<String>,
		var name: String,
		var payment: Int
)