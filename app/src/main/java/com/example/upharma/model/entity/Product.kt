package com.example.upharma.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "product")
class Product (
    @PrimaryKey
    @ColumnInfo(name = "ProductId") @SerializedName("ProductId")
    var productId : String="",
    @ColumnInfo(name = "ProductName") @SerializedName("ProductName")
    var productName : String="",
    @ColumnInfo(name = "UnitOfMeasure") @SerializedName("UnitOfMeasure")
    var unitOfMeasure : String="",
    @ColumnInfo(name = "Quantity") @SerializedName("Quantity")
    var quantity : Int=0,
    @ColumnInfo(name = "UnitPrice") @SerializedName("UnitPrice")
    var unitPrice : Double=0.0,
    @ColumnInfo(name = "VAT") @SerializedName("VAT")
    var vAT : Double=0.0,
    @ColumnInfo(name = "Discount") @SerializedName("Discount")
    var discount :  Double=0.0,
    @ColumnInfo(name = "AmountDiscount") @SerializedName("AmountDiscount")
    var amountDiscount : Double=0.0,
    @ColumnInfo(name = "AmountIncludingVAT") @SerializedName("AmountIncludingVAT")
    var amountIncludingVAT : Double=0.0,
    @ColumnInfo(name = "Amount") @SerializedName("Amount")
    var amount :  Double=0.0,
    @ColumnInfo(name = "Gift") @SerializedName("Gift")
    var gift : String=""
)