package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.repository.CartRepository
import com.example.waroengujang_sembarangwes.repository.MenuRepository
import com.example.waroengujang_sembarangwes.repository.OrderRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderViewModel(application: Application): AndroidViewModel(application) {
    private val repository: OrderRepository
    var orderLD: LiveData<List<Order>> = MutableLiveData()

    init {
        val orderDao = MenuDatabase.buildDatabase(application.applicationContext).orderDao()
        repository = OrderRepository(orderDao)
        orderLD = repository.allOrders
    }
}