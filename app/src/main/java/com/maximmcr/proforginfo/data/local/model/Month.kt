package com.maximmcr.proforginfo.data.local.model

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.text.DateFormatSymbols

@Entity
class Month {
    @Id
    var id: Long = 0
    @Backlink
    lateinit var orders: ToMany<Order>
    lateinit var timestamp: String
    lateinit var firebaseId: String
    var isPayed: Boolean = false

    fun name(): String = with(timestamp.split("-")) {
        return "${DateFormatSymbols.getInstance().months[this[1].toInt()]}, ${this[0]}"
    }
}

fun formatDate(year: Int, month: Int) = "$year-$month"