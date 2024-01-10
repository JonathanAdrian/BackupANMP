package com.example.waroengujang_sembarangwes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrderDetail(orderDetail: OrderDetail)

    @Delete
    fun deleteOrder(order: Order)

    @Query("SELECT * FROM order_table")
    fun getAllOrders(): LiveData<List<Order>>

    @Query("DELETE FROM order_table")
    fun clearAllOrders()

    @Query("SELECT * FROM order_detail_table")
    fun getAllOrderDetails(): LiveData<List<OrderDetail>>

}


