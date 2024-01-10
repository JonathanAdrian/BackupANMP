package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.OrderDetailViewModel
import androidx.navigation.fragment.findNavController
import com.example.waroengujang_sembarangwes.model.MenuDao
import com.example.waroengujang_sembarangwes.model.MenuDatabase
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.squareup.picasso.Picasso


class OrderDetailFragment : Fragment() {

    private lateinit var viewModel: OrderDetailViewModel

    private lateinit var txtTableOd: TextView
    private lateinit var txtDurationOd: TextView
    private lateinit var txtHargaOd: TextView
    private lateinit var txtOrderOd: TextView
    private lateinit var btnOrderMore: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_detail, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(OrderDetailViewModel::class.java)

        txtTableOd = view.findViewById(R.id.txtTableOd)
        txtDurationOd = view.findViewById(R.id.txtDurationOD)
        txtHargaOd = view.findViewById(R.id.txtHargaOd)
        txtOrderOd = view.findViewById(R.id.txtOrderOD)
        btnOrderMore=view.findViewById(R.id.btnOrderMore)

        viewModel.selectedOrder.observe(viewLifecycleOwner, { order ->
            txtTableOd.text = order.no_table.toString()
            txtDurationOd.text = order.duration
            txtHargaOd.text = order.harga_total.toString()
        })

        viewModel.orderdetailLD.observe(viewLifecycleOwner, { orderDetails ->
            val formattedMenuDetails = orderDetails.joinToString { "Quantity:${it.quantity}, Menu ID:${it.menuItemId}" }
            txtOrderOd.text = formattedMenuDetails
        })

        btnOrderMore.setOnClickListener {
            val action = OrderDetailFragmentDirections.ActionItemMenu()
            Navigation.findNavController(it).navigate(action)
        }

        return view
    }
}