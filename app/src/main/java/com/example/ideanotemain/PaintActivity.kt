package com.example.ideanotemain

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.core.content.FileProvider
import com.example.ideanotemain.PaintView.Companion.bmp
import com.example.ideanotemain.PaintView.Companion.colorList
import com.example.ideanotemain.PaintView.Companion.currentBrush
import com.example.ideanotemain.PaintView.Companion.pathList
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

private const val FILE_NAME = "drawing.jpg"
private const val REQUEST_CODE = 1
private lateinit var drawFile: File

class PaintActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    var brushSizes = arrayOf(6, 8, 10, 12, 14)
    var location = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        supportActionBar?.hide()

        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val blueBtn = findViewById<ImageButton>(R.id.blueColor)
        val greenBtn = findViewById<ImageButton>(R.id.greenColor)
        val blackBtn = findViewById<ImageButton>(R.id.blackColor)
        val eraser = findViewById<ImageButton>(R.id.whiteColor)
        val save = findViewById<Button>(R.id.save_button)
        val spinner = findViewById<Spinner>(R.id.brush_spinner)

        spinner.onItemSelectedListener = this
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, brushSizes)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        redBtn.setOnClickListener{
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }
        blueBtn.setOnClickListener{
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }
        greenBtn.setOnClickListener{
            paintBrush.color = Color.GREEN
            currentColor(paintBrush.color)
        }
        blackBtn.setOnClickListener{
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }
        eraser.setOnClickListener{
            pathList.clear()
            colorList.clear()
            path.reset()
        }
        save.setOnClickListener{
            drawFile = getPhotoFile(FILE_NAME)
            location = drawFile.absolutePath
            val out = FileOutputStream(drawFile)
            try{
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            catch (e: IOException) {
                Toast.makeText(this, "Saving Failed", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent()
            intent.putExtra("returned_drawing", location)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {
        paintBrush.strokeWidth = brushSizes[position].toFloat()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // Do Nothing
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }
}