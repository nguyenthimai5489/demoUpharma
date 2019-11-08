package com.example.upharma.employee.adapter.Product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.upharma.R
import com.example.upharma.employee.fragment.OrderFragmentDirections
import com.example.upharma.model.entity.Product
import kotlinx.android.synthetic.main.list_iteam_product.view.*


class ProductAdapter(
    var iProductAdapterCallBack: IProductAdapterCallBack,
    var products: List<Product> = ArrayList()
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_iteam_product, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bindItems(product)

        holder.itemView.btnAddProduct.setOnClickListener {
//            holder.itemView.btnAddProduct.visibility = View.GONE
//            holder.itemView.lineChoseQuantity.visibility = View.VISIBLE

        }

        holder.itemView.btnSub.setOnClickListener {
            iProductAdapterCallBack.sub(product)

//            if(product.quantity==0){
//                holder.itemView.btnAddProduct.visibility = View.VISIBLE
//                holder.itemView.lineChoseQuantity.visibility = View.GONE
//            }
        }

        holder.itemView.btnAdd.setOnClickListener {

            iProductAdapterCallBack.add(product)


        }

        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).
                navigate(OrderFragmentDirections.actionOrderFragmentToDetailProductFragment(product.productId ))
        }

        holder.itemView.btnNote.setOnClickListener {
            iProductAdapterCallBack.note(product)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: Product) {

            itemView.txtNameProduct.text = product.productName
            itemView.txtUnitPrice.text = product.unitPrice.toString().plus("/").plus(product.unitOfMeasure)
            itemView.txtEachQuantity.text = product.quantity.toString()
        }
    }
}

