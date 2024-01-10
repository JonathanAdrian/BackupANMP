package com.example.waroengujang_sembarangwes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menuEntity: MenuEntity)

    @Query("SELECT * FROM menu_table")
    fun getAllMenus(): LiveData<List<MenuEntity>>

    @Query("DELETE FROM menu_table")
    fun clearAllMenus()

    @Query("SELECT * FROM menu_table WHERE id = :menuId")
    fun getMenuById(menuId: Long): MenuEntity?

//    @Query("SELECT * FROM menu_table WHERE nama LIKE :searchQuery")
//    fun searchMenus(searchQuery: String): LiveData<List<MenuEntity>>
}

