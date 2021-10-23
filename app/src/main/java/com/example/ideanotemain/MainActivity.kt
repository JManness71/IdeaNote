package com.example.ideanotemain

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var listPrefs: SharedPreferences
    lateinit var listElements: ArrayList<ListItem>
    var listTitles = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview = findViewById<ListView>(R.id.listView1)
        val addButton = findViewById<Button>(R.id.button1)
        val value = findViewById<EditText>(R.id.editText1)
        loadData()
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listTitles)

        listview.adapter = adapter

        addButton.setOnClickListener{
            val l = ListItem()
            l.title = value.text.toString()
            listElements.add(l)
            listTitles.add(value.text.toString())
            saveData()
            adapter.notifyDataSetChanged()
        }

        listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("item", listElements[position])
            intent.putExtra("position", position)
            //this.startActivity(intent)
            this.startActivityForResult(intent, 1)
        }

        listview.setOnItemLongClickListener {_, _, index, _ ->
            listElements.removeAt(index)
            listTitles.removeAt(index)
            saveData()
            adapter.notifyDataSetChanged()
            true
        }
    }

    private fun loadData(){
        listPrefs = getSharedPreferences("list_prefs", MODE_PRIVATE)

        val gson = Gson()

        val json: String? = listPrefs.getString("notes", emptyList<String>().toString())

        val type = object: TypeToken<ArrayList<ListItem>>() {}.type

        listElements = gson.fromJson<ArrayList<ListItem>>(json, type)

        if(listElements == null){
            listElements = ArrayList<ListItem>()
        }

        for(t in listElements){
            listTitles.add(t.title)
        }
    }

    private fun saveData(){
        listPrefs = getSharedPreferences("list_prefs", MODE_PRIVATE)

        val editor: SharedPreferences.Editor = listPrefs.edit()

        val gson = Gson()

        val json: String = gson.toJson(listElements)

        editor.putString("notes", json)
        editor.apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1){
            val pos = data?.extras?.getInt("returned_position", 0)
            listElements[pos!!] = data.extras!!.getSerializable("returned_item") as ListItem
            saveData()
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}