package com.example.waroengujang_sembarangwes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.HomeViewModel
import com.example.waroengujang_sembarangwes.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmitHome = view.findViewById<Button>(R.id.btnSubmitHome)
        val editTableHome = view.findViewById<EditText>(R.id.editTableHome)
        val txtChangeHome = view.findViewById<TextView>(R.id.txtChangeHome)
        val txtTableHome = view.findViewById<TextView>(R.id.txtTableHome)
        val imgTableHome = view.findViewById<ImageView>(R.id.imgTableHome)
        val txtEnterTableHome = view.findViewById<TextView>(R.id.txtEnterTableHome)
        val txtServingHome = view.findViewById<TextView>(R.id.txtServingHome)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        btnSubmitHome.setOnClickListener {
            val tableNumber = editTableHome.text.toString()
            if (tableNumber.isNotEmpty()) {
                viewModel.setTableNumber(tableNumber)
                sharedViewModel.tableNumber.value = tableNumber

                txtServingHome.text = "Currently Serving"
                txtTableHome.visibility = View.VISIBLE
                txtChangeHome.visibility = View.VISIBLE
                imgTableHome.visibility = View.INVISIBLE
                txtEnterTableHome.visibility = View.INVISIBLE
                editTableHome.visibility = View.INVISIBLE
                btnSubmitHome.visibility = View.INVISIBLE
            }
        }

        viewModel.tableNumber.observe(viewLifecycleOwner, { number ->
            txtTableHome.text = "Table $number"
        })

        txtChangeHome.setOnClickListener {
            txtServingHome.text = "Serve New Customer"
            txtTableHome.visibility = View.INVISIBLE
            txtChangeHome.visibility = View.INVISIBLE
            imgTableHome.visibility = View.VISIBLE
            txtEnterTableHome.visibility = View.VISIBLE
            editTableHome.visibility = View.VISIBLE
            btnSubmitHome.visibility = View.VISIBLE
        }
    }
}