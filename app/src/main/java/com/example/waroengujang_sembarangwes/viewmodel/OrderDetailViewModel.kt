package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDetail
import com.example.waroengujang_sembarangwes.repository.MenuRepository
import com.example.waroengujang_sembarangwes.repository.OrderRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _selectedOrder = MutableLiveData<Order>()
    private val repository: OrderRepository
    var orderdetailLD: LiveData<List<OrderDetail>> = MutableLiveData()

    init {
        val orderDao = MenuDatabase.buildDatabase(application.applicationContext).orderDao()
        repository = OrderRepository(orderDao)
        orderdetailLD = repository.allOrderDetails
    }

    // Expose the LiveData as read-only
    val selectedOrder: LiveData<Order>
        get() = _selectedOrder

    // Function to set the selected menu
    fun setSelectedOrder(order: Order) {
        _selectedOrder.value = order
    }

//    fun deleteOrderDetail(order_id: Long){
//
//    }
}