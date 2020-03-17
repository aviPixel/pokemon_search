package com.finnomena.project.core.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.SystemClock
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.finnomena.project.candidate.R

fun Any.toast(context: Context?, duration: Int) {
    if (context == null) {
        return
    }
    Toast.makeText(context, this.toString(), duration).show()
}

fun Context.convertDpToPx(dp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics).toInt()
}

fun Context.convertPxToDp(px: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, this.resources.displayMetrics).toInt()
}

//if (1000.checkClickLastTime()) return
private var mClickLastTime: Long = 0
fun Int.checkClickLastTime(): Boolean {
    return if (SystemClock.elapsedRealtime() - mClickLastTime > this) {
        mClickLastTime = SystemClock.elapsedRealtime()
        false
    } else {
        true
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun Activity.setStatusBarWhite() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//23
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }
}

fun Activity.hideSoftInputKeyboard() {
    try {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}