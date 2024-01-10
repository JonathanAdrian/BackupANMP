package com.example.waroengujang_sembarangwes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val foto: String?,
    val nama: String?,
    val kategori: String?,
    val deskripsi: String?,
    val harga: Int?
)
@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey
    val order_id: String,
    val no_table: Int,
    val harga_total: Int,
    val duration: String,
    val status: Int
)
data class Waiter(
    val img_url:String,
    val work_since:String,
    val name:String
)
@Entity(
    tableName = "order_detail_table",
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["order_id"],
            childColumns = ["order_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("order_id")]
)
data class OrderDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val order_id: String,
    val table: Int,
    val duration: String,
    val menuItemId: Long,
    val quantity: Int?
)


@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val menuItemId: Long,
    val nama: String?,
    val kategori: String?,
    val harga: Int?,
    val foto: String?,
    var quantity: Int?
)
