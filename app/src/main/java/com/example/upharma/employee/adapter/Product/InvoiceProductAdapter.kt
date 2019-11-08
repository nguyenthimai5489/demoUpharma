package com.example.upharma.employee.adapter.Product


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.upharma.R
import com.example.upharma.model.entity.InvoiceLine
import com.example.upharma.model.entity.Product
import kotlinx.android.synthetic.main.list_iteam_invoice_product.view.*


class InvoiceProductAdapter(
    var invoiceLines: List<InvoiceLine> = ArrayList(),
    var products : List<Product> = ArrayList()

) :
    RecyclerView.Adapter<InvoiceProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_iteam_invoice_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, positon: Int) {
        val invoiceLine = invoiceLines[positon]
        holder.bindItems(invoiceLine)
    }

    override fun getItemCount(): Int {
        return invoiceLines.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(invoiceLine: InvoiceLine) {
            itemView.txtQuantity.text = invoiceLine.quantity.toString()
            itemView.txtPrice.text = invoiceLine.unitPrice.toString()
            if ( invoiceLine.productName !=null){
                itemView.txtProductName.text= invoiceLine.productName
            }
        }
    }
}