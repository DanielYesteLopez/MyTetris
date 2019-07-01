package com.example.mytetris.blocks

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.example.mytetris.Constants
import com.example.mytetris.TetrisBlock

class MonoBlock(image: Bitmap) : TetrisBlock(image) {
    private var resizedImage= Bitmap.createScaledBitmap(image, Constants.HEIGHT_BLOCK, Constants.WIDTH_BLOCK,false)
    fun draw(canvas: Canvas,x:Int,y:Int){
        var reX = x*Constants.WIDTH_BLOCK
        var reY = y*Constants.HEIGHT_BLOCK
        canvas.drawBitmap(resizedImage,reX.toFloat(),reY.toFloat(),null)
    }
}