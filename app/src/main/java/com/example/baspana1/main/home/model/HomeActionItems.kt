package com.example.baspana1.main.home.model

class HomeActionItems(icon : Int, text : String) {
    private var icon : Int = 0
    private lateinit var text : String

    init {
        this.icon = icon
        this.text = text
    }

    fun  getIcon() : Int{
        return this.icon
    }

    fun setIcon(icon : Int) {
        this.icon = icon
    }

    fun getText() : String {
        return this.text
    }

    fun setText(text : String) {
        this.text = text
    }



}