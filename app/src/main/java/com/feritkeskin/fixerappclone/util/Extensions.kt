package com.feritkeskin.fixerappclone.util

fun monthToTwoLength(month: String): String {
    return if (month.length == 1) {
        "0$month"
    } else {
        month
    }
}