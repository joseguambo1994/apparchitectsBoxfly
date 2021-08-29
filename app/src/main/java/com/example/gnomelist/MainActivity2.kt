package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.item_gnome.view.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val selectedBrastewarkName = intent.getStringExtra("selectedBrastewarkName")
        val selectedBrastewarkThumbnail = intent.getStringExtra("selectedBrastewarkThumbnail")
        val selectedBrastewarkProfessions = intent.getStringArrayListExtra("selectedBrastewarkProfessions")
        println("selectedBrastewarkName " + selectedBrastewarkName)
        println("selectedBrastewarkThumbnail "+selectedBrastewarkThumbnail)
        println("selectedBrastewarkProfessions "+selectedBrastewarkProfessions)

        setContentView(R.layout.activity_main2)
                if (selectedBrastewarkThumbnail != null) {
            Glide.with(this)
                .load(selectedBrastewarkThumbnail)
                .into(ivThumbail)
        }

    }

//    fun loadThumbnail(thumbnailUrl:String){
//        Glide.with(this)
//            .load(thumbnailUrl)
//            .into(ivThumbail)
//    }
}