package com.example.upharma.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "guest")
class GuestInfo (
    @PrimaryKey
    @ColumnInfo(name = "UserName") @SerializedName("UserName")
    var userName: String = "",
    @ColumnInfo(name = "Password") @SerializedName("Password")
    var password: String? = "",
    @ColumnInfo(name = "FullName") @SerializedName("FullName")
    var fullName: String? = "",
    @ColumnInfo(name = "PhoneNumber") @SerializedName("PhoneNumber")
    var phoneNumber: String? = "",
    @ColumnInfo(name = "Email") @SerializedName("Email")
    var email: String? = "",
    @ColumnInfo(name = "Sex") @SerializedName("Sex")
    var sex: String? = "",
    @ColumnInfo(name = "Address") @SerializedName("Address")
    var address: String? = "",
    @ColumnInfo(name = "Birthday") @SerializedName("Birthday")
    var birthday: String? = ""
)

