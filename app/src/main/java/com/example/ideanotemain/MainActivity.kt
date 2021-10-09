package com.example.ideanotemain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview = findViewById<ListView>(R.id.listView1)
        val addButton = findViewById<Button>(R.id.button1)
        val value = findViewById<EditText>(R.id.editText1)

        val listElements = ArrayList<String>()
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listElements)

        listview.adapter = adapter

        addButton.setOnClickListener{
            listElements.add(value.text.toString())
            adapter.notifyDataSetChanged()
        }

        listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NoteActivity::class.java)
            this.startActivity(intent)
        }

//        val bSketch = findViewById<Button>(R.id.button_sketch)
//        val bCam = findViewById<Button>(R.id.button_camera)
//        val bNote = findViewById<Button>(R.id.button_note)
//
//        bSketch.setOnClickListener{
//            val intent = Intent(this, PaintActivity::class.java)
//            startActivity(intent)
//        }
//
//        bCam.setOnClickListener{
//            val intent = Intent(this, CameraActivity::class.java)
//            startActivity(intent)
//        }
//
//        bNote.setOnClickListener{
//            val intent = Intent(this, NoteActivity::class.java)
//            startActivity(intent)
//        }
    }
}