package com.example.waroengujang_sembarangwes.viewmodel

import android.app.Application
import android.util.Log
import android.view.Menu
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.repository.MenuRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MenuViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MenuRepository
    var menusLD: LiveData<List<MenuEntity>> = MutableLiveData()

    init {
        val menuDao = MenuDatabase.buildDatabase(application.applicationContext).menuDao()
        repository = MenuRepository(menuDao)
        menusLD = repository.allMenus
        refresh()
    }

//    fun searchMenus(searchQuery: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val searchResult = repository.searchMenus(searchQuery)
//            menusLD = searchResult
//            refresh()
//        }
//    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            // Assuming you want to add a sample menu item manually
            repository.clearDatabase()
            val sampleMenu1 = MenuEntity(
                id = 1,
                foto = "https://www.natural-poultry.com/wp-content/uploads/2021/04/Ayam-rica-rica-1200x900.jpg",
                nama = "Ayam Rica-Rica",
                kategori = "Makanan",
                deskripsi = "Ayam Rica-Rica adalah hidangan khas dari Sulawesi, Indonesia. Ayam dimasak dengan saus pedas berbahan dasar cabai dan rempah-rempah. Rasanya sangat pedas dan lezat. Disajikan dengan nasi.",
                harga = 60000
            )
            val sampleMenu2 = MenuEntity(
                id = 2,
                foto = "https://www.anekawisata.com/wp-content/uploads/2023/03/Nasi-Goreng-Bandung.jpg",
                nama = "Nasi Goreng",
                kategori = "Makanan",
                deskripsi = "Nasi Goreng adalah hidangan nasi goreng khas Indonesia yang disajikan dengan telur, daging ayam, dan sayuran. Rasanya gurih dan lezat. Disajikan dengan bawang goreng dan acar.",
                harga = 45000
            )
            val sampleMenu3 = MenuEntity(
                id = 3,
                foto = "https://www.dapurkobe.co.id/wp-content/uploads/sate-ayam.jpg",
                nama = "Sate Ayam",
                kategori = "Makanan",
                deskripsi = "NSate Ayam adalah hidangan sate khas Indonesia yang terbuat dari potongan daging ayam yang ditusuk dan dipanggang dengan bumbu kacang. Rasanya gurih dan nikmat. Disajikan dengan saus kacang dan lontong.",
                harga = 25000
            )
            val sampleMenu4 = MenuEntity(
                id = 4,
                foto = "https://www.resepmama.id/cdn/recipes/1673384850650131-soto-ayam-bening.jpg",
                nama = "Soto Ayam",
                kategori = "Makanan",
                deskripsi = "Soto Ayam adalah sup ayam khas Indonesia dengan tambahan mie, telur, dan bumbu rempah. Rasanya hangat dan lezat. Nikmati rasa tradisional dalam setiap suapan.",
                harga = 35000
            )
            val sampleMenu5 = MenuEntity(
                id = 5,
                foto = "https://images.soco.id/162-rekomendasi-5-tempat-makan-bakmi-di-yogyakarta-2.jpg.jpeg",
                nama = "Bakmi",
                kategori = "Makanan",
                deskripsi = "Bakmi adalah hidangan mi khas Indonesia yang disajikan dengan berbagai bumbu dan topping. Rasanya gurih dan lezat. Nikmati kenikmatan mi dalam setiap suapan.",
                harga = 20000
            )
            val sampleMenu6 = MenuEntity(
                id = 6,
                foto = "https://dcostseafood.id/wp-content/uploads/2021/12/Es-teh-tawar-manis.jpg",
                nama = "Es Teh Manis",
                kategori = "Minuman",
                deskripsi = "Es Teh Manis adalah minuman dingin yang terbuat dari teh manis dan es batu. Sangat menyegarkan pada hari yang panas. Rasanya manis dan segar.",
                harga = 8000
            )
            val sampleMenu7 = MenuEntity(
                id = 7,
                foto = "https://i0.wp.com/resepkoki.id/wp-content/uploads/2021/02/Jus-Alpukat.jpg",
                nama = "Jus Alpukat",
                kategori = "Minuman",
                deskripsi = "Jus Alpukat adalah minuman segar yang terbuat dari daging alpukat yang di-blend dengan susu dan gula. Rasanya kaya dan lezat. Minuman klasik yang mengenyangkan.",
                harga = 15000
            )
            val sampleMenu8 = MenuEntity(
                id = 8,
                foto = "https://api.omela.com/storage/1139/conversions/1c0c9fd197d2d4c9b39046a60c2580b7-large.png",
                nama = "Teh Tarik",
                kategori = "Minuman",
                deskripsi = "Teh Tarik adalah minuman teh susu klasik dari Malaysia dan Indonesia. Rasanya manis dan krimi dengan busa yang dihasilkan oleh proses penyeduhan yang khas. Minuman yang memanjakan lidah.",
                harga = 10000
            )
            val sampleMenu9 = MenuEntity(
                id = 9,
                foto = "https://assets.pikiran-rakyat.com/crop/661x15:1728x977/x/photo/2021/12/20/1897445211.jpg",
                nama = "Martabak Manis",
                kategori = "Desert",
                deskripsi = "Martabak Manis adalah makanan penutup khas Indonesia yang terbuat dari adonan tipis yang digoreng dan diisi dengan cokelat, kacang, dan keju. Rasanya manis dan lezat. Menyuguhkan kelezatan kue dalam setiap suapan.",
                harga = 25000
            )
            val sampleMenu10 = MenuEntity(
                id = 10,
                foto = "https://img-global.cpcdn.com/recipes/742522984117a86e/680x482cq70/ice-cream-klepon-foto-resep-utama.jpg",
                nama = "Klepon Ice Cream",
                kategori = "Desert",
                deskripsi = "Klepon Ice Cream adalah eskrim lembut yang terinspirasi oleh makanan penutup tradisional Indonesia. Rasanya manis dan nikmat, dengan tekstur yang menyegarkan. Nikmati kelezatan klepon dalam setiap gigitan.",
                harga = 15000
            )
            repository.insertMenu(sampleMenu1)
            repository.insertMenu(sampleMenu2)
            repository.insertMenu(sampleMenu3)
            repository.insertMenu(sampleMenu4)
            repository.insertMenu(sampleMenu5)
            repository.insertMenu(sampleMenu6)
            repository.insertMenu(sampleMenu7)
            repository.insertMenu(sampleMenu8)
            repository.insertMenu(sampleMenu9)
            repository.insertMenu(sampleMenu10)
        }
    }

//    fun refresh() {
//        repository.clearDatabase()
//        val queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/anmp/menu.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                viewModelScope.launch(Dispatchers.IO) {
//                    // Parse the JSON response using Gson
//                    val sType = object : TypeToken<List<MenuEntity>>() {}.type
//                    val result = Gson().fromJson<List<MenuEntity>>(response, sType)
//
//                    result.forEach { menuEntity ->
//                        repository.insertMenu(menuEntity)
//                    }
//                }
//            },
//            { error ->
//                Log.e("showvoley", "Error: ${error.message}")
//            })
//        queue.add(stringRequest)
//    }
}
