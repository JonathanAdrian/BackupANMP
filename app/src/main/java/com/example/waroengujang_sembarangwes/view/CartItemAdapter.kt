package com.example.waroengujang_sembarangwes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.CartItemEntity
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso

class CartItemAdapter(private var cartItemEntity: List<CartItemEntity>,
                      private val sharedViewModel: SharedViewModel
)
    : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItemEntity[position]

        var txtNama = holder.itemView.findViewById<TextView>(R.id.txtNamaItem)
        var txtHarga = holder.itemView.findViewById<TextView>(R.id.txtHargaItemCart)
        var txtQty = holder.itemView.findViewById<TextView>(R.id.txtItemCartQty)
        val imgFoto = holder.itemView.findViewById<ImageView>(R.id.imgFotoCart)
        val imageButtonAdd = holder.itemView.findViewById<ImageButton>(R.id.imageButtonAdd)
        val imageButtonMin = holder.itemView.findViewById<ImageButton>(R.id.imageButtonMin)

        val nama = cartItem.nama
        val harga = cartItem.harga
        val qty = cartItem.quantity
        val foto = cartItem.foto

        txtNama.text = "$nama"
        txtHarga.text = "Rp. $harga"
        txtQty.text = "$qty"
        Picasso.get().load(foto).into(imgFoto)

        // Set the initial quantity
        txtQty.text = cartItem.quantity.toString()

        imageButtonAdd.setOnClickListener {
            cartItem.quantity = cartItem.quantity!! + 1
            txtQty.text = cartItem.quantity.toString()
            sharedViewModel.cartItemEntity.value = ArrayList(cartItemEntity)
        }

        imageButtonMin.setOnClickListener {
            if (cartItem.quantity!! > 1) {
                cartItem.quantity = cartItem.quantity!! - 1
                txtQty.text = cartItem.quantity.toString()
            }
            sharedViewModel.cartItemEntity.value = ArrayList(cartItemEntity)

        }
    }

    fun updateCart(newCart: List<CartItemEntity>) {
        cartItemEntity = newCart
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cartItemEntity.size
    }
}