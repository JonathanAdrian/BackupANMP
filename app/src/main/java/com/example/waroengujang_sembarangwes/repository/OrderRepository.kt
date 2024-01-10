package com.example.waroengujang_sembarangwes.repository

import androidx.lifecycle.LiveData
import com.example.waroengujang_sembarangwes.model.CartDao
import com.example.waroengujang_sembarangwes.model.CartItemEntity
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDao
import com.example.waroengujang_sembarangwes.model.OrderDetail

class OrderRepository(private val orderDao: OrderDao) {
    val allOrders: LiveData<List<Order>> = orderDao.getAllOrders()
    val allOrderDetails: LiveData<List<OrderDetail>> = orderDao.getAllOrderDetails()

    fun insertOrder(order: Order) {
        orderDao.insertOrder(order)
    }

    fun insertOrderDetail(orderDetail: OrderDetail) {
        orderDao.insertOrderDetail(orderDetail)
    }
}
