package com.example.mytetris.blocks

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.mytetris.Constants
import com.example.mytetris.TetrisBlock

class Iblock(image: Bitmap) : TetrisBlock(image) {
    var resizedImage = Bitmap.createScaledBitmap(image,Constants.WIDTH_BLOCK,Constants.HEIGHT_BLOCK*4,false)
    fun draw(canvas: Canvas){
     canvas.drawBitmap(resizedImage,x.toFloat(),y.toFloat(),null)
 }



}