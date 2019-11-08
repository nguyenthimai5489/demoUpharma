package com.example.upharma.utilities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.upharma.LoginActivity
import com.example.upharma.R


import java.text.SimpleDateFormat
import java.util.*


fun Fragment.showToast(str: String) {
    Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
}

fun Fragment.showDialog(context: Context,title: String,arr : ArrayList<String>,index: Int)   {
    val pictureDialog = AlertDialog.Builder(context)
    pictureDialog.setTitle(title)
    val pictureDialogItems = arr.toArray(arrayOfNulls<CharSequence>(arr.size))
    pictureDialog.setItems(pictureDialogItems
    ) { dialog, which ->

    }
    pictureDialog.show()
}

fun AppCompatActivity.showToast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
}

fun Fragment.logout() {
    showToast(resources.getString(R.string.error_token_text))
    SharedPreferences.logout(context!!)
    val intent = Intent(context, LoginActivity::class.java)
    intent.putExtra(LoginActivity.LOGOUT,true)
    startActivity(intent)
    activity!!.finish()

}

fun Fragment.visibilityView(view: View) {
    view.visibility = View.VISIBLE
}

fun Fragment.goneView(view: View) {
    view.visibility = View.GONE
}


@SuppressLint("SimpleDateFormat")
fun Date.converDateToStringType1() : String{
    val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
    val strDate = dateFormat.format(time)
    return strDate
}

@SuppressLint("SimpleDateFormat")
fun Date.converDateToStringDDMMYYYY() : String{
    val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN_DDMMYYYY)
    val strDate = dateFormat.format(time)
    return strDate
}

@SuppressLint("SimpleDateFormat")
fun String.convertStringToDateDDMMYYYY() : Date {
    val sdf = SimpleDateFormat(DATE_FORMAT_PATTERN_DDMMYYYY)
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.parse(this)
    // Toast.makeText(this@NewCallActivity,date.time.toString(), Toast.LENGTH_LONG).show()
}

fun String.firstChar(): String {
    if (this.length > 0){
        return this[0].toString().toUpperCase()
    }
    return ""
}
