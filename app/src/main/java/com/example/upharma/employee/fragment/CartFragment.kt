package com.example.upharma.employee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upharma.MainActivity
import com.example.upharma.R
import com.example.upharma.common.Status
import com.example.upharma.di.Injectable
import com.example.upharma.employee.adapter.Product.InvoiceProductAdapter
import com.example.upharma.employee.viewmodel.Invoice.InvoiceProductLstViewModel
import com.example.upharma.model.entity.InvoiceLine
import com.example.upharma.model.entity.Product
import com.example.upharma.utilities.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject


class CartFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: InvoiceProductAdapter
    private lateinit var viewModel: InvoiceProductLstViewModel

    private var mInvoiceLines : List<InvoiceLine> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViewModel()
        subscribeUi()

        toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }

        txtAddProduct.setOnClickListener {
            activity!!.onBackPressed()
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(InvoiceProductLstViewModel::class.java)
        adapter = InvoiceProductAdapter()
        recyclerInvoiceLine.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerInvoiceLine.layoutManager = layoutManager
        recyclerInvoiceLine.adapter = adapter

    }

    private fun subscribeUi() {
        with(viewModel){


            products.observe(viewLifecycleOwner, Observer {resource ->

//                ( mInvoiceLines as ArrayList ).clear()
                    if (resource != null) {
                        checkStatus(resource.status,null)
                        val mProducts = resource.data
                        if(mProducts != null){
                            for (mProduct in mProducts){
                                val mInvoiceLine = InvoiceLine()
                                if(mProduct.quantity != 0 ){
                                    mInvoiceLine.productId = mProduct.productId
                                    mInvoiceLine.productName = mProduct.productName
                                    mInvoiceLine.unitOfMeasure = mProduct.unitOfMeasure
                                    mInvoiceLine.quantity = mProduct.quantity
                                    mInvoiceLine.unitPrice = mProduct.unitPrice
                                    mInvoiceLine.vAT = mProduct.vAT
                                    mInvoiceLine.discount = mProduct.discount
                                    mInvoiceLine.amountDiscount = mProduct.amountDiscount
                                    mInvoiceLine.amountIncludingVAT = mProduct.amountIncludingVAT
                                    mInvoiceLine.amount = mProduct.amount
                                    mInvoiceLine.gift = mProduct.gift

                                    ( mInvoiceLines as ArrayList).add(mInvoiceLine)

                                }
                            }
                            adapter.invoiceLines = mInvoiceLines
                            adapter.notifyDataSetChanged()
                    }
                }
            })

            invoiceLines.observe(viewLifecycleOwner, Observer {

                if(it != null ){

                }
            })
        }
    }


    private fun checkStatus(status: Status, messageSuccess: String? ) {
        when (status){
            Status.LOADING ->{
                startProcess()
            }
            Status.SUCCESS_NETWORK ->{
                endProcess()
                messageSuccess?.let {
                    showToast(messageSuccess)
                }
            }

            Status.SUCCESS_DB ->{
                endProcess()
            }
            Status.ERROR ->{
                endProcess()
                showToast(resources.getString(R.string.error_network_text))
            }
            Status.ERROR_TOKEN ->{
                endProcess()
                logout()
            }
            Status.ERROR_DB ->{
                endProcess()
                showToast(resources.getString(R.string.error_db_text))
            }
        }
    }

    fun startProcess(){
        visibilityView(progresMain)
        goneView(lineOrderMain)
    }

    fun endProcess(){
        goneView(progresMain)
        visibilityView(lineOrderMain)
    }



    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity){
            activity!!.navigation.visibility = View.GONE
        }
    }
}
