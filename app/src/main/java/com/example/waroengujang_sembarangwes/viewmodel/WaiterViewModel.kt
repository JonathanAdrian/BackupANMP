package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.model.Waiter
import com.example.waroengujang_sembarangwes.repository.MenuRepository
import com.example.waroengujang_sembarangwes.repository.WaiterRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaiterViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WaiterRepository
    var waiterLD: LiveData<List<Waiter>> = MutableLiveData()

    init {
        val waiterDao = MenuDatabase.buildDatabase(application.applicationContext).waiterDao()
        repository = WaiterRepository(waiterDao)
        waiterLD = repository.allWaiters
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            // Assuming you want to add a sample menu item manually
            repository.clearDatabase()
            val sampleWaiter = Waiter(
                username = "jennie",
                password = "111",
                img_url = "https://www.hollywoodreporter.com/wp-content/uploads/2023/05/Jennie-Kim-2023-Met-Gala-getty-1486924742-H-2023.jpg?w=1296",
                work_since = "January",
                name = "jennie"
            )
            repository.insertWaiter(sampleWaiter)
        }
    }
    fun updateWaiterPassword(password: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWaiterPassword(password, username)
        }
    }

    fun getWaiter(username: String): LiveData<Waiter?> {
        return repository.getWaiter(username)
    }
}