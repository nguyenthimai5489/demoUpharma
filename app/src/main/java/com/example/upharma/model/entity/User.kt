package com.example.upharma.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user")
class User (
    @PrimaryKey
    @ColumnInfo(name = "UserName") @SerializedName("UserName")
    var userName: String = "",
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
    @ColumnInfo(name = "Center") @SerializedName("Center")
    var center: String? = "",
    @ColumnInfo(name = "EmployeeType") @SerializedName("EmployeeType")
    var employeeType: String? = "",
    @ColumnInfo(name = "EmployeeCode") @SerializedName("EmployeeCode")
    var employeeCode: String? = "",
    @ColumnInfo(name = "Birthday") @SerializedName("Birthday")
    var birthday: String? = "",
    @ColumnInfo(name = "PeopleId") @SerializedName("PeopleId")
    var peopleId: String? = ""
)

