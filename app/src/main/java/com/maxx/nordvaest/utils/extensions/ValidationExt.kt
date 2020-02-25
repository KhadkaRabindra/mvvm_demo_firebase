package com.maxx.nordvaest.utils.extensions

import android.text.TextUtils


/**
 * Created by anup on 05/09/2018
 */

fun validateBonusLinkId(bonusLinkId: String?): Boolean? {
    val reversedBonusLinkId = StringBuilder(bonusLinkId).reverse().toString()
    var identifyingFactor = 0
    for (i in 0 until reversedBonusLinkId.length) {
        if ((i + 1) % 2 == 0) {
            var doubledValue = convertCharToInt(reversedBonusLinkId[i]) * 2
            if (doubledValue.toString().length > 1) {
                val tempDoubleValue = doubledValue.toString()
                doubledValue = convertCharToInt(tempDoubleValue[0]) + convertCharToInt(tempDoubleValue[1])
            }
            identifyingFactor += doubledValue
        } else
            identifyingFactor += convertCharToInt(reversedBonusLinkId[i])
    }
    return identifyingFactor % 10 == 0
}

fun convertCharToInt(character: Char?): Int {
    return Integer.parseInt(character.toString())
}

fun validateOptionalEmail(email: String?): Boolean? {
    return TextUtils.isEmpty(email) || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}