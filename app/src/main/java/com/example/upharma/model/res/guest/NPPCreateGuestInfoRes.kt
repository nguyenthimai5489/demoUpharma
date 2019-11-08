package com.example.upharma.model.res.guest

import com.example.upharma.model.UpharmaCap
import com.google.gson.annotations.SerializedName

class NPPCreateGuestInfoRes(
    @SerializedName("RespText")
    override val respText: String? = "",
    @SerializedName("RespCode")
    override val respCode: Int? = 0
): UpharmaCap()