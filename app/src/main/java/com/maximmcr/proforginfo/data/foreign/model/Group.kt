package com.maximmcr.proforginfo.data.foreign.model

import com.google.firebase.firestore.DocumentReference

data class Group(
        var name: String = "",
        var payment: String = "",
        var addInfo: String = "",
        var id: String = "",
        var admin: DocumentReference? = null,
        var users: List<DocumentReference> = mutableListOf()
)