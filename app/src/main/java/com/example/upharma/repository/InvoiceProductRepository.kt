package com.example.upharma.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.upharma.common.ApiResponse
import com.example.upharma.common.AppExecutors
import com.example.upharma.common.NetworkBoundResource
import com.example.upharma.common.Resource
import com.example.upharma.model.entity.Product
import com.example.upharma.model.res.Invoice.STGetProductByUIdRes
import com.example.upharma.utilities.runOnIoThread
import com.example.upharma.webservice.WebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoiceProductRepository @Inject constructor(

    private val webService: WebService,
    private val appExecutors: AppExecutors
){
    private var productLstByUIdLiveData= MediatorLiveData<List<Product>>()

    init {
        productLstByUIdLiveData.value= ArrayList<Product>()
    }

    fun getProductByUId(userName: String, token: String,
                        placeId: String="0101", saleType : String="AT", shouldFetch: Boolean = true,
                        callDelay: Long = 0) : LiveData<Resource<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, STGetProductByUIdRes>(appExecutors) {

            override fun saveCallResult(item: STGetProductByUIdRes) {
                val products = item.productLst
//                if (invoices != null){
//                    insertAllToRoom(invoices)
//                }
                runOnIoThread {
                    productLstByUIdLiveData.postValue(products)
                }

            }

            override fun shouldFetch(data: List<Product>?): Boolean {
                return data == null || shouldFetch
            }

            override fun fetchDelayMillis(): Long {
                return callDelay
            }

            override fun loadFromDb(): LiveData<List<Product>> {
//                return invoiceDAO.getInvoiceByUId(userName)
                return productLstByUIdLiveData
            }

            override fun createCall(): LiveData<ApiResponse<STGetProductByUIdRes>> =
                webService.getProductByUId(userName,token,placeId,saleType)

        }.asLiveData()

    }
}