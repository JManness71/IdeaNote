package com.example.ideanotemain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteActivity : AppCompatActivity() {
    var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val thisItem: ListItem = intent.getSerializableExtra("item") as ListItem
        val position = intent.getIntExtra("position", 0)

        val titleText = findViewById<EditText>(R.id.title_text)
        val bodyText = findViewById<EditText>(R.id.body_text)
        val saveBtn = findViewById<Button>(R.id.save_note_button)
        val sketchBtn = findViewById<Button>(R.id.add_sketch_button)
        val photoBtn = findViewById<Button>(R.id.add_picture_button)

        titleText.setText(thisItem.title)
        bodyText.setText(thisItem.body)

        sketchBtn.setOnClickListener{
            val sketchIntent = Intent(this, PaintActivity::class.java)
            this.startActivity(sketchIntent)
        }

        photoBtn.setOnClickListener{
            val photoIntent = Intent(this, CameraActivity::class.java)
            this.startActivity(photoIntent)
        }

        saveBtn.setOnClickListener{
            thisItem.title = titleText.text.toString()
            thisItem.body = bodyText.text.toString()
            val intent = Intent()
            intent.putExtra("returned_item", thisItem)
            intent.putExtra("returned_position", position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}