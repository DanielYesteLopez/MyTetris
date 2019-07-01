package com.example.mytetris

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import com.example.mytetris.game.GameView

class Menu : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            setContentView(GameView(this))
        }
    }
}
