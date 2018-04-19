package com.maximmcr.proforginfo.data.local.model

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Month {
    @Id var id: Long = 0
    @Backlink
    lateinit var orders: ToMany<Order>
    lateinit var name: String
    lateinit var timestamp: String
    lateinit var firebaseId: String
}