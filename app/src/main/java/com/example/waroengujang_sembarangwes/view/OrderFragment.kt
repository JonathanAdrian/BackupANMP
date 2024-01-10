package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.CartViewModel
import com.example.waroengujang_sembarangwes.viewmodel.MenuDetailViewModel
import com.example.waroengujang_sembarangwes.viewmodel.OrderDetailViewModel
import com.example.waroengujang_sembarangwes.viewmodel.OrderViewModel

class OrderFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderDetailViewModel: OrderDetailViewModel
    private lateinit var orderAdapter: RvAdapterOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderDetailViewModel = ViewModelProvider(requireActivity()).get(OrderDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        orderViewModel = ViewModelProvider(requireActivity()).get(OrderViewModel::class.java)
        orderAdapter = RvAdapterOrder(arrayListOf(), orderDetailViewModel)

        recyclerView = view.findViewById(R.id.rvOrder)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = orderAdapter

        observeViewModel()

        return view
    }

    fun observeViewModel() {
        orderViewModel.orderLD.observe(viewLifecycleOwner, Observer { orders ->
            orderAdapter.updateOrder(orders)
        })
    }
}
