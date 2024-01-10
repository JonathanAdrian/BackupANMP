package com.example.waroengujang_sembarangwes.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.model.OrderDetail
import com.example.waroengujang_sembarangwes.viewmodel.CartViewModel
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.UUID

class CartFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartItemAdapter
    private lateinit var txtSubtotal: TextView
    private lateinit var txtTax: TextView
    private lateinit var txtTotal: TextView
    private lateinit var btnProses: Button
    private lateinit var txtTableNum: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)
        cartAdapter = CartItemAdapter(ArrayList(), sharedViewModel)

        recyclerView.adapter = cartAdapter

        observeViewModel()

        txtSubtotal = view.findViewById(R.id.txtSubtotal)
        txtTax = view.findViewById(R.id.txtTax)
        txtTotal = view.findViewById(R.id.txtTotal)
        btnProses = view.findViewById(R.id.btnProses)
        txtTableNum = view.findViewById(R.id.txtTableNum)

        sharedViewModel.tableNumber.observe(viewLifecycleOwner, { tableNumber ->
            txtTableNum.text = "Table $tableNumber"
        })

        sharedViewModel.cartItemEntity.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.updateCart(cartItems)
            cartViewModel.calculateCartTotals(cartItems)
        }

        cartViewModel.subtotal.observe(viewLifecycleOwner) { subtotal ->
            txtSubtotal.text = "Subtotal: IDR $subtotal"
        }

        cartViewModel.tax.observe(viewLifecycleOwner) { tax ->
            txtTax.text = "Tax (10%): IDR $tax"
        }

        cartViewModel.total.observe(viewLifecycleOwner) { total ->
            txtTotal.text = "Total: IDR $total"
        }

        val btnProses = view.findViewById<Button>(R.id.btnProses)
        btnProses.setOnClickListener {
            val currentTime: Date = Calendar.getInstance().getTime()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val currentTimeString: String = dateFormat.format(currentTime)

            val order = Order(
                order_id = UUID.randomUUID().toString(),
                no_table = 1,
                harga_total = cartViewModel.total.value?.toInt() ?: 0,
                duration = currentTimeString,
                status = 0
            )
            cartViewModel.insertOrder(order)

            cartViewModel.cartItemsLD.value?.forEach { cartItem ->
                val orderDetail = OrderDetail(
                    order_id = order.order_id,
                    table = 1,
                    duration = currentTimeString,
                    menuItemId = cartItem.menuItemId,
                    quantity = cartItem.quantity
                )
                cartViewModel.insertOrderDetail(orderDetail)
            }
        }

        return view
    }

    fun observeViewModel() {
        cartViewModel.cartItemsLD.observe(viewLifecycleOwner, Observer { cartItemEntities ->
            cartAdapter.updateCart(cartItemEntities)
            cartViewModel.calculateCartTotals(cartItemEntities)
        })
    }
}