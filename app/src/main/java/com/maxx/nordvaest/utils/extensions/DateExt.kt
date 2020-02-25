package com.maxx.nordvaest.utils.extensions

import android.util.Log.d
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by rakezb on 3/7/2019.
 */
/**
 * get todays date in yyyy/MM/dd format to get txn history
 */
fun getDateAndTime() : String?{
    return getTodaysDate() + " (" + getTime() + ") "
}


fun getTodaysDate(): String? {
    val cal = Calendar.getInstance()
    return getFormattedDate(cal.time)
}

/**
 * format date to yyyy/MM/dd
 */
fun getFormattedDate(date: Date?): String? {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    return dateFormat.format(date)
}

fun getTime() : String?{
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
}

fun getCurrentUTCDate(): Long {
    return System.currentTimeMillis()
}

fun getDayFromDateString(dateString: String?): Int {
    try {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        var date = dateFormat.parse(dateString)
        dateFormat = SimpleDateFormat("dd")
        return dateFormat.format(date).toInt()
    } catch (e: Exception) {
        return getCurrentDay()
    }
}

fun getMonthFromDateString(dateString: String?): Int {
    try {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        var date = dateFormat.parse(dateString)
        dateFormat = SimpleDateFormat("MM")
        return dateFormat.format(date).toInt() - 1
    } catch (e: Exception) {
        return getCurrentMonth()
    }
}

fun getYearFromDateString(dateString: String?): Int {
    d("tag", "okhttp date $dateString")
    try {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        var date = dateFormat.parse(dateString)
        dateFormat = SimpleDateFormat("yyyy")
        d("tag", "okhttp date $date")
        return dateFormat.format(date).toInt()
    } catch (e: Exception) {
        d("tag", "okhttp date catch ${getCurrentYear()}")
        return getCurrentYear()
    }
}

fun getCurrentDay(): Int {
    var dateFormat = SimpleDateFormat("dd")
    return dateFormat.format(getCurrentUTCDate()).toInt()
}

fun getCurrentMonth(): Int {
    var dateFormat = SimpleDateFormat("MM")
    return dateFormat.format(getCurrentUTCDate()).toInt() - 1
}

fun getCurrentYear(): Int {
    var dateFormat = SimpleDateFormat("yyyy")
    return dateFormat.format(getCurrentUTCDate()).toInt()
}

fun getCurrentHour(): Int {
    var dateFormat = SimpleDateFormat("HH")
    return dateFormat.format(getCurrentUTCDate()).toInt()
}

fun getCurrentMinute(): Int {
    var dateFormat = SimpleDateFormat("mm")
    return dateFormat.format(getCurrentUTCDate()).toInt()
}

fun getCurrentMonthString(): String {
    var dateFormat = SimpleDateFormat("MMMM")
    return dateFormat.format(getCurrentUTCDate())
}

//Format yyyy-MM-dd
fun getDateString(year: Int, month: Int, day: Int): String {
    return "${day.toDoubleDigitFormat()}-${(month.plus(1)).toDoubleDigitFormat()}-$year"
}

fun getTimeString(hour: Int, minute: Int): String {
    return "${hour.toDoubleDigitFormat()}:${minute.toDoubleDigitFormat()}"
}



fun Int.toDoubleDigitFormat(): String {
    var decimalFormat = DecimalFormat("00")
    return decimalFormat.format(this)
}

/**
 * Date format is supplies as :ddMMyyyyHHmmss
 * Returend format dd/mm/yyyy
 */
fun getDateFromConcatedString(dateString : String): String{
    return dateString.substring(0, 2)+"/"+dateString.substring(2, 4)+"/"+dateString.substring(4, 8)
}

fun getTimeFromConcatedString(dateString: String): String{
    return dateString.substring(8, 10)+":"+dateString.substring(10, 12)+":"+dateString.substring(12, 14)
}

fun getConcatedDateFromDateTime(date: String, time: String): String{
    return date.replace("-","")+time.replace(":","")+"00"
}

/**
 * 2019-05-02T11:26:26.87
 * yyyy-MM-dd'T'HH:mm:ss.SS
 * yyyy-MM-dd'T'HH:mm:ss.SSSZ
 */
fun getDateForHistory(date: String?): String?{
    try{
        var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS")
        var date = dateFormat.parse(date)
        dateFormat = SimpleDateFormat("MMMM dd")
        return dateFormat.format(date)
    }catch (ex: Exception){
        return ""
    }

}

fun getTimeForHistory(date: String?): String?{
    try {
        var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS")
        var date = dateFormat.parse(date)
        dateFormat = SimpleDateFormat("h:mm a")
        return dateFormat.format(date)
    }catch (ex: Exception){
        return ""
    }
}

fun getYearList(): List<String> {
    val yearList: MutableList<String> = ArrayList()
    var yearToAdd = getCurrentYear()
    for(i in 1..10){
        yearList.add(yearToAdd.toString())
        yearToAdd--
    }
    return yearList
}

fun getMonthsList(): List<String> {
    return listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
}