package com.example.waroengujang_sembarangwes.repository

import androidx.lifecycle.LiveData
import com.example.waroengujang_sembarangwes.model.MenuDao
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.model.Waiter
import com.example.waroengujang_sembarangwes.model.WaiterDao

class WaiterRepository(private val waiterDao: WaiterDao) {
    val allWaiters: LiveData<List<Waiter>> = waiterDao.getAllWaiters()

    fun insertWaiter(waiter: Waiter) {
        waiterDao.insertWaiter(waiter)
    }

    fun clearDatabase() {
        waiterDao.clearAllWaiters()
    }

    fun getWaiter(username: String): LiveData<Waiter?> {
        return waiterDao.getWaiter(username)
    }

    fun updateWaiterPassword(password: String, username: String){
        return waiterDao.updateWaiterPassword(password, username)
    }
}
