package com.example.upharma.employee.viewmodel.Invoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.upharma.common.Resource
import com.example.upharma.model.entity.InvoiceLine
import com.example.upharma.model.entity.Product
import com.example.upharma.repository.InvoiceProductRepository
import com.example.upharma.utilities.AbsentLiveData
import com.example.upharma.utilities.InjectorUtils
import javax.inject.Inject

class InvoiceProductLstViewModel @Inject constructor(

    private val invoiceProductRepository: InvoiceProductRepository
) : ViewModel() {

    val invoiceLines = MutableLiveData<List<InvoiceLine>>()

    private val _type = MutableLiveData<Int>()
    val type: LiveData<Int>
        get() = _type

    fun setType(type: Int){
        _type.value = type
    }

    private val _parameter: MutableLiveData<Parameter> = MutableLiveData()
    val parameter: LiveData<Parameter>
        get() = _parameter

    init {
        _type.value = 2
    }

    val products: LiveData<Resource<List<Product>>> = Transformations
        .switchMap(_parameter) { input ->
            input.ifExists { uId ->
                invoiceProductRepository.getProductByUId(InjectorUtils.USER_CODE, InjectorUtils.TOKEN)
            }
        }

    fun retry() {
        val uId = parameter.value?.uId
        if (uId != null) {
            _parameter.value = Parameter(uId)
        }
    }

    fun setParameter(uId: String) {
        val update = Parameter(uId)
        if (_parameter.value == update) {
            return
        }
        _parameter.value = update
    }

    data class Parameter(val uId: String) {
        fun <T> ifExists(f: (String) -> LiveData<T>): LiveData<T> {
            return if (uId.equals("")) {
                AbsentLiveData.create()
            } else {
                f(uId)
            }
        }
    }


}
