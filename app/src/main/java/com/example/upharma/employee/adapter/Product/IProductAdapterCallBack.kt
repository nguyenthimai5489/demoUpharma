package com.example.upharma.employee.adapter.Product

import com.example.upharma.model.entity.Product

interface IProductAdapterCallBack {

     fun note(product:Product)
     fun add(product: Product)
     fun sub(product: Product)

}