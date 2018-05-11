package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.foreign.model.Group
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

    fun getRemoteId(groupId: String): Observable<Pair<String, String>>
    fun sendOrder(order: com.maximmcr.proforginfo.data.foreign.model.Order): Single<Boolean>

    fun changePaymentStatus(status: Boolean, userId: String, monthId: String, timestamp: String): Single<Boolean>
}

interface GroupRepo {
    fun add(group: Group): Single<String>
    fun get(id: String): Single<Group>
    fun getAll(): Observable<Group>
    fun change(group: Group): Single<Boolean>
    fun delete(groupId: String): Single<Boolean>

    fun isAdmin(userId: String, groupId: String): Single<Boolean>
}

interface PriceRepo {
    fun get()
    fun change()
}

interface GroupOrderRepo {
    fun getOrdersByMonth()
    fun getOrderById()
}

interface GroupUsersRepo {
    fun getAll()
    fun get()
    fun add()
    fun delete()
}

interface AuthRepo {
    fun isLoggedIn(): Boolean
    fun login()
    fun logout()
}