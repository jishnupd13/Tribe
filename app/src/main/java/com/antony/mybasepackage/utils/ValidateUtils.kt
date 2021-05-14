package com.antony.mybasepackage.utils

import java.util.regex.Pattern

/**
 * Created By antony on 6/6/2019.
 */
fun isEmailValid(email: String): Boolean {
    val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)

    return matcher.matches()
}

fun isPhoneNumberValid(phone: String): Boolean {
    return if (!Pattern.matches("[a-zA-Z]+", phone)) {
        !(phone.length < 6 || phone.length > 13)
    } else {
        false
    }
}