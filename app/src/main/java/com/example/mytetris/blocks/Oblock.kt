package com.example.mytetris.blocks

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.example.mytetris.Constants
import com.example.mytetris.TetrisBlock

class Oblock (image: Bitmap) : TetrisBlock(image) {
    var resizedImage = Bitmap.createScaledBitmap(image,
        Constants.WIDTH_BLOCK*2,
        Constants.HEIGHT_BLOCK*2,false)
    fun draw(canvas: Canvas) {
        canvas.drawBitmap(resizedImage,x.toFloat(),y.toFloat(),null)
    }

}