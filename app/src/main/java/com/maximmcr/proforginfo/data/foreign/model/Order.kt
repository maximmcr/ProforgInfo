package com.maximmcr.proforginfo.data.foreign.model

data class Order(
		var group: String,
		var isPayed: Boolean,
		var timestamp: String,
		var userId: String
)