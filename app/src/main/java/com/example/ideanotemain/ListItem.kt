package com.example.ideanotemain

import java.io.Serializable

public class ListItem: Serializable  {
    var title: String = ""
    var body: String = ""
    val pictures = arrayListOf<String>()
    val drawings = arrayListOf<String>()

    fun addPicture(location: String){
        pictures.add(location)
    }

    fun addDrawing(location: String){
        drawings.add(location)
    }
}