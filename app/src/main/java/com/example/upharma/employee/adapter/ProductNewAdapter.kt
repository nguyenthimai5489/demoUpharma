package com.example.upharma.employee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.upharma.R
import com.example.upharma.model.entity.Product
import kotlinx.android.synthetic.main.list_iteam_product_new.view.*


class ProductNewAdapter(
    var products: List<Product> = ArrayList()
) : RecyclerView.Adapter<ProductNewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_iteam_product_new, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bindItems(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: Product) {

        }
    }

}