package com.example.upharma.model.res.Invoice

import com.example.upharma.model.UpharmaCap
import com.example.upharma.model.entity.Product
import com.google.gson.annotations.SerializedName

class STGetProductByUIdRes(
    @SerializedName("RespText")
    override val respText: String? = "",
    @SerializedName("RespCode")
    override val respCode: Int? = 0,
    @SerializedName("ProductLst")
    val productLst : List<Product>?

): UpharmaCap()