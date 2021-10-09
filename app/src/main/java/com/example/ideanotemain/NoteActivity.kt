package com.example.ideanotemain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val sketchBtn = findViewById<Button>(R.id.add_sketch_button)
        val photoBtn = findViewById<Button>(R.id.add_picture_button)

        sketchBtn.setOnClickListener{
            val sketchIntent = Intent(this, PaintActivity::class.java)
            this.startActivity(sketchIntent)
        }

        photoBtn.setOnClickListener{
            val photoIntent = Intent(this, CameraActivity::class.java)
            this.startActivity(photoIntent)
        }
    }
}