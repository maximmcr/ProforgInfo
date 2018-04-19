package com.maximmcr.proforginfo.data.local

import com.maximmcr.proforginfo.data.Repository
import com.maximmcr.proforginfo.data.local.model.*
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DB(boxStore: BoxStore) : Repository.DatabaseSource {

    private val monthes = boxStore.boxFor<Month>()
    private val orders = boxStore.boxFor<Order>()

    override fun addMonth(timestamp: String) : Boolean {
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
    override fun getAllMonthes() : Observable<List<Month>> {
        val query = monthes
                .query()
                .sort({ o1, o2 -> (o2.id - o1.id).toInt() })
                .build()
        return RxQuery
                .observable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    override fun removeMonth(id: Long) {
        monthes.remove(id)
    }
    override fun getMonth(id: Long) : Single<Month> {
        val query = monthes
                .query()
                .equal(Month_.id, id)
                .build()
        return RxQuery
                .single(query)
                .map { it[0] }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addOrder(monthId: Long, order: Order) : Boolean {
        val month: Month? = monthes.query()
                .equal(Month_.id, monthId)
                .build()
                .findUnique()
        month?.let { return it.orders.add(order) } ?: return false
    }
    override fun changeOrder(order: Order) {
        orders.put(order)
    }
    override fun deleteOrder(order: Order) {
        orders.remove(order)
    }
    override fun getOrder(id: Long): Single<Order> {
        val query = orders.query()
                .equal(Order_.id, id)
                .build()
        return RxQuery
                .single(query)
                .map { it[0] }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOrdersForMonth(monthId: Long): Observable<List<Order>> {
        val query = orders
                .query()
                .equal(Order_.monthId, monthId)
                .build()
        return RxQuery
                .observable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}