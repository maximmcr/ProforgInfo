package com.maximmcr.proforginfo.data

import com.maximmcr.proforginfo.data.local.model.Month
import com.maximmcr.proforginfo.data.local.model.Order
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {

    interface DatabaseSource {

        fun addMonth(timestamp: String): Boolean
        fun removeMonth(id: Long)
        fun getMonth(id: Long): Single<Month>
        fun getAllMonthes(): Observable<List<Month>>

        fun addOrder(monthId: Long, order: Order): Boolean
        fun changeOrder(order: Order)
        fun deleteOrder(order: Order)
        fun getOrder(id: Long): Single<Order>

        fun getOrdersForMonth(monthId: Long): Observable<List<Order>>

    }

    interface ForeignSource {
        fun getMonth(timestamp: String)
        fun getMonthes()
        fun getGroupInfo()
    }

    interface ForeignUserSource : ForeignSource {

        fun addOrUpdateGroupOrder(timestamp: String, isPaid: Boolean)
        fun changePaymentStatus(timestamp: String, isPaid: Boolean)

    }

    interface ForeignAdminSource : ForeignSource {

        fun addMonth(timestamp: String)
        fun updateGroupInfo()
        fun getMonthOrders(timestamp: String)
        fun getUsers()
        fun getUser(userId: String)
        fun getUserLastOrder(userId: String)
        fun getUserOrders(userId: String)
        fun makeUserAdmin(userId: String, isAdmin: Boolean)

    }

}