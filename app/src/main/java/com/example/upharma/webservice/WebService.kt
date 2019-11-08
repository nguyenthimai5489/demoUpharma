package com.example.upharma.webservice


import androidx.lifecycle.LiveData
import com.example.upharma.common.ApiResponse
import com.example.upharma.model.entity.GuestInfo
import com.example.upharma.model.res.Invoice.STGetProductByUIdRes
import com.example.upharma.model.res.guest.NPPGuestLoginRes
import com.example.upharma.model.res.user.STUserLoginRes
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    //user
    @FormUrlEncoded
    @POST("Guest/GuestLogin")
    fun login(@Field("UserName") UserName: String,
              @Field("Password") Password: String): LiveData<ApiResponse<STUserLoginRes>>

    @FormUrlEncoded
    @POST("Invoice/GetProductByUId")
    fun getProductByUId(@Field("UserName") UserName:String,
                        @Field("Token") Token:String,
                        @Field("PlaceId") PlaceId:String,
                        @Field("SaleType") SaleType:String): LiveData<ApiResponse<STGetProductByUIdRes>>

    //guest

    @Multipart
    @POST("NPPGuestInfo/CreateGuestInfo")
    fun createGuestInfo(@Query("UserName") UserName: String,
                   @Query("Password") Password: String,
                   @Query ("Address") Address: String): Call<GuestInfo>

}