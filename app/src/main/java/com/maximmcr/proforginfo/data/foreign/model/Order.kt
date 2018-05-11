package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.database.Exclude

data class Order(
		var group: String,
		var isPayed: Boolean = false,
		var month: String,
		var user: String,

		var m: Int = 0,
		var mBus: Int = 0,
		var mTrolleybus: Int = 0,
		var mTram: Int = 0,
		var m46: Int = 0,
		var mBus46: Int = 0,
		var mTrolleybus46: Int = 0,
		var mTram46: Int = 0,
		var m62: Int = 0,
		var mBus62: Int = 0,
		var mTrolleybus62: Int = 0,
		var mTram62: Int = 0,

		@Exclude var id: String? = null
)