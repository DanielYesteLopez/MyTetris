package com.example.mytetris

import android.content.res.Resources
import android.graphics.Bitmap
import android.util.Log
import com.example.mytetris.blocks.*
import java.util.*

open class TetrisBlock(var image: Bitmap) {
    val speed = 10
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0

    init {
        w = image.width
        h = image.height

        x = Constants.WIDTH_BLOCK*(Constants.WIDTH/2)
        y = 0
    }

    fun updateMovement(newTouchX:Int,newTouchY: Int){
        if(newTouchX> Constants.WIDTH_BLOCK*(Constants.WIDTH/2)){
            x += Constants.WIDTH_BLOCK
            if (x>(Constants.WIDTH_BLOCK*Constants.WIDTH)-1){
                x -= Constants.WIDTH_BLOCK
            }
        }else{
            x-= Constants.WIDTH_BLOCK
            if (x< 0){
                x += Constants.WIDTH_BLOCK
            }
        }

    }

}
