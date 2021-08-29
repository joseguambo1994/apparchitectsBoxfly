package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent




class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        val value1 = intent.getStringExtra("currentBrastlewarkProfessions")
        val value2 = intent.getStringExtra("currentBrastlewark")
        println("value1 " + value1)
        println("value2 " + value2)
    }
}