package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.waroengujang_sembarangwes.model.CartItemEntity
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDetail
import com.example.waroengujang_sembarangwes.repository.CartRepository
import com.example.waroengujang_sembarangwes.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CartRepository
    private val orderRepository: OrderRepository

    private val _tax = MutableLiveData<Double>()
    val tax: LiveData<Double> get() = _tax
    private val _subtotal = MutableLiveData<Double>()
    val subtotal: LiveData<Double> get() = _subtotal
    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> get() = _total

    var cartItemsLD: LiveData<List<CartItemEntity>> = MutableLiveData()
    init {
        val cartDao = MenuDatabase.buildDatabase(application.applicationContext).cartDao()
        repository = CartRepository(cartDao)
        cartItemsLD = repository.allCartItems

        val orderDao = MenuDatabase.buildDatabase(application.applicationContext).orderDao()
        orderRepository = OrderRepository(orderDao)
    }

    fun insertCartItem(cartItemEntity: CartItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cartItemEntity)
        }
    }
    fun updateCartItem(menuItemId: Long, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCartItem(menuItemId,quantity)
        }
    }
    fun getCartItemExistence(menuItemId: Long): LiveData<Int> {
        return repository.getCartItemExistence(menuItemId)
    }
    fun calculateCartTotals(cartItems: List<CartItemEntity>) {

        val subtotal = cartItems.sumByDouble { calculateCartItemSubtotal(it) }
        val tax = calculateTax(subtotal)
        val total = subtotal + tax

        _subtotal.postValue(subtotal)
        _tax.postValue(tax)
        _total.postValue(total)
    }
    private fun calculateCartItemSubtotal(cartItemEntity: CartItemEntity): Double {
        val price = cartItemEntity.harga?.toDouble() ?: 0.0
        return price * (cartItemEntity.quantity ?: 0)
    }

    private fun calculateTax(subtotal: Double): Double {
        return subtotal * 0.10
    }

    fun insertOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.insertOrder(order)
        }
    }

    fun insertOrderDetail(orderDetail: OrderDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.insertOrderDetail(orderDetail)
        }
    }
}
