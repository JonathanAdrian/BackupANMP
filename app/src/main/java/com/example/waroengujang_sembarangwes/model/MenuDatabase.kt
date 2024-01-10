package com.example.waroengujang_sembarangwes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MenuEntity::class, CartItemEntity::class, Order::class, OrderDetail::class, Waiter::class],
    version = 7,
    exportSchema = false
)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun waiterDao(): WaiterDao

    companion object {
        @Volatile
        private var instance: MenuDatabase? = null

        private val LOCK = Any()

        fun buildDatabase(context: Context): MenuDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "menu_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}


