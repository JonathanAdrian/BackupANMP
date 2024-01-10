package com.example.waroengujang_sembarangwes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.model.Order
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.model.MenuEntity
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.example.waroengujang_sembarangwes.viewmodel.OrderDetailViewModel


class RvAdapterOrder(private var orderList: List<Order>, private val orderDetailViewModel: OrderDetailViewModel)
    : RecyclerView.Adapter<RvAdapterOrder.OrderViewHolder>() {

    class OrderViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_layout, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = orderList[position]

        val tableNo = holder.itemView.findViewById<TextView>(R.id.txtTableOrder)
        val totalPrice = holder.itemView.findViewById<TextView>(R.id.txtHargaOrder)
        val duration = holder.itemView.findViewById<TextView>(R.id.txtDurationOrder)
        val status = holder.itemView.findViewById<TextView>(R.id.txtStatusOrder)
        val btnDetail = holder.itemView.findViewById<TextView>(R.id.btnOrderDetail)

        tableNo.text = currentOrder.no_table.toString()
        totalPrice.text ="IDR "+ currentOrder.harga_total.toString()
        duration.text = currentOrder.duration
        status.text = currentOrder.status.toString()

        btnDetail.setOnClickListener {
            orderDetailViewModel.setSelectedOrder(currentOrder)
            val action = OrderFragmentDirections.ActionOrderDetail()
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun updateOrder(newOrderList: List<Order>) {
        orderList = newOrderList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}
