package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.item_gnome.view.*
import kotlinx.android.synthetic.main.item_profession.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val selectedBrastlewarkName = intent.getStringExtra("selectedBrastlewarkName")
        val selectedBrastlewarkThumbnail = intent.getStringExtra("selectedBrastlewarkThumbnail")
        val selectedBrastlewarkProfessions = intent.getStringArrayListExtra("selectedBrastlewarkProfessions")
        println("selectedBrastewarkName " + selectedBrastlewarkName)
        println("selectedBrastewarkThumbnail "+selectedBrastlewarkThumbnail)
        println("selectedBrastewarkProfessions "+selectedBrastlewarkProfessions)

        setContentView(R.layout.activity_main2)
                if (selectedBrastlewarkThumbnail != null) {
            loadThumbnail(selectedBrastlewarkThumbnail)
        }
       // tvProfession.text=selectedBrastlewarkName
        tvName.text=selectedBrastlewarkName
        if (selectedBrastlewarkProfessions != null) {
            initRecycler(selectedBrastlewarkProfessions)
        }


    }

    fun loadThumbnail(thumbnailUrl:String){
        Glide.with(this)
            .load(thumbnailUrl)
            .into(ivThumbnail)
    }

    fun initRecycler(listOfProfessions:ArrayList<String>) {
        rvProfessions.layoutManager = LinearLayoutManager(this)
        val adapter = ProfessionAdapter(listOfProfessions);
        rvProfessions.adapter=adapter
    }
}