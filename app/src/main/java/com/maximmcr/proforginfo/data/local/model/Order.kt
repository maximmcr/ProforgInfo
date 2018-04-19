package com.maximmcr.proforginfo.data.local.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class Order {
    @Id
    var id: Long = 0
    lateinit var month: ToOne<Month>
    lateinit var customerName: String

    var metroUnlim: Int = 0
    var metroBusUnlim: Int = 0
    var metroTrolleybusUnlim: Int = 0
    var metroTramUnlim: Int = 0

    var metro46: Int = 0
    var metroBus46: Int = 0
    var metroTrolleybus46: Int = 0
    var metroTram46: Int = 0

    var metro62: Int = 0
    var metroBus62: Int = 0
    var metroTrolleybus62: Int = 0
    var metroTram62: Int = 0
}