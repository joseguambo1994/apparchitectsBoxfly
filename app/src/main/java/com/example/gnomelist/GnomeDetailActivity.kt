package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gnome_list.*
import kotlinx.android.synthetic.main.gnome_detail.*
import kotlinx.android.synthetic.main.item_gnome.view.*
import kotlinx.android.synthetic.main.item_profession.*


class GnomeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val selectedGnomeName = intent.getStringExtra("selectedGnomeName")
        val selectedGnomeThumbnail = intent.getStringExtra("selectedGnomeThumbnail")
        val selectedGnomeProfessions = intent.getStringArrayListExtra("selectedGnomeProfessions")
        
        setContentView(R.layout.gnome_detail)
                if (selectedGnomeThumbnail != null) {
            loadThumbnail(selectedGnomeThumbnail)
        }
        tvName.text=selectedGnomeName
        if (selectedGnomeProfessions != null) {
            initRecycler(selectedGnomeProfessions)
        }


    }

    private fun loadThumbnail(thumbnailUrl:String){
        Glide.with(this)
            .load(thumbnailUrl)
            .into(ivThumbnail)
    }

    private fun initRecycler(listOfProfessions:ArrayList<String>) {
        rvProfessions.layoutManager = LinearLayoutManager(this)
        val adapter = ProfessionAdapter(listOfProfessions);
        rvProfessions.adapter=adapter
    }
}