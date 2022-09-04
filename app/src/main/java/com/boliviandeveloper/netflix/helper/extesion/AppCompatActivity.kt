package com.boliviandeveloper.netflix.helper.extesion

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.boliviandeveloper.netflix.R
import com.boliviandeveloper.netflix.helper.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.push(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.anim_fragment_slide_left_enter,
            R.anim.anim_fragment_slide_left_exit,
            R.anim.anim_fragment_slide_right_enter,
            R.anim.anim_fragment_slide_right_exit
        )
        .replace(R.id.frameLayout, fragment, Constants.FRAGMENT_DETAIL)
        .addToBackStack(null)
        .commitAllowingStateLoss()
}

fun AppCompatActivity.back() {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStack()
    } else {
        finish()
    }
}

fun AppCompatActivity.hideMenuBottom() {
    findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.GONE
}

fun AppCompatActivity.showMenuBottom() {
    findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
}


