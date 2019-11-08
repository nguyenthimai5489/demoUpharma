package com.example.upharma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upharma.employee.adapter.ProductNewAdapter
import com.example.upharma.employee.adapter.PromotionAdapter
import com.example.upharma.model.entity.Product
import com.example.upharma.model.entity.Promotion
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.upharma.SnapHelper.StartSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.upharma.utilities.showToast


class HomeFragment : Fragment() {

    var promotions : MutableList<Promotion> = ArrayList()
    var productNews : MutableList<Product> = ArrayList()
    private lateinit var adapter : PromotionAdapter
    private lateinit var adapterProduct : ProductNewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val promotion = Promotion("Giảm giá 20%")
        promotions.add(promotion)
        promotions.add(promotion)
        promotions.add(promotion)
        promotions.add(promotion)


        adapter = PromotionAdapter(promotions)
        recyclerViewPromotion.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPromotion.layoutManager = layoutManager
        recyclerViewPromotion.adapter = adapter

        adapterProduct = ProductNewAdapter(productNews)
        recyclerProductNew.setHasFixedSize(true)
        val layoutManager1 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerProductNew.layoutManager = layoutManager1
        recyclerProductNew.adapter = adapterProduct

        val startSnapHelper1 = StartSnapHelper()
        val startSnapHelper2 = StartSnapHelper()

        startSnapHelper1.attachToRecyclerView(recyclerViewPromotion)
        startSnapHelper2.attachToRecyclerView(recyclerProductNew)

        btnOder.setOnClickListener {

            Navigation.findNavController(it).
                navigate(HomeFragmentDirections.actionHomeFragmentToOrderFragment(placeId = "0101"))

        }

    }
}
