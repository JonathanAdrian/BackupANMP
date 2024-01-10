package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.CartItemEntity
import com.example.waroengujang_sembarangwes.viewmodel.CartViewModel
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso

class MenuDetailFragment : Fragment() {
    private lateinit var viewModel: MenuDetailViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartItemAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MenuDetailViewModel::class.java)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        cartAdapter = CartItemAdapter(cartViewModel.cartItemsLD.value ?: ArrayList(), sharedViewModel)

        viewModel.selectedMenu.observe(viewLifecycleOwner, { menuEntity ->
            val txtNamaDetail = view?.findViewById<TextView>(R.id.txtNamaDetail)
            val txtHargaDetail = view?.findViewById<TextView>(R.id.txtHargaDetail)
            val txtDeskripsiDetail = view?.findViewById<TextView>(R.id.txtDeskripsiDetail)
            val imgFotoDetail = view?.findViewById<ImageView>(R.id.imgFotoDetail)

            txtNamaDetail?.text = menuEntity?.nama
            txtHargaDetail?.text = menuEntity?.harga?.toString()
            txtDeskripsiDetail?.text = menuEntity?.deskripsi
            Picasso.get().load(menuEntity?.foto).into(imgFotoDetail)
        })

        var jumlah = 1
        var txtJumlahDetail = view?.findViewById<TextView>(R.id.txtJumlahDetail)
        val btnAddDetail = view?.findViewById<Button>(R.id.btnAddDetail)
        val btnTambahDetail = view?.findViewById<Button>(R.id.btnTambahDetail)
        val btnKurangDetail = view?.findViewById<Button>(R.id.btnKurangDetail)

        btnTambahDetail?.setOnClickListener{
            jumlah++
            txtJumlahDetail?.text = jumlah.toString()
        }

        btnKurangDetail?.setOnClickListener{
            if (jumlah > 1) {
                jumlah--
                txtJumlahDetail?.text = jumlah.toString()
            }
        }

        // Add to cart disini.
        btnAddDetail?.setOnClickListener {
            val selectedMenu = viewModel.selectedMenu.value

            selectedMenu?.let { menuItem ->
                val selectedQuantity = txtJumlahDetail?.text.toString().toInt()

                val doesExistLiveData = cartViewModel.getCartItemExistence(menuItem.id)

                doesExistLiveData.observe(viewLifecycleOwner) { doesExist ->
                    if (doesExist > 0) {
                        // Item exists, update the quantity
                        cartViewModel.updateCartItem(menuItem.id, selectedQuantity)
                    } else {
                        // Item doesn't exist, insert a new item
                        val cartItemEntity = CartItemEntity(
                            menuItemId = menuItem.id,
                            nama = menuItem.nama ?: "",
                            kategori = menuItem.kategori ?: "",
                            harga = menuItem.harga,
                            foto = menuItem.foto ?: "",
                            quantity = selectedQuantity
                        )
                        cartViewModel.insertCartItem(cartItemEntity)
                    }

                    // Update your sharedViewModel and adapter here
                    sharedViewModel.cartItemEntity.value = cartViewModel.cartItemsLD.value
                    sharedViewModel.cartAdapter.value = cartAdapter

                    doesExistLiveData.removeObservers(viewLifecycleOwner)
                }
            }
        }
    }
}