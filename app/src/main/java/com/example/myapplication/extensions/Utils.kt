package com.example.football2.extensions

import android.view.View

//Fungsi visible() digunakan untuk menampilkan ProgressBa
fun View.visible() {
    visibility = View.VISIBLE
}

//menyembunyikannya.
fun View.invisible() {
    visibility = View.INVISIBLE
}