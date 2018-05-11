package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.database.Exclude


data class Prices(
		var m: Int,
		var m46: Int,
		var m62: Int,
		var mAuto: Int,
		var mAuto46: Int,
		var mAuto62: Int,
		var mTram: Int,
		var mTram46: Int,
		var mTram62: Int,
		var mTrolleybus: Int,
		var mTrolleybus46: Int,
		var mTrolleybus62: Int,
		@Exclude var id: String? = null
)