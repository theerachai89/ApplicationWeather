package com.android.weather.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toTime() :String{
    if (this.isBlank() || this.isEmpty()) return ""
    val formatter = SimpleDateFormat("HH:mm")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date: Date = dateFormat.parse(this) as Date
    return formatter.format(date)
}

fun String.toDateTime() :String{
    if (this.isBlank() || this.isEmpty()) return ""
    val formatter = SimpleDateFormat("dd MMMM YYYY")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date: Date = dateFormat.parse(this) as Date
    return formatter.format(date)
}