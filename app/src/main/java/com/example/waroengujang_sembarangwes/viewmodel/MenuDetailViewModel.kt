package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.view.Menu
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waroengujang_sembarangwes.model.MenuEntity

class MenuDetailViewModel : ViewModel() {
    private val _selectedMenu = MutableLiveData<MenuEntity>()

    // Expose the LiveData as read-only
    val selectedMenu: LiveData<MenuEntity>
        get() = _selectedMenu

    // Function to set the selected menu
    fun setSelectedMenu(menu: MenuEntity) {
        _selectedMenu.value = menu
    }
}

