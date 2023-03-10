package com.feritkeskin.fixerappclone.model

data class FixerModel(
    val base: String,
    val end_date: String,
    val fluctuation: Boolean,
    val rates: Rates,
    val start_date: String,
    val success: Boolean
)