package com.example.ideanotemain

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class NoteActivity : AppCompatActivity() {
    var thisItem = ListItem()
    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        thisItem = intent.getSerializableExtra("item") as ListItem
        position = intent.getIntExtra("position", 0)

        val titleText = findViewById<EditText>(R.id.title_text)
        val bodyText = findViewById<EditText>(R.id.body_text)
        val saveBtn = findViewById<Button>(R.id.save_note_button)
        val sketchBtn = findViewById<Button>(R.id.add_sketch_button)
        val photoBtn = findViewById<Button>(R.id.add_picture_button)

        titleText.setText(thisItem.title)
        bodyText.setText(thisItem.body)

        val linear = findViewById<LinearLayout>(R.id.linear)

        for(element in thisItem.pictures){
            val img = ImageView(this)
            val bmp = BitmapFactory.decodeFile(element)
            img.setImageBitmap(bmp)
            linear.addView(img)
        }

        for(element in thisItem.drawings){
            val img = ImageView(this)
            val bmp = BitmapFactory.decodeFile(element)
            img.setImageBitmap(bmp)
            linear.addView(img)
        }

        sketchBtn.setOnClickListener{
            val sketchIntent = Intent(this, PaintActivity::class.java)
            sketchIntent.putExtra("sketchItem", thisItem)
            this.startActivityForResult(sketchIntent, 2)
        }

        photoBtn.setOnClickListener{
            val photoIntent = Intent(this, CameraActivity::class.java)
            this.startActivityForResult(photoIntent, 1)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val linear = findViewById<LinearLayout>(R.id.linear)
        if(requestCode == 1){
            thisItem.addPicture(data?.extras?.getString("returned_picture")!!)
            val img = ImageView(this)
            val bmp = BitmapFactory.decodeFile(data?.extras?.getString("returned_picture")!!)
            img.setImageBitmap(bmp)
            linear.addView(img)

        }
        else if(requestCode == 2){
            thisItem.addDrawing(data?.extras?.getString("returned_drawing")!!)
            val img = ImageView(this)
            val bmp = BitmapFactory.decodeFile(data?.extras?.getString("returned_drawing")!!)
            img.setImageBitmap(bmp)
            linear.addView(img)
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}