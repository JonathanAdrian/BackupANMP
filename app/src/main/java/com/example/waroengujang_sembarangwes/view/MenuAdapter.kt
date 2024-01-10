package com.example.waroengujang_sembarangwes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.squareup.picasso.Picasso


class MenuAdapter(private var menuEntities: List<MenuEntity>, private val menuDetailViewModel: MenuDetailViewModel)
    : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){
    class MenuViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        var txtNama = holder.itemView.findViewById<TextView>(R.id.textView)
        var txtHarga = holder.itemView.findViewById<TextView>(R.id.txtTableOrder)
        var txtKategori = holder.itemView.findViewById<TextView>(R.id.txtHargaOrder)
        var imgFoto = holder.itemView.findViewById<ImageView>(R.id.imgFoto)
        var btnDetail = holder.itemView.findViewById<Button>(R.id.btnDetail)

        val currentItem = menuEntities[position]

        txtNama.text = currentItem.nama
        txtHarga.text = currentItem.harga.toString()
        txtKategori.text = currentItem.kategori
        Picasso.get().load(currentItem.foto).into(imgFoto)

        btnDetail.setOnClickListener {
            menuDetailViewModel.setSelectedMenu(currentItem)

            val action = MenuFragmentDirections.actionMenuDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateMenu(newMenuEntities: List<MenuEntity>) {
        menuEntities = newMenuEntities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menuEntities.size
    }
}