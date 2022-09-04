package com.boliviandeveloper.netflix.helper.extesion

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.push(fragment: Fragment) {
    (activity as AppCompatActivity).push(fragment)
}

fun Fragment.back() {
    (activity as AppCompatActivity).back()
}

fun Fragment.hideMenuBottom() {
    (activity as AppCompatActivity).hideMenuBottom()
}

fun Fragment.showMenuBottom() {
    (activity as AppCompatActivity).showMenuBottom()
}
