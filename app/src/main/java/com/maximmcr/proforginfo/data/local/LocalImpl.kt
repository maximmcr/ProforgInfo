package com.maximmcr.proforginfo.data.local

import com.maximmcr.proforginfo.data.LocalMonthRepo
import com.maximmcr.proforginfo.data.LocalOrderRepo
import com.maximmcr.proforginfo.data.local.model.Month
import com.maximmcr.proforginfo.data.local.model.Month_
import com.maximmcr.proforginfo.data.local.model.Order
import com.maximmcr.proforginfo.data.local.model.Order_
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalOrderImpl @Inject constructor(boxStore: BoxStore) : LocalOrderRepo {

    private val monthes = boxStore.boxFor<Month>()
    private val orders = boxStore.boxFor<Order>()

    override fun add(monthId: Long, order: Order): Boolean {
        val month: Month? = monthes.query()
                .equal(Month_.id, monthId)
                .build()
                .findUnique()
        month?.let { return it.orders.add(order) } ?: return false
    }

    override fun remove(order: Order) {
        orders.remove(order)
    }

    override fun change(order: Order) {
        orders.put(order)
    }

    override fun get(id: Long): Single<Order> {
        val query = orders.query()
                .equal(Order_.id, id)
                .build()
        return RxQuery
                .single(query)
                .map { it[0] }
    }

    override fun getForMonth(monthId: Long): Observable<List<Order>> {
        val query = orders
                .query()
                .equal(Order_.monthId, monthId)
                .build()
        return RxQuery.observable(query)
    }
}

class LocalMonthImpl @Inject constructor(boxStore: BoxStore) : LocalMonthRepo {

    private val monthes = boxStore.boxFor<Month>()

    override fun add(timestamp: String): Boolean {
        monthes.query()
                .contains(Month_.timestamp, timestamp)
                .build()
                .findFirst()
                ?.let { return false }

        val month = Month().apply {
            this.timestamp = timestamp
        }
        monthes.put(month)
        return true
    }

    override fun remove(id: Long) {
        monthes.remove(id)
    }

    override fun getAll(): Observable<List<Month>> {
        val query = monthes
                .query()
                .sort({ o1, o2 -> (o2.id - o1.id).toInt() })
                .build()
        return RxQuery
                .observable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun syncWithRemote(timestamp: String, firebaseId: String): Single<Boolean> {
        monthes.find(Month_.timestamp, timestamp).first().fMonthId = firebaseId
        return Single.just(true)
    }

    override fun changePaymentStatus(status: Boolean, timestamp: String): Single<Boolean> {
        monthes.find(Month_.timestamp, timestamp).first().isPayed = status
        return Single.just(status)
    }
}