package com.example.upharma.employee.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.upharma.MainActivity
import com.example.upharma.common.Status
import com.example.upharma.di.Injectable
import com.example.upharma.employee.viewmodel.Invoice.InvoiceProductLstViewModel
import com.example.upharma.model.entity.Product
import com.example.upharma.utilities.goneView
import com.example.upharma.utilities.logout
import com.example.upharma.utilities.showToast
import com.example.upharma.utilities.visibilityView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_product.*
import javax.inject.Inject
import android.R.id.text2
import android.R.id.text1
import android.text.Editable
import android.text.TextWatcher
import com.example.upharma.R


class DetailProductFragment : Fragment(), Injectable {

    private lateinit var product1 : Product

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: InvoiceProductLstViewModel
    private var productId=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productId=DetailProductFragmentArgs.fromBundle(arguments!!).productId
        return inflater.inflate(R.layout.fragment_detail_product, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViewModel()
        subscribeUi()

        btnAdd.setOnClickListener {
            product1.quantity+=1
            edtQuntity.setText(product1.quantity.toString())

        }

        btnSub.setOnClickListener {
            if(product1.quantity > 0){
                product1.quantity+=-1
                edtQuntity.setText(product1.quantity.toString())
            }

            else
                return@setOnClickListener
        }

        endLine.setOnClickListener {
            activity!!.onBackPressed()
        }

        edtQuntity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val a = edtQuntity.text.toString()
                product1.quantity = Integer.parseInt(a)
            }
        })


    }

//    private fun changeEdt(){
//
//    }


    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(InvoiceProductLstViewModel::class.java)
      //  viewModel.setParameter(productId)

    }

    private fun subscribeUi() {
        with(viewModel){

            products.observe(viewLifecycleOwner, Observer { resource ->
                if (resource?.data != null) {
                    checkStatus(resource.status,null)
                    val products = resource.data
                    for (product in products) {
                        if(product.productId.equals(productId)){
                            product1=product
                            showDetailProduct(product)
                        }
                    }

                }
            })
        }
    }

    private fun showDetailProduct(product: Product) {
        txtNameProduct.text=product.productName
        txtUnitPrice.text=product.unitPrice.toString()
        edtQuntity.setText(product.quantity.toString())

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
        goneView(lineDetailProduct)
    }

    fun endProcess(){
        goneView(progresMain)
        visibilityView(lineDetailProduct)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
//        (activity as AppCompatActivity).getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
//        (activity as AppCompatActivity).getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
    }



    override fun onResume() {
        super.onResume()
        if (activity is MainActivity){
            activity!!.navigation.visibility = View.GONE
        }
    }


}
