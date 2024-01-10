package com.example.waroengujang_sembarangwes.repository

import androidx.lifecycle.LiveData
import com.example.waroengujang_sembarangwes.model.MenuDao
import com.example.waroengujang_sembarangwes.model.MenuEntity

class MenuRepository(private val menuDao: MenuDao) {
    val allMenus: LiveData<List<MenuEntity>> = menuDao.getAllMenus()

    fun insertMenu(menuEntity: MenuEntity) {
        menuDao.insertMenu(menuEntity)
    }

//    fun searchMenus(searchQuery: String): LiveData<List<MenuEntity>> {
//        return menuDao.searchMenus(searchQuery)
//    }

    fun clearDatabase() {
        menuDao.clearAllMenus()
    }
}
