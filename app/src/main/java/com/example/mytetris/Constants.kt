package com.example.mytetris

import android.content.res.Resources.getSystem

class Constants {
    companion object{
        const val WIDTH =40
        const val HEIGHT = 100
        val  HEIGHT_BLOCK = getSystem().displayMetrics.heightPixels/HEIGHT
        val WIDTH_BLOCK = getSystem().displayMetrics.widthPixels/WIDTH

    }
}