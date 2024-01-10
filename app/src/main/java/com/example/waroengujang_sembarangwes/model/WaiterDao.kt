package com.example.waroengujang_sembarangwes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WaiterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWaiter(waiter: Waiter)

    @Query("SELECT * FROM waiter_table")
    fun getAllWaiters(): LiveData<List<Waiter>>

    @Query("DELETE FROM waiter_table")
    fun clearAllWaiters()

    @Query("UPDATE waiter_table SET password = :password WHERE username = :username")
    fun updateWaiterPassword(password: String, username: String)
}


