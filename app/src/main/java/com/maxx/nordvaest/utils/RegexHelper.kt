package com.maxx.nordvaest.utils

import java.util.regex.Pattern

class RegexHelper {

    companion object {

        public fun matchRegex(input: String, regex: String): Boolean {

            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(input)
            return matcher.find()
        }


        const val ACCOUNT_NUMBER_AU = "^\\d{6}-\\d{6,10}$"
        const val ACCOUNT_NUMBER_NZ = "^\\d{2}-\\d{4}-\\d{7}-\\d{2,3}$"
        const val ACCOUNT_NUMBER_UK = "^\\d{6}-\\d{8,10}$"
        const val ACCOUNT_NUMBER_US = "^\\d{9}-\\d{8,17}$"


        fun matchCountryAccountNumberRegex(countryCode: String, input: String): Boolean {
            if (countryCode.equals("au"))
                return matchRegex(input, ACCOUNT_NUMBER_AU)
            else if (countryCode.equals("nz"))
                return matchRegex(input, ACCOUNT_NUMBER_NZ)
            else if (countryCode.equals("gb"))
                return matchRegex(input, ACCOUNT_NUMBER_UK)
            else if (countryCode.equals("us"))
                return matchRegex(input, ACCOUNT_NUMBER_US)
            else
                return true
        }

        fun getAccountNumberErrorMessageByCountry(countryCode: String): String {
            return if (countryCode.equals("au"))
                "BSB Code: 6 digits\nAccount No: 6-10 digits."
            else if (countryCode.equals("nz"))
                "Bank: 2 digits\nBranch: 4 digits\nAccount No: 7 digits\nSuffix: 2-3 digits."
            else if (countryCode.equals("gb"))
                "Sort Code: 6 digits\nAccount No: 8-10 digits."
            else if (countryCode.equals("us"))
                "Routing Number: 9 digits\nAccount No: 8-17 digits."
            else
                ""
        }
    }
}