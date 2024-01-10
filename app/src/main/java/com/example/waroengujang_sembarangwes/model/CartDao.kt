package com.example.waroengujang_sembarangwes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCartItem(cartItem: CartItemEntity)

    @Query("UPDATE cart_items SET quantity = quantity + :quantity WHERE menuItemId = :menuItemId")
    open fun updateCartItem(menuItemId: Long, quantity: Int)

    @Delete
    fun deleteCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): LiveData<List<CartItemEntity>>

    @Query("SELECT COUNT(*) FROM cart_items WHERE menuItemId = :menuItemId")
    fun getCartItemExistence(menuItemId: Long): LiveData<Int>

    @Query("DELETE FROM cart_items")
    fun clearAllCartItems()

}


