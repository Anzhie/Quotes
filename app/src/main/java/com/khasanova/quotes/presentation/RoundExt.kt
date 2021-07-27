package com.khasanova.quotes.presentation

import kotlin.math.round

fun Double.roundToTwoDigits() = round(this * 100) / 100