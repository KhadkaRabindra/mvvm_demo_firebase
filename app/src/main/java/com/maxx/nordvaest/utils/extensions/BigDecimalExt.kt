package com.maxx.nordvaest.utils.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

fun BigDecimal?.formatToString(): String? {
    if (this == null)
        return ""
    val format = DecimalFormat("##.##")
    return format.format(this)
}

fun BigDecimal?.formatToTwoDecimal(): BigDecimal? {
    if (this == null)
        return null
    val format = DecimalFormat("##.##")
    format.isParseBigDecimal = true
    return format.parse(format.format(this)) as BigDecimal
}