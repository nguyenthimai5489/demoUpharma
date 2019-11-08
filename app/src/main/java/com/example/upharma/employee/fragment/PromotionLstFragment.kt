package com.example.upharma.employee.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.upharma.R
import com.example.upharma.SnapHelper.StartSnapHelper
import com.example.upharma.employee.adapter.PromotionalCodeAdapter
import com.example.upharma.model.entity.Promotion
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.fragment_promotion_lst.*

class PromotionLstFragment : Fragment() {

    var promotions : MutableList<Promotion> = ArrayList()
    private lateinit var adapterPromotion : PromotionalCodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promotion_lst, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val promotion = Promotion("Giảm giá 10%")
        promotions.add(promotion)
        promotions.add(promotion)
        promotions.add(promotion)

        adapterPromotion = PromotionalCodeAdapter(promotions)
        recyclerPromotionLst.setHasFixedSize(true)
        val layoutManager1 = LinearLayoutManager(activity, VERTICAL, false)
        recyclerPromotionLst.layoutManager = layoutManager1
        recyclerPromotionLst.adapter = adapterPromotion

        val startSnapHelper2 = StartSnapHelper()
        startSnapHelper2.attachToRecyclerView(recyclerProductLst)
    }


}
