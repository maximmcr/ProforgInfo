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

    var m: Int = 0
    var mBus: Int = 0
    var mTrolleybus: Int = 0
    var mTram: Int = 0
    var m46: Int = 0
    var mBus46: Int = 0
    var mTrolleybus46: Int = 0
    var mTram46: Int = 0
    var m62: Int = 0
    var mBus62: Int = 0
    var mTrolleybus62: Int = 0
    var mTram62: Int = 0
}