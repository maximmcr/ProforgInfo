package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.database.Exclude

data class Month(
        var group: String,
        var timestamp: String,
        @Exclude var id: String? = null
)