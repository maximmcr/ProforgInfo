package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.local.model.Month
import com.maximmcr.proforginfo.data.local.model.Order
import io.reactivex.Observable
import io.reactivex.Single

interface LocalOrderRepo {
    fun add(monthId: Long, order: Order): Boolean
    fun remove(order: Order)
    fun change(order: Order)
    fun get(id: Long): Single<Order>
    fun getForMonth(monthId: Long): Observable<List<Order>>
}

interface LocalMonthRepo {
    fun add(timestamp: String): Boolean
    fun remove(id: Long)
    fun getAll(): Observable<List<Month>>
    fun syncWithRemote(timestamp: String, firebaseId: String): Single<Boolean>
    fun changePaymentStatus(status: Boolean, timestamp: String): Single<Boolean>
}
