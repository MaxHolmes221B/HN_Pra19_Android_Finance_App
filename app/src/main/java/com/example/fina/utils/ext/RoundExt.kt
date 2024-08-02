package com.example.fina.utils.ext

fun Double.roundTwoDecimal(): String {
    return when {
        this >= 1_000_000_000 -> "%.2f".format(this / 1_000_000_000.0) + "B"
        this >= 1_000_000 -> "%.2f".format(this / 1_000_000.0) + "M"
        else -> "%.2f".format(this)
    }
}

fun Double.roundOneDecimal(): String {
    return when {
        this >= 1_000_000_000 -> "%.1f".format(this / 1_000_000_000.0) + "B"
        this >= 1_000_000 -> "%.1f".format(this / 1_000_000.0) + "M"
        else -> "%.1f".format(this)
    }
}

fun Int.round(): String {
    return when {
        this >= 1_000_000_000 -> "%.2f".format(this / 1_000_000_000.0) + "B"
        this >= 1_000_000 -> "%.2f".format(this / 1_000_000.0) + "M"
        else -> this.toString()
    }
}