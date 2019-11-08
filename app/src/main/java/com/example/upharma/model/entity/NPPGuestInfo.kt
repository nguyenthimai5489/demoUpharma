package com.example.upharma.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "nppGuest")
class NPPGuestInfo (
    @PrimaryKey
    @ColumnInfo(name = "FullName") @SerializedName("FullName")
    var fullName: String? = "",
    @ColumnInfo(name = "Address") @SerializedName("Address")
    var address: String? = "",
    @ColumnInfo(name = "Email") @SerializedName("Email")
    var email: String? = "",
    @ColumnInfo(name = "Phone") @SerializedName("Phone")
    var phone: String? = "",
    @ColumnInfo(name = "Sex") @SerializedName("Sex")
    var sex: String? = "",
    @ColumnInfo(name = "Birthday") @SerializedName("Birthday")
    var birthday: String? = ""

)
