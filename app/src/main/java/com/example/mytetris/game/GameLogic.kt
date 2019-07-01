package com.example.mytetris.game

import android.graphics.Canvas
import android.util.Log
import com.example.mytetris.Constants
import com.example.mytetris.TetrisBlock
import com.example.mytetris.blocks.MonoBlock
import kotlin.random.Random


class GameLogic{
    var matrixMap = Array(Constants.WIDTH) {BooleanArray(Constants.HEIGHT)}
    var nextBlockName = 0

    fun initMatrix(){
        matrixMap.all { false }
    }



    fun update(tetrisBlock: TetrisBlock){
        eliminateRow()
        if (!stopMovement(tetrisBlock)) {
            tetrisBlock.y += tetrisBlock.speed
        }else{
            addNewPiece(tetrisBlock.x,tetrisBlock.y)
            nextBlock()
            reset(tetrisBlock)
        }
    }

    private fun stopMovement(tetrisBlock: TetrisBlock): Boolean {
        return (tetrisBlock.y > tetrisBlock.screenHeight.toFloat()-tetrisBlock.image.height) or
                checkColision(tetrisBlock.x,tetrisBlock.y)
    }


    fun nextBlock(){
        nextBlockName = Random.nextInt(1,3)
    }

    fun reset(tetrisBlock: TetrisBlock){
        tetrisBlock.x = Constants.WIDTH_BLOCK*(Constants.WIDTH/2)
        tetrisBlock.y = 0

    }
    fun drawMap(monoBlock: MonoBlock,canvas: Canvas){
        for(i in 0 until Constants.WIDTH ){
            for (j in 0 until Constants.HEIGHT){
                if (matrixMap[i][j])  {
                    monoBlock.draw(canvas,i,j)
                }

            }
        }
    }

    fun addNewPiece(positionX:Int,positionY:Int){
        when(nextBlockName){
            1->addNewIPiece(positionX,positionY)
            2->addNewOPiece(positionX,positionY)
        }

    }

    private fun addNewOPiece(positionX: Int, positionY: Int) {
        val matrixPositionX = positionX / Constants.WIDTH_BLOCK
        val matrixPositionY = positionY / Constants.HEIGHT_BLOCK
        for (i in 0..1) {
            for (j in 0..1)
            matrixMap[matrixPositionX-i][matrixPositionY - j] = true
        }
    }

    private fun addNewIPiece(positionX: Int, positionY: Int) {
        val matrixPositionX = positionX / Constants.WIDTH_BLOCK
        val matrixPositionY = positionY / Constants.HEIGHT_BLOCK
        for (i in 0..3) {
            matrixMap[matrixPositionX][matrixPositionY + i] = true
        }
    }

    fun checkColision(positionX: Int,positionY: Int): Boolean {
        when(nextBlockName){
            1->return checkIColision(positionX,positionY)
            2->return checkOColision(positionX,positionY)
        }
        return false
    }

    private fun checkOColision(positionX: Int, positionY: Int): Boolean {
        val matrixPositionX = positionX/Constants.WIDTH_BLOCK
        val matrixPositionY = positionY/Constants.HEIGHT_BLOCK
        return (matrixMap[matrixPositionX+2][matrixPositionY+3])
    }

    private fun checkIColision(positionX: Int, positionY: Int): Boolean {
        val matrixPositionX = positionX/Constants.WIDTH_BLOCK
        val matrixPositionY = positionY/Constants.HEIGHT_BLOCK
        return (matrixMap[matrixPositionX][matrixPositionY+3])
    }

    private fun eliminateRow(){
        var nCompletedRow = 0
        while (nCompletedRow < (Constants.WIDTH) &&  matrixMap[nCompletedRow][73]){
            nCompletedRow += 1
        }
        if (nCompletedRow == (Constants.WIDTH)){
            matrixUpdate()
        }
    }

    private fun matrixUpdate(){
        for(i in 0 until Constants.WIDTH ){
            for (j in 1 until Constants.HEIGHT){
                matrixMap[i][j] = matrixMap[i][j-1]

            }
        }
    }

}


