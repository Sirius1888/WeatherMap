package com.example.weatherapp

import android.content.Context
import android.widget.Toast


fun Int.toSom(c: Int = 1): String {
    return "${this * c} сом"
}

fun Context.stringFromResources(id: Int): String {
    return getString(id)
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}




