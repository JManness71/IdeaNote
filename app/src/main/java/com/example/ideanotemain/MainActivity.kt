package com.example.ideanotemain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bSketch = findViewById<Button>(R.id.button_sketch)
        val bCam = findViewById<Button>(R.id.button_camera)
        val bNote = findViewById<Button>(R.id.button_note)

        bSketch.setOnClickListener{
            val intent = Intent(this, PaintActivity::class.java)
            startActivity(intent)
        }

        bCam.setOnClickListener{
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        bNote.setOnClickListener{
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }
}