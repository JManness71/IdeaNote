package com.example.ideanotemain

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.ideanotemain.PaintView.Companion.colorList
import com.example.ideanotemain.PaintView.Companion.currentBrush
import com.example.ideanotemain.PaintView.Companion.pathList

class PaintActivity : AppCompatActivity() {
    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        supportActionBar?.hide()

        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val blueBtn = findViewById<ImageButton>(R.id.blueColor)
        val blackBtn = findViewById<ImageButton>(R.id.blackColor)
        val eraser = findViewById<ImageButton>(R.id.whiteColor)

        redBtn.setOnClickListener{
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
        }
        blueBtn.setOnClickListener{
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
        }
        blackBtn.setOnClickListener{
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
        }
        eraser.setOnClickListener{
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}