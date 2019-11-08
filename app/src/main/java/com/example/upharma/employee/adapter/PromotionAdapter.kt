package com.example.upharma.employee.adapter

import android.graphics.Color

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.upharma.R

import com.example.upharma.model.entity.Promotion
import kotlinx.android.synthetic.main.list_iteam_promotion.view.*


class PromotionAdapter(
    var calls: List<Promotion> = ArrayList()
    ) : RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_iteam_promotion, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val call = calls[position]
        holder.bindItems(call)
    }

    override fun getItemCount(): Int {
        return calls.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(call: Promotion) {

            itemView.txtPromotion.text = call.name


        }
    }

}