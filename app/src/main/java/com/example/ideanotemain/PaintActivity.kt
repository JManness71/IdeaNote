package com.example.ideanotemain

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.ideanotemain.PaintView.Companion.bmp
import com.example.ideanotemain.PaintView.Companion.colorList
import com.example.ideanotemain.PaintView.Companion.currentBrush
import com.example.ideanotemain.PaintView.Companion.pathList
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

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
        val save = findViewById<Button>(R.id.save_button)

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
        save.setOnClickListener{
            val uri = bitmapToFile(bmp)
            Toast.makeText(this, "Saved to " + uri, Toast.LENGTH_SHORT).show()
        }
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }

    private fun bitmapToFile(bitmap: Bitmap) : Uri {
        val wrapper = ContextWrapper(applicationContext)

        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try{
            val stream:OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }
        catch (e: IOException){
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)
    }
}