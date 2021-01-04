package com.chg.adapter.extension

import android.content.Context
import android.widget.Toast

/**
 * 封装长时间显示toast
 */
fun Context.longToast(text:String) {
    Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}

/**
 * 封装短时间显示toast
 */
fun Context.shortToast(text:String) {
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

