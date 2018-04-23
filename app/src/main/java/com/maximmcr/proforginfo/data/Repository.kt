package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.local.model.Month
import com.maximmcr.proforginfo.data.local.model.Order
import io.reactivex.Observable
import io.reactivex.Single

interface UserOrderRepo {
    fun add(monthId: Long, order: Order): Boolean
    fun remove(order: Order)
    fun change(order: Order)
    fun get(id: Long): Single<Order>
    fun getForMonth(monthId: Long): Observable<List<Order>>
}

interface UserMonthRepo {
    fun add(timestamp: String): Boolean
    fun remove(id: Long)
    fun getAll(): Observable<List<Month>>
    fun syncWithRemote(timestamp: String, firebaseId: String): Single<Boolean>

    fun getRemoteId(timestamp: String): Single<String>
    fun sendOrder(timestamp: String): Single<Boolean>
    fun removeMonthOrder()

    fun changePaymentStatus(timestamp: String, status: Boolean): Single<Boolean>
}

interface GroupRepo {
    fun add()
    fun get()
    fun getAll()
    fun change()
    fun delete()

    fun isAdmin()
    fun isLoggedIn()
}

interface PriceRepo {
    fun get()
    fun change()
}

interface GroupOrderRepo {
    fun getOrdersByMonth()
    fun getOrderById()
}

interface GroupUserRepo {
    fun getAll()
    fun get()
}