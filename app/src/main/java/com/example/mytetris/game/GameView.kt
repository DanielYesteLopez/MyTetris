package com.example.mytetris.game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import com.example.mytetris.R
import com.example.mytetris.TetrisBlock
import com.example.mytetris.blocks.Iblock
import com.example.mytetris.blocks.MonoBlock
import com.example.mytetris.blocks.Oblock
import kotlin.concurrent.thread

class GameView (context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    var gameLogic = GameLogic()
    var tetrisLBlock :Iblock? = null
    var tetrisMonoBlock: MonoBlock? = null
    var tetrisOBlock : Oblock? = null
    private val thread: GameThread

    private var touched: Boolean = false
    private var touchedX: Int = 0
    private var touchedY: Int = 0
    init {

        holder.addCallback(this)

        // instantiate the game thread
        thread = GameThread(holder, this)
        gameLogic.nextBlock()
    }
    override fun surfaceCreated(holder: SurfaceHolder?) {
        this.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        gameLogic.initMatrix()
        tetrisLBlock = Iblock(BitmapFactory.decodeResource(resources,R.drawable.sticker_sprite))
        tetrisMonoBlock = MonoBlock(BitmapFactory.decodeResource(resources,R.drawable.mono_block))
        tetrisOBlock = Oblock(BitmapFactory.decodeResource(resources,R.drawable.block_sprite))
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }


    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // when ever there is a touch on the screen,
        // we can get the position of touch
        // which we may use it for tracking some of the game objects
        touchedX = event.x.toInt()
        touchedY = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> touched = false
            MotionEvent.ACTION_CANCEL -> touched = false
            MotionEvent.ACTION_OUTSIDE -> touched = false
        }
        return true
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (canvas != null) {
            gameLogic.drawMap(tetrisMonoBlock!!,canvas)
            when(gameLogic.nextBlockName){
                1-> tetrisLBlock!!.draw(canvas)
                2->tetrisOBlock!!.draw(canvas)
            }

        }
    }
    fun update(){
        when(gameLogic.nextBlockName){
            1-> updateLBlock()
            2->updateOBlock()
        }

    }

    fun updateLBlock(){
        gameLogic.update(tetrisLBlock!!)
        if(touched){
            tetrisLBlock!!.updateMovement(touchedX,touchedY)
        }
    }

    fun updateOBlock(){
        gameLogic.update(tetrisOBlock!!)
        if(touched){
            tetrisOBlock!!.updateMovement(touchedX,touchedY)
        }
    }
}