package com.example.upharma.employee.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upharma.MainActivity

import com.example.upharma.R
import com.example.upharma.common.Status
import com.example.upharma.di.Injectable
import com.example.upharma.employee.adapter.Product.ProductAdapter
import com.example.upharma.employee.adapter.Product.IProductAdapterCallBack
import com.example.upharma.employee.viewmodel.Invoice.InvoiceProductLstViewModel
import com.example.upharma.model.entity.InvoiceLine
import com.example.upharma.model.entity.Product
import com.example.upharma.utilities.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_note.view.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.list_iteam_product.*
import javax.inject.Inject



class OrderFragment : Fragment(), Injectable,IProductAdapterCallBack {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: InvoiceProductLstViewModel
    private var mProducts : List<Product> = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViewModel()
        subscribeUi()

        btnCart.setOnClickListener {
            Navigation.findNavController(it).
                navigate(OrderFragmentDirections.actionOrderFragmentToCartFragment())
        }

         var sumPrice : Double = 0.0
        for(mProduct in mProducts ){
            if(mProduct.quantity != 0){
                sumPrice  = mProduct.unitPrice * mProduct.quantity + sumPrice
            }

        }

        txtSumPrice.setText(sumPrice.toString())

        toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(InvoiceProductLstViewModel::class.java)
        viewModel.setParameter(InjectorUtils.USER_CODE)
        adapter = ProductAdapter(this)
        recyclerProductLst.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerProductLst.layoutManager = layoutManager
        recyclerProductLst.adapter = adapter
    }

    private fun subscribeUi() {
        with(viewModel){

            products.observe(viewLifecycleOwner, Observer { resource ->
                if (resource != null) {
                    checkStatus(resource.status,null)
                    val products = resource.data
                    if (products != null){
                        adapter.products = products
                        mProducts = products
                        adapter.notifyDataSetChanged()
                    }
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
        goneView(recyclerProductLst)
    }

    fun endProcess(){
        goneView(progresMain)
        visibilityView(recyclerProductLst)
    }

    override fun note(product: Product){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_note, null)
        //AlertDialogBuilder
        val mBuilder = context?.let {
            AlertDialog.Builder(it)
                .setView(mDialogView)
                .setTitle("Thêm ghi chú")
        }

        val  mAlertDialog = mBuilder?.show()

        mDialogView.btnAddNote.setOnClickListener {
            if (mAlertDialog != null) {
                mAlertDialog.dismiss()
            }
            val name = mDialogView.edtNote.text.toString()
        }
    }

    override fun add(product: Product){
        product.quantity++
        adapter.notifyDataSetChanged()
    }

    override fun sub(product: Product) {
        if(product.quantity > 0){
            product.quantity--
            adapter.notifyDataSetChanged()
        }
        else
          return
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

