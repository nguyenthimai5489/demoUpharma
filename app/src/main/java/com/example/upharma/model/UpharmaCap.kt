package com.example.upharma.model

import com.google.gson.annotations.SerializedName

abstract class UpharmaCap {
    abstract val respText: String?
    abstract val respCode: Int?
}

data class MyUpharmaCap (
    @SerializedName("RespText")
    override val respText: String? = null,
    @SerializedName("RespCode")
    override val respCode: Int? = null) : UpharmaCap()
