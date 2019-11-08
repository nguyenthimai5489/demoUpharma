package com.example.upharma.employee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.upharma.R
import com.example.upharma.model.entity.Promotion
import kotlinx.android.synthetic.main.list_iteam_promotional_code.view.*

class PromotionalCodeAdapter(
    var promotionalCodes: List<Promotion> = ArrayList()
) : RecyclerView.Adapter<PromotionalCodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_iteam_promotional_code, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promotionalCode = promotionalCodes[position]
        holder.bindItems(promotionalCode)
    }

    override fun getItemCount(): Int {
        return promotionalCodes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(promotionalCode: Promotion) {

            itemView.txtNamePromotion.text = promotionalCode.name
        }
    }

}