package com.example.waroengujang_sembarangwes.repository

import androidx.lifecycle.LiveData
import com.example.waroengujang_sembarangwes.model.CartDao
import com.example.waroengujang_sembarangwes.model.CartItemEntity
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDetail

class CartRepository(private val cartDao: CartDao) {
    val allCartItems: LiveData<List<CartItemEntity>> = cartDao.getAllCartItems()

    fun insertCartItem(cartItemEntity: CartItemEntity) {
        cartDao.insertCartItem(cartItemEntity)
    }

    fun deleteCartItem(cartItemEntity: CartItemEntity) {
        cartDao.deleteCartItem(cartItemEntity)
    }

    fun updateCartItem(menuItemId: Long, quantity: Int) {
        cartDao.updateCartItem(menuItemId, quantity)
    }

    fun getCartItemExistence(menuItemId: Long): LiveData<Int>{
        return cartDao.getCartItemExistence(menuItemId)
    }

    fun clearDatabase() {
        cartDao.clearAllCartItems()
    }
}
